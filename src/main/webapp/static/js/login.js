function verify() {
    let post = new XMLHttpRequest();
    let usernameField = document.querySelector("#username");
    let passwordField = document.querySelector(("#password"));
    let username = usernameField.value;
    let password = passwordField.value;
    let data = {"username": username, "password": password};
    let jsonData = JSON.stringify(data);
    post.open("post", "/login");
    post.setRequestHeader("Content-Type", "application/json");
    post.onreadystatechange = function () {
        if (post.readyState === 4 && post.status === 200) {
            let json = JSON.parse(post.responseText);
            console.log(json);
            if (json) {
                location.replace("/")
            } else {
                let container = document.getElementById("login-container");
                let div = document.createElement("div");
                div.innerHTML = '<h4>Wrong username or password</h4>';
                container.appendChild(div);
            }
        }
    };
    post.send(jsonData)
}