const API_BASE_URL = 'http://localhost:8080';
let movieToDelete = null;

async function fetchMovies() {
    try {
        const res = await fetch(`${API_BASE_URL}/movies`);
        if (!res.ok) {
            throw new Error(`Erro ${res.status}`);
        }
        const movies = await res.json();
        renderMovies(movies);
    } catch (error) {
        console.error('Erro ao buscar filmes: ', error);
        alert('Erro ao carregar filmes. Tente novamente.')
    }
}

function renderMovies(movies) {
    const movieCards = document.getElementById('movie-cards');
    if (!movieCards) return;
    const existingCards = Array.from(movieCards.children);
    existingCards.forEach(card => card.remove());

    movies.forEach(movie => {
        movieCards.innerHTML += `<div class="col-md-6 col-lg-4">
    <div class="card h-100 shadow-sm">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-start mb-2">
                <h5 class="card-title">${movie.title}</h5>
                <div class="dropdown">
                    <button class="btn btn-sm btn-link text-dark" type="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        <i class="bi bi-three-dots-vertical"></i></button>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li>
                            <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#updateMovieModal"
                               onclick="openUpdateModal(${movie.id}, '${movie.title}', '${movie.synopsis}', '${movie.genre}', ${movie.releaseYear})"><i
                                    class="bi bi-pencil me-2"></i>Editar</a>
                        </li>
                        <li>
                            <hr class="dropdown-divider">
                        </li>
                        <li>
                            <a class="dropdown-item text-danger" data-bs-toggle='modal'
                               data-bs-target="#deleteMovieModal"
                               onclick="openDeleteModal(${movie.id}, '${movie.title}')"><i
                                    class="bi bi-trash me-2"></i>Excluir</a>
                        </li>
                    </ul>
                </div>
            </div>
            <p class="card-text text-muted small mb-2">
                <i class="bi bi-tag me-1"></i>${movie.genre}
                <span class="ms-3"><i class="bi bi-calendar3 me-1"></i>${movie.releaseYear}</span>
            </p>
            <p class="card-text">${movie.synopsis}</p>
            <a href="reviews.html?id=${movie.id}" class="btn btn-outline-primary w-100">
                <i class="bi bi-info-circle me-2"></i>Ver Detalhes
            </a>
        </div>
    </div>
</div>`
    });
}

async function handleMovieForm(event) {
    event.preventDefault();

    const form = event.target;
    if (!form.checkValidity()) {
        event.stopPropagation();
        form.classList.add('was-validated');
        return;
    }

    const movie = {
        title: document.getElementById('title').value,
        synopsis: document.getElementById('synopsis').value,
        genre: document.getElementById('genre').value,
        releaseYear: parseInt(document.getElementById('releaseYear').value)
    };

    console.log(movie)

    try {
        const res = await fetch(`${API_BASE_URL}/movies/add`, {
            method: 'POST', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify(movie),
        });
        if (res.ok) {
            window.location.href = 'movies.html';
        }
    } catch (error) {
        console.error('Erro ao enviar dados: ', error);
        alert('Erro ao cadastrar filme. Tente novamente.')
    }
}

async function updateMovie(event) {
    event.preventDefault();

    const form = event.target;
    if (!form.checkValidity()) {
        event.stopPropagation();
        form.classList.add('was-validated');
        return
    }

    const movieId = document.getElementById('updateMovieId').value;
    const movie = {
        title: document.getElementById('updateTitle').value,
        synopsis: document.getElementById('updateSynopsis').value,
        genre: document.getElementById('updateGenre').value,
        releaseYear: parseInt(document.getElementById('updateReleaseYear').value)
    };
    try {
        const res = await fetch(`${API_BASE_URL}/movies/update/${movieId}`, {
            method: 'PUT', headers: {
                'Content-Type': 'application/json',
            }, body: JSON.stringify(movie),
        });
        if (!res.ok) {
            throw new Error(`Erro ${res.status}`);
        }
        const modal = bootstrap.Modal.getInstance(document.getElementById('updateMovieModal'));
        modal.hide();
        await fetchMovies();
    } catch (error) {
        console.error('Erro ao atualizar filme: ', error);
    }
}

function openUpdateModal(id, title, synopsis, genre, releaseYear) {
    document.getElementById('updateMovieId').value = id;
    document.getElementById('updateTitle').value = title;
    document.getElementById('updateSynopsis').value = synopsis;
    document.getElementById('updateGenre').value = genre;
    document.getElementById('updateReleaseYear').value = releaseYear;
}

async function deleteMovie() {
    if (!movieToDelete) return;
    try {
        const res = await fetch(`${API_BASE_URL}/movies/delete/${movieToDelete}`, {
            method: 'DELETE',
        });
        if (!res.ok) {
            throw new Error('Falha ao deletar o filme');
        }
        const modal = bootstrap.Modal.getInstance(document.getElementById('deleteMovieModal'));
        modal.hide();
        await fetchMovies();
    } catch (error) {
        console.error('Erro ao deletar o filme: ', error);
        alert('Erro ao deletar filme. Tente novamente.')
    } finally {
        movieToDelete = null;
    }
}

function openDeleteModal(movieId, movieTitle) {
    movieToDelete = movieId;
    document.getElementById('deleteMovieTitle').textContent = movieTitle;
}


document.addEventListener('DOMContentLoaded', function () {
    if (document.getElementById('movie-cards')) {
        fetchMovies();
    }

    const formAdd = document.getElementById('movieForm');
    if (formAdd) {
        formAdd.addEventListener('submit', handleMovieForm);
    }

    const updateForm = document.getElementById('updateMovieForm');
    if (updateForm) {
        updateForm.addEventListener('submit', updateMovie);
    }

    const confirmDeleteButton = document.getElementById('confirmDeleteButton');
    if (confirmDeleteButton) {
        confirmDeleteButton.addEventListener('click', deleteMovie);
    }
});