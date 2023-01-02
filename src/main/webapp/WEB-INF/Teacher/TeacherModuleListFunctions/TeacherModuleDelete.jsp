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

    <link rel="icon" href="../../../resources/Logo-04-removebg-preview.png">
    <link rel="stylesheet" href="../../../css/footer.css"/>
    <link rel="stylesheet" href="../../../css/header.css"/>
    <link rel="stylesheet" href="../../../css/sidebar.css"/>
    <link rel="stylesheet" href="../../../css/UsersStyle.css"/>
    <link rel="stylesheet" href="../../../css/FormStyle.css"/>
</head>
<body>
<div class="containers">
    <div class="Navbar">
        <div class="logo">
            <a href="./login"><img src="../../../resources/Logo-04-removebg-preview.png" alt="logo"></a>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="./logout">
                        Log Out
                    </a>
                </li>
                <li>
                    <img src="../../../resources/teacher-24.png" alt="profile-pic grey icon">
                    <a href="./login">
                        <jsp:useBean id="teacher" scope="session" type="model.User"/>
                        <c:out value="${teacher.username}"/>
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
                    <img src="../../../resources/list-2-24.png" alt="list-users grey icon"/>
                    <a href="../TeacherMain.jsp">
                        Modules
                    </a>
                </li>

            </ul>
        </div>

        <div class="page-content">
            <form class="form" action="../TeacherModuleDisplay.jsp">
                <ul>
                    <li>
                        <label for="delete-typeModule">Module Type</label>
                        <input type="text" name="typeModule" id="delete-typeModule" maxlength="100"
                               pattern="Cour|TP|TD" required spellcheck='false'/>
                        <span>Type Of Module [ Cour/TP/TD ] [ Required ]</span>
                    </li>
                    <li>
                        <label for="delete-date">Date</label>
                        <input type="text" name="delete-date" id="delete-date" maxlength="100" required
                               spellcheck='false'/>
                        <span> Format [2022-12-31] [ Required ]</span>
                    </li>
                    <li>
                        <label for="delete-period">Period</label>
                        <input type="text" name="delete-period" id="delete-period" required spellcheck='false'/>
                        <span> Format [00:00-23:59] [ Required ]</span>
                    </li>
                    <li>
                        <input type="submit" id="delete-submit-module" value="Delete"/>
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
    <img src="../../../resources/Logo-04-removebg-preview.png" alt="University Logo">
    <div>
        <p>Phone :031 77 50 27</p>
        <p>Contact webmaster@univ-constantine2</p>
    </div>
</div>
</body>
</html>