<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <!-- Bootstrap JavaScript (requires jQuery and Popper.js) -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-HOg5B1iPhnf/sL2H3BKaBpoFYfA9x3OdLtbGMPLa/bB3mZaLJoaqX8jz1HWSaPJp" crossorigin="anonymous"></script>
    <title >Adherent Collection</title>
    <style>
        body{
            display: flex;
            flex-direction: column;
            align-items: stretch;
        }
        table {
            margin: auto;
            padding: 0;
            width: 80%;
        }
        th, td {
            text-align: left;
            padding: 8px;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        button {
            padding: 8px 16px;
            margin: 10px 5px;
            border: none;
            border-radius: 4px;
            background-color: #4CAF50;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #3e8e41;
        }
        #add-book-btn {
            background-color: #2196F3;
            float: right;
        }
        label[for="search"] {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        /* Style the search input */
        input[type="text"]#search {
            width: 300px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        /* Style the submit button */
        #search_btn{
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            background-color: #008CBA;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        .menu {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            align-items: center;
            background-color: #333;
            color: #fff;
            padding: 10px;
        }

        /* Style for the menu items */
        .menu li {
            list-style: none;
            margin: 0 10px;
        }

        /* Style for the links in the menu items */
        .menu li a {
            text-decoration: none;
            color: #fff;
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        /* Hover style for the links in the menu items */
        .menu li a:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav" style="display: flex;justify-content: space-between">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="CrudLivre"  >Livre</a>
                </li>
                <c:if test="${admin != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="CrudAdherent" style="color: #333333">Adherent</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="CrudEmprunt">Emprunts</a>
                    </li>
                </c:if>
            </ul>
            <c:choose>
                <c:when test="${admin == null}">
                    <button type="submit" class="btn btn-outline-primary ms-auto" ><a style="color: black;text-decoration: none;" href="AdminLogin">Sign in</a></button>
                </c:when>
                <c:otherwise>
                    <button type="submit" class="btn btn-outline-primary ms-auto"><a style="color: black;text-decoration: none;" href="AdminLogin?logOut=logout">Sign out</a></button>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>
<div style="display: flex;flex-direction: column">
    <div style="width: 80%;margin: auto;display:flex;justify-content: space-between;">
        <c:if test="${noSuchAdh == null}">
            <div style="align-self: center">
                <form action="CrudAdherent" method="post">
                    <input type="text" id="search" name="s_cin" placeholder="Cin adherent...">
                    <input type="hidden" name="_method" value="search" required>
                    <button type="submit" id="search_btn">Go</button>
                </form>
            </div>
        </c:if>
        <c:if test="${admin != null}">
            <div >
                <button id="add-book-btn" style="background: #008CBA"><a style="color: #dddddd;text-decoration: none;" href="addAdherent" >Add Adherent</a></button>
            </div>
        </c:if>
    </div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Prenom</th>
                <th>Cin</th>
                <th>Nombre d'emprunts</th>
                <c:if test="${admin != null}">
                    <th>Modify</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:if test="${noSuchAdh == null}">
            </thead>
            <tbody>
            <c:forEach items="${adherents}" var="adh">
                <tr>
                    <td><c:out value="${adh.idClient}" /></td>
                    <td><c:out value="${adh.nom}" /></td>
                    <td><c:out value="${adh.prenom}" /></td>
                    <td><c:out value="${adh.cin}" /></td>
                    <td><c:out value="${adh.emprunts.size()}" /></td>
                    <c:if test="${admin != null}">
                        <td>
                            <form action="modifyAdherent" method="post">
                                <button type="submit" style="background: darkgreen;color: #ffffff">
                                    Modify
                                </button>
                                <input type="hidden" name="m_idClient" value="${adh.idClient}">
                                <input type="hidden" name="m_nom" value="${adh.nom}">
                                <input type="hidden" name="m_prenom" value="${adh.prenom}">
                                <input type="hidden" name="m_cin" value="${adh.cin}">
                            </form>
                        </td>
                        <td>
                            <form action="CrudAdherent" method="post">
                                <input type="hidden" name="_method" value="delete">
                                <input type="hidden" name="d_idClient" value="${adh.idClient}">
                                <button type="submit" style="background:lightcoral;color: #ffffff">Delete</button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
            </c:if>
        </table>
    <h1 style="color: red;align-self: center">${noSuchAdh}</h1>
    <h1 style="color: red;align-self: center">${delete_Adh_error}</h1>

</div>
</body>
</html>
