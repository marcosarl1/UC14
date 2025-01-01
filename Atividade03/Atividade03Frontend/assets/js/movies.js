const API_BASE_URL = 'http://localhost:8080';

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
        movieCards.innerHTML += `
        <div class="col-md-6 col-lg-4">
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
                                    <a class="dropdown-item"><i class="bi bi-pencil me-2"></i>Editar</a>
                                    <a class="dropdown-item text-danger" onclick="deleteMovie(${movie.id})"><i class="bi bi-trash me-2"></i>Excluir</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <p class="card-text text-muted small mb-2">
                        <i class="bi bi-tag me-1"></i>${movie.genre}
                        <span class="ms-3">
                                <i class="bi bi-calendar3 me-1"></i>${movie.releaseYear}
                            </span>
                    </p>
                    <p class="card-text">${movie.synopsis}</p>
                    <a href="/reviews/${movie.id}" class="btn btn-outline-primary w-100">
                        <i class="bi bi-info-circle me-2"></i>Ver Detalhes
                    </a>
                </div>
            </div>
        </div>`;
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

async function updateMovie(movieId) {

}

async function deleteMovie(movieId) {
    if (!confirm('Tem certeza que deseja deleter esse filme?')) return;

    try {
        const res = await fetch(`${API_BASE_URL}/movies/delete/${movieId}`, {
            method: 'DELETE',
        });
        if (res.ok) {
            alert('Filme deletado com sucesso!');
            fetchMovies();
        } else {
            throw new Error('Falha ao deletar o filme');
        }
    } catch (error) {
        console.error('Erro ao deletar o filme: ', error);
        alert('Erro ao deletar filme. Tente novamente.')
    }
}


document.addEventListener('DOMContentLoaded', function () {
    const movieCards = document.getElementById('movie-cards')
    if (movieCards) {
        fetchMovies();

        const form = document.getElementById('movieForm');
        if (form) {
            form.addEventListener('submit', handleMovieForm);
        }
    }
});