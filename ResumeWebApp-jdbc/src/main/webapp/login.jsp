
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>resume Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="assets/css/users.css">

</head>
<body>

<div style="margin-top: 100px" class="Conteiner myConteiner">
    <center>
        <h1>Login:</h1>
    </center>
    <br/>

    <form action="login" method="post">
        <div>
            <label for="email">email:</label>
            <input class="btn btn-outline-secondary" name="email" type="text" value="">
        </div>
        <br/>
        <div>
            <label for="password">password:</label>
            <input  class="btn btn-outline-secondary"  name="password" type="password" value="">
        </div>
        <br/>
        <div>
            <input   class="btn btn-primary" type="submit" name="login" value="Login">
        </div>
    </form>


</div>

</body>
</html>
