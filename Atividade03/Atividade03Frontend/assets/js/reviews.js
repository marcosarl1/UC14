const API_BASE_URL = 'http://localhost:8080/movies';
let currentMovieId = null;

function getMovieIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('id');
}

async function fetchMovie() {
    try {
        currentMovieId = getMovieIdFromUrl();
        const res = await fetch(`${API_BASE_URL}/${currentMovieId}`);
        if (!res.ok) {
            throw new Error('Filme não encontrado');
        }
        const movie = await res.json();
        renderMovie(movie);
        await fetchReviews();
    } catch (error) {
        console.error('Erro ao carregar detalhes do filme: ', error);
    }
}

async function fetchReviews() {
    try {
        const res = await fetch(`${API_BASE_URL}/${currentMovieId}/reviews`);
        if (!res.ok) {
            throw new Error('Análises não encontradas');
        }
        const reviews = await res.json();;
        renderReviews(reviews)
    } catch (error) {
        console.error('Erro ao carregar análises: ', error);
    }
}

async function handleReviewForm(event) {
    event.preventDefault();

    const form = event.target;
    if (!form.checkValidity()) {
        event.stopPropagation();
        form.classList.add('was-validated');
        return;
    }

    const reviewData = {
        review: document.getElementById('review').value,
        score: parseFloat(document.getElementById('score').value)
    };

    try {
        const res = await fetch(`${API_BASE_URL}/${currentMovieId}/reviews/add`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(reviewData)
        });
        if (!res.ok) {
            throw new Error('Erro ao salvar análise');
        }

        const modal = bootstrap.Modal.getInstance(document.getElementById('addReviewModal'));
        modal.hide();
        form.reset();
        form.classList.remove('was-validated');
        await fetchReviews();
    } catch (error) {
        console.error('Erro ao salvar análise: ', error);
    }
}

function renderMovie(movie) {
    document.getElementById('movieTitle').textContent = movie.title;
    document.getElementById('movieSynopsis').textContent = movie.synopsis;
    document.getElementById('movieGenre').textContent = movie.genre;
    document.getElementById('movieYear').textContent = movie.releaseYear;
}


function renderReviews(reviews) {
    const reviewCards = document.getElementById('reviewsContainer');
    if (!reviewCards) return;
    const existingCards = Array.from(reviewCards.children);
    existingCards.forEach(card => card.remove());

    reviews.forEach(review => {
        reviewCards.innerHTML += `<div class="col-md-6 mb-3">
    <div class="card h-100 shadow-sm">
        <div class="card-body">
            <div class="d-flex justify-content-between align-items-center">
                <div class="starts-container">
                    ${generateStars(review.score)}
                </div>
                <div class="d-flex align-items-center">
                    <span class="badge bg-primary">${review.score.toFixed(1)}/5</span>
                    <div class="dropdown">
                        <button class="btn btn-sm btn-link text-dark" type="button" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <i class="bi bi-three-dots-vertical"></i></button>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li>
                                <a class="dropdown-item" data-bs-toggle="modal" data-bs-target="#updateReviewModal"><i
                                        class="bi bi-pencil me-2"></i>Editar</a>
                            </li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <a class="dropdown-item text-danger" data-bs-toggle='modal'
                                   data-bs-target="#deleteReviewModal"><i
                                        class="bi bi-trash me-2"></i>Excluir</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <p class="card-text">${review.review}</p>
        </div>
    </div>
</div>`;
    })
}

function generateStars(score) {
    let starsHtml = '';
    for (let i = 1; i <= 5; i++) {
        if (score >= i) {
            starsHtml += '<i class="bi bi-star-fill text-warning"></i>'
        } else if (score >= i - 0.5) {
            starsHtml += '<i class="bi bi-star-half text-warning"></i>'
        } else {
            starsHtml += '<i class="bi bi-star text-warning"></i>'
        }
    }
    return starsHtml;
}

document.addEventListener('DOMContentLoaded', function () {
    fetchMovie();

    const reviewForm = document.getElementById('reviewForm');
    if (reviewForm) {
        reviewForm.addEventListener('submit', handleReviewForm);
    }
})