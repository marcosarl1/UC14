document.addEventListener('DOMContentLoaded', () => {
   const navbar = document.getElementById('navbar');
   navbar.innerHTML = `
           <nav class="nav navbar-expand-lg navbar-dark bg-primary mb-4">
            <div class="container py-3 d-flex justify-content-between align-items-center">
                <a class="navbar-brand fs-4" href="movies.html">
                    <i class="bi bi-film me-2"></i>Casa Cultural</a>
                    <button id="toggleTheme" class="btn btn-outline-light"><i class="bi bi-moon-fill me-2"></i>Tema escuro</button>
            </div>
        </nav>
   `;

   const themeToggle = document.getElementById('toggleTheme');
   const body = document.body;

   const currentTheme = getCookies('theme') || 'light';
   if (currentTheme === 'dark') {
      enableDarkMode();
   }

   themeToggle.addEventListener('click', () => {
      (body.classList.contains('dark-mode')) ? disableDarkMode() : enableDarkMode();
   });
});