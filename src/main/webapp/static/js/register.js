function registration() {
    let post = new XMLHttpRequest();
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let data = {"username": username, "password": password, "email": email};
    let JSONData = JSON.stringify(data);
    post.open("post", "/register");
    post.setRequestHeader("Content-type", "application/json");
    post.onreadystatechange = function () {
        if (post.readyState === 4 && post.status === 200) {
            let json = post.responseText;
            if (json === "true"){location.replace("/login")}
            else {alert(json)}
        }
    };
    post.send(JSONData);


}