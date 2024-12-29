document.addEventListener('DOMContentLoaded', () => {
    const inputs = document.querySelectorAll('input, textarea');
    const form = document.getElementById('reviewForm');

    inputs.forEach(input => {
       input.addEventListener('input', () => {
           if (input.checkValidity()) {
               input.classList.remove('is-invalid');
           }
       });
    });

    form.addEventListener('submit', ev => {
       if (!form.checkValidity()) {
           ev.preventDefault();
           ev.stopPropagation();
       }
       form.classList.add('was-validated');
    }, false);
});