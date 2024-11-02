//src/main/resources/static/script/request-login.js


document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    let username = document.getElementById('username').value;
    console.log('username: ', username);

    let password = document.getElementById('password').value;
    console.log('password: ', password);

    // Captura o token CSRF do meta tag
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');


    try{
        let response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                [csrfHeader]: csrfToken
            },
            body: new URLSearchParams({
                username: username,
                password: password
            })
        });
        console.log('Este é o URL: ' + response.url);
        console.log('Response status: ', response.status); // Adicionado para ver o status da resposta

        if(response.ok){
            console.log("Status 200, a resposta: ",  response);
        }

    }catch(err){
        console.log('Erro durante a requisiacao de acesso: ' + err);
    }
})