document.addEventListener('DOMContentLoaded', () => {
   const navbar = document.getElementById('navbar');
   navbar.innerHTML = `
           <nav class="nav navbar-expand-lg navbar-dark bg-primary mb-4">
            <div class="container py-3">
                <a class="navbar-brand fs-4" href="movies.html">
                    <i class="bi bi-film me-2"></i>Casa Cultural</a>
            </div>
        </nav>
   `;
});