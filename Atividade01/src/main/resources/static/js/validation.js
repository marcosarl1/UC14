document.addEventListener('DOMContentLoaded', () => {
    const inputs = document.querySelectorAll('input[required], textarea[required]');

    inputs.forEach(input => {
       input.addEventListener('input', () => {
           if (input.checkValidity()) {
               input.classList.remove('is-invalid');
           }
       });
    });
});