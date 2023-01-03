<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>E-Learning</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css"/>
    <link rel="preconnect" href="https://fonts.googleapis.com"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@200;400;600&display=swap" rel="stylesheet"/>

    <link rel="icon" href="../../../resources/Logo-04-removebg-preview.png">
    <link rel='stylesheet' href='../../../css/header.css'>
    <link rel="stylesheet" href="../../../css/footer.css">
    <link rel="stylesheet" href="../../../css/sidebar.css">
    <link rel="stylesheet" href="../../../css/UsersStyle.css">
    <link rel="stylesheet" href="../../../css/FormStyle.css">

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
                    <img src="../../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./login">
                        Modules
                    </a>
                </li>
                <li>
                    <img src="../../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./addWeek">
                        Add Week
                    </a>
                </li>
                <li>
                    <img src="../../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./modifyWeek">
                        Modify Week
                    </a>
                </li>
                <li>
                    <img src="../../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./deleteWeek">
                        Delete Week
                    </a>
                </li>
            </ul>

        </div>

        <div class="page-content">
            <form class="form" action="updateContent" method="post">
                <ul>
                    <li>
                        <label for="update-subject">Subject</label>
                        <select name="subject-name" id="update-subject">
                            <jsp:useBean id="listOfSubjects" scope="session" type="java.util.List"/>
                            <c:if test="${listOfSubjects.size()!=0}">
                                <c:forEach var="i" begin="0" end="${listOfSubjects.size()-1}" step="1">
                                    <option value="<c:out value="${listOfSubjects[i].name}"/>">
                                        <c:out value="${listOfSubjects[i].name}"/>
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                        <span>Enter a Subject's Name [ Required ]</span>
                    </li>
                    <li>
                        <label for="update-week-start">Week Start</label>
                        <input type="date" name="week-start" id="update-week-start" required/>
                        <span>Start Of The Teaching Week [ Required ]</span>
                    </li>
                    <li>
                        <label for="update-week-end">Week End</label>
                        <input type="date" name="week-end" id="update-week-end" required/>
                        <span>End Of The Teaching Week [ Required ]</span>
                    </li>
                    <li>
                        <label for="update-type">Type</label>
                        <select name="type" id="update-type">
                            <option value="cour">Cour</option>
                            <option value="td">TD</option>
                            <option value="tp">TP</option>
                        </select>
                        <span>Select the Type of the Uploaded Content [ Required ]</span>
                    </li>
                    <li>
                        <label for="update-content-link">Content Link</label>
                        <input type="text" name="contentLink" id="update-content-link" required/>
                        <span>Enter Link to the Content [ Required ]</span>
                    </li>
                    <li>
                        <label for="update-new-link">New Link</label>
                        <input type="text" name="newLink" id="update-new-link"/>
                        <span>Enter New Link to the Content [ Optional ]</span>
                    </li>
                    <li>
                        <label for="update-new-name">New Name</label>
                        <input type="text" name="newName" id="update-new-name"/>
                        <span>Choose a New Name [ Optional ]</span>
                    </li>
                    <li>
                        <label for="update-description">Description</label>
                        <input type="text" name="description" id="update-description" maxlength="300"/>
                        <span>Change Description of The Week [ Optional ]</span>
                    </li>
                    <li>
                        <input type="submit" id="update-submit" value="Update"/>
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