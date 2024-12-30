document.addEventListener('DOMContentLoaded', function () {
    fetch('http://localhost:8080/movies')
        .then(response => response.json())
        .then(data => {
            const movieCards = document.getElementById('movie-cards');
            data.forEach(movie => {
                const movieCard =
                    `
                               <div class="col-md-6 col-lg-4">
                                <div class="card h-100 shadow-sm">
                                    <div class="card-body">
                                        <h5 class="card-title">${movie.title}</h5>
                                        <p class="card-text text-muted small mb-2">
                                            <i class="bi bi-tag me-1"></i>
                                            ${movie.genre}
                                            <span class="ms-3">
                                                <i class="bi bi-calendar3 me-1"></i>
                                                ${movie.releaseYear}
                                            </span>
                                        </p>
                                        <p class="card-text">${movie.synopsis}</p>
                                        <a href="/reviews/${movie.id}" class="btn btn-outline-primary w-100">
                                            <i class="bi bi-info-circle me-2"></i>Ver Detalhes
                                        </a>
                                    </div>
                                </div>
                            </div>
            `;
                movieCards.innerHTML += movieCard;
            });
        })
        .catch(error => console.error('Erro ao buscar filmes: ', error));
});