let post = new XMLHttpRequest();
let buttons = document.getElementsByClassName("login-field");
Array.from(buttons).forEach(function(button)  {
    let username = button.getAttribute("username");
    let password = button.getAttribute("password");
    let data = {"username": username, "password": password};
    let jsonData = JSON.stringify(data);
    button.addEventListener("click", ()=> {
        post.open("POST", "/login", true);
    post.setRequestHeader("Content-Type", "application/json");
    post.send(jsonData);
})
});