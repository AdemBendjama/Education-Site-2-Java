<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>Admin</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap" rel="stylesheet"/>

    <link rel="icon" href="../../resources/Logo-04-removebg-preview.png">
    <link rel='stylesheet' href='../../css/footer.css'>
    <link rel="stylesheet" href="../../css/header.css">
    <link rel="stylesheet" href="../../css/sidebar.css">
    <link rel="stylesheet" href="../../css/UsersStyle.css">


</head>
<body>
<div class="containers">
    <div class="Navbar">
        <div class="logo">
            <a href="AdminMain.jsp"><img src="../../resources/Logo-04-removebg-preview.png" alt="logo"></a>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="../../index.jsp">
                        Log Out
                    </a>
                </li>
                <li>
                    <img src="../../resources/admin-24.png" alt="profile-pic grey icon">
                    <a href="AdminMain.jsp">
                        Web Master
                    </a>
                </li>
            </ul>
        </nav>
    </div>


    <div class="center-content">
        <div id="sidebar">
            <header>
                <a href="#">Menu</a>
            </header>
            <ul class="nav">
                <li>
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="AdminMain.jsp">
                        List Users
                    </a>
                </li>
                <li>
                    <img src="../../resources/change-user-24.png" alt="add-user grey icon">
                    <a href="AdminUpdateUser.jsp">
                        Update a User
                    </a>
                </li>
                <li>
                    <img src="../../resources/remove-user-24.png" alt="add-user grey icon">
                    <a href="AdminDeleteUser.jsp">
                        Delete a User
                    </a>
                </li>
                <li>
                    <img src="../../resources/add-user-24.png" alt="add-user grey icon">
                    <a href="AdminAddUser.jsp">
                        Add a User
                    </a>
                </li>

            </ul>
        </div>

        <div class="page-content">
            <div class="options">
                <label for="sortButton"></label><select name="" id="sortButton" class="button">
                <option value="name" selected>Name</option>
                <option value="email">Email</option>
                <option value="type">Type</option>
            </select>
            </div>
            <table class="table table-dark">
                <thead>
                <tr>
                    <td>Name</td>
                    <td>Email</td>
                    <td>Password</td>
                    <td>Type</td>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>

        </div>

    </div>
</div>
<div class="footer">
    <div>
        <p>© 2022 e-learning. All rights reserved.</p>
        <p>Université Constantine 2 Abdelhamid Mehri <br/> Nouvelle ville Ali Mendjeli BP : 67A, Constantine
            <br/> Algérie La Nouvelle Ville Ali Mendjeli, 25016
        </p>
    </div>
    <img src="../../resources/Logo-04-removebg-preview.png" alt="University Logo">
    <div>
        <p>Phone :031 77 50 27</p>
        <p>Contact webmaster@univ-constantine2</p>
    </div>
</div>
</body>
</html>