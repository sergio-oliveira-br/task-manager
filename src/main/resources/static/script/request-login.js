//src/main/resources/static/script/request-login.js


document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    let username = document.getElementById('username').value;
    console.log('username: ', username);

    let password = document.getElementById('password').value;
    console.log('password: ', password);

    try{
        let response = await fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({username, password}),

        });
        console.log('Este é o URL: ' + response.url);
        console.log('Response status: ', response.status); // Adicionado para ver o status da resposta

        if(response.ok){
            console.log("Status 200, a resposta: ",  response);

            if(response.headers.get('content-type')?.includes('application/json'))
            {
                let data = await response.json();
                console.log('Response OK -> Data: ', data);
            }
        }

    }catch(err){
        console.log(err);
    }
})