<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>E-learning</title>

    <link rel="stylesheet" href="../index.css"/>
    <link rel="stylesheet" href="../css/footer.css"/>
    <link rel="stylesheet" href="../indexLoginForm.css">
    <link rel="icon" href="../resources/Logo-04-removebg-preview.png">
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="Con1">
        <h1>Welcome To <span>E-Learning</span></h1>
        <h3>University Of Constantine 2</h3>
        <img class="logo1" src="../resources/Logo-04-removebg-preview.png" alt="image of the logo of e-learning"/>
    </div>
    <div class="Con1">
        <div class="login-form-wrap">

            <form class="login-form" name="login" action="login" method="post">

                <span class="login-form-title">E-Learning</span>

                <div class="input-field-container">
                    <label for="email"></label><input class="input-field" type="text" name="email" placeholder="Email" id="email"
                                                      spellcheck='false'>

                </div>

                <div class="input-field-container">
                    <label for="password"></label><input class="input-field" type="password" name="password" placeholder="Password"
                                                         id="password"
                                                         spellcheck='false'>
                </div>

                <input class="login-button" id="button" type="submit" value="Login"/>

            </form>

        </div>
    </div>
</div>

</body>
</html>
