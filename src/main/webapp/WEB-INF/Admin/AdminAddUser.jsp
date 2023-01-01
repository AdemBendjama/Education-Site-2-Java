<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>E-Learning</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap" rel="stylesheet"/>

    <link rel="icon" href="../../resources/Logo-04-removebg-preview.png">
    <link rel="stylesheet" href="../../css/footer.css"/>
    <link rel="stylesheet" href="../../css/header.css"/>
    <link rel="stylesheet" href="../../css/sidebar.css"/>
    <link rel="stylesheet" href="../../css/UsersStyle.css"/>
    <link rel="stylesheet" href="../../css/FormStyle.css"/>
</head>
<body>
<div class="containers">

    <div class="Navbar">

        <div class="logo">
            <a href="./redirect"><img src="../../resources/Logo-04-removebg-preview.png" alt="logo"/></a>
        </div>

        <nav>
            <ul>
                <li>
                    <a href="./Logout">
                        Log Out
                    </a>
                </li>
                <li>
                    <img src="../../resources/admin-24.png" alt="profile-pic grey icon"/>
                    <a href="./redirect">
                        <c:out value="${admin.username}"/>
                    </a>
                </li>
            </ul>
        </nav>

    </div>

    <div class="center-content">

        <div id="sidebar">
            <header>
                <a>Menu</a>
            </header>
            <ul class="nav">
                <li>
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon"/>
                    <a href="./listUsers">
                        List Users
                    </a>
                </li>
                <li>
                    <img src="../../resources/change-user-24.png" alt="add-user grey icon"/>
                    <a href="./updateUser">
                        Update a User
                    </a>
                </li>
                <li>
                    <img src="../../resources/remove-user-24.png" alt="add-user grey icon"/>
                    <a href="./deleteUser">
                        Delete a User
                    </a>
                </li>
                <li>
                    <img src="../../resources/add-user-24.png" alt="add-user grey icon"/>
                    <a href="./addUser">
                        Add a User
                    </a>
                </li>
            </ul>
        </div>

        <div class="page-content">
            <form class="form" action="addUser" method="post">
                <ul>
                    <li>
                        <label for="add-name">Name</label>
                        <input type="text" name="name" id="add-name" maxlength="100" required spellcheck='false'/>
                        <span>Fullname of the User [ Required ]</span>
                    </li>
                    <li>
                        <label for="add-email">Email</label>
                        <input type="email" name="email" id="add-email" maxlength="100" required spellcheck='false'/>
                        <span>Enter a valid email address [ Required ]</span>
                    </li>
                    <li>
                        <label for="add-password">Password</label>
                        <input type="password" name="password" id="add-password" minlength="7" maxlength="100" required/>
                        <span>Enter a Password [ Required ]</span>
                    </li>
                    <li>
                        <label for="add-position">Position</label>
                        <input type="text" name="position" pattern="teacher|student" id="add-position" required
                               spellcheck='false'/>
                        <span> Teacher or Student [ Required ]</span>
                    </li>
                    <li>
                        <input type="submit" id="add-submit" value="Add"/>
                    </li>
                </ul>
            </form>
        </div>
    </div>
</div>
<div class="footer">
    <div>
        <p>&copy; 2022 e-learning. All rights reserved.</p>
        <p>
            University Of Constantine 2 Abdelhamid Mehri <br/>
            Nouvelle ville Ali Mendjeli BP : 67A, Constantine <br/>
            Algerie La Nouvelle Ville Ali Mendjeli, 25016
        </p>
    </div>
    <img src="../../resources/Logo-04-removebg-preview.png" alt="University Logo"/>
    <div>
        <p>Phone :031 77 50 27</p>
        <p>Contact webmaster@univ-constantine2</p>
    </div>
</div>

</body>
</html>