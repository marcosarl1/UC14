// Theme management with Bootstrap's data-bs-theme
function initTheme() {
    const currentTheme = getCookies('theme') || 'light';
    if (currentTheme === 'dark') {
        enableDarkMode();
    }
}

function enableDarkMode() {
    document.documentElement.setAttribute('data-bs-theme', 'dark');

    const themeToggle = document.getElementById('toggleTheme');
    if (themeToggle) {
        themeToggle.innerHTML = '<i class="bi bi-sun-fill me-2"></i>Tema Claro';
    }

    setCookies('theme', 'dark', 30);
}

function disableDarkMode() {
    document.documentElement.setAttribute('data-bs-theme', 'light');

    const themeToggle = document.getElementById('toggleTheme');
    if (themeToggle) {
        themeToggle.innerHTML = '<i class="bi bi-moon-fill me-2"></i>Tema Escuro';
    }

    setCookies('theme', 'light', 30);
}

document.addEventListener('DOMContentLoaded', () => {
    initTheme();

    const themeToggle = document.getElementById('toggleTheme');
    if (themeToggle) {
        themeToggle.addEventListener('click', () => {
            const currentTheme = document.documentElement.getAttribute('data-bs-theme');
            currentTheme === 'dark' ? disableDarkMode() : enableDarkMode();
        });
    }
});