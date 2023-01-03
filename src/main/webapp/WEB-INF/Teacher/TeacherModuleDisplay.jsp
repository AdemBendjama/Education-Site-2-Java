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
    <link rel="stylesheet" href="../../css/header.css"/>
    <link rel="stylesheet" href="../../css/footer.css"/>
    <link rel="stylesheet" href="../../css/sidebar.css"/>
    <link rel="stylesheet" href="../../css/UsersStyle.css"/>
</head>
<body>
<div class="containers">
    <div class="Navbar">
        <div class="logo">
            <a href="./login"><img src="../../resources/Logo-04-removebg-preview.png" alt="logo"></a>
        </div>
        <nav>
            <ul>
                <li>
                    <a href="./logout">
                        Log Out
                    </a>
                </li>
                <li>
                    <img src="../../resources/teacher-24.png" alt="profile-pic grey icon">
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
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon"/>
                    <a href="./login">
                        Modules
                    </a>
                </li>
                <li>
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./addWeek">
                        Add Week
                    </a>
                </li>
                <li>
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./modifyWeek">
                        Modify Week
                    </a>
                </li>
                <li>
                    <img src="../../resources/list-2-24.png" alt="list-users grey icon">
                    <a href="./deleteWeek">
                        Delete Week
                    </a>
                </li>
            </ul>
        </div>

        <div class="page-content student-module">
            <jsp:useBean id="subjectName" scope="session" type="java.lang.String"/>
            <h1><c:out value="${subjectName}"/></h1>

            <jsp:useBean id="weeks" scope="session" type="java.util.HashMap"/>

            <c:if test="${weeks.size()!=0}">
                <c:forEach var="week" items="${weeks}">

                    <h3><c:out value="${week.value}"/></h3>
                    <table class="table table-dark">

                        <thead>
                        <tr>
                            <td>Lessons</td>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="cours" scope="session" type="java.util.HashMap"/>
                        <c:if test="${cours.get(week.key).size() != 0}">
                            <c:forEach var="cour" items="${cours.get(week.key)}">
                                <tr>
                                    <td>
                                        <a href="<c:out value="${cour.key}"/>" target="_blank">
                                            <c:out value="${cour.value}"/></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>

                        <thead>
                        <tr>
                            <td>TDs</td>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="tds" scope="session" type="java.util.HashMap"/>
                        <c:if test="${tds.get(week.key).size() != 0}">
                            <c:forEach var="td" items="${tds.get(week.key)}">
                                <tr>
                                    <td>
                                        <a href="<c:out value="${td.key}"/>" target="_blank">
                                            <c:out value="${td.value}"/></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>

                        <thead>
                        <tr>
                            <td>TPs</td>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="tps" scope="session" type="java.util.HashMap"/>
                        <c:if test="${tps.get(week.key).size() != 0}">
                            <c:forEach var="tp" items="${tps.get(week.key)}">
                                <tr>
                                    <td>
                                        <a href="<c:out value="${tp.key}"/>" target="_blank">
                                            <c:out value="${tp.value}"/></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>

                    </table>
                </c:forEach>
            </c:if>
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
    <img src="../../resources/Logo-04-removebg-preview.png" alt="University Logo">
    <div>
        <p>Phone :031 77 50 27</p>
        <p>Contact webmaster@univ-constantine2</p>
    </div>
</div>

</body>
</html>