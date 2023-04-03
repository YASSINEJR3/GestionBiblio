<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <title >My Book Collection</title>
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
                        <a class="nav-link" href="CrudLivre" style="color: #333333" >Livre</a>
                    </li>
                    <c:if test="${admin != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="CrudAdherent">Adherent</a>
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
                        <ul class="navbar-nav" style="margin-left: auto;">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <span style="color: #333333" >admin</span>
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" style="color: #333333" href="AdminLogin?logOut=logout" >Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </nav>
    <div style="display: flex;flex-direction: column">
        <div style="width: 80%;margin: auto;display:flex;justify-content: space-between;">
            <c:if test="${noSuchBook == null}">
                <div style="align-self: center">
                    <form action="CrudLivre" method="post">
                        <input type="text" id="search" name="s_auteur" placeholder="Auteur...">
                        <input type="hidden" name="_method" value="search" required>
                        <button type="submit" id="search_btn">Go</button>
                    </form>
                </div>
            </c:if>
            <c:if test="${admin != null}">
                <div >
                    <button id="add-book-btn" style="background: #008CBA"><a style="color: #dddddd;text-decoration: none;" href="addlivre" >Add Book</a></button>
                </div>
            </c:if>
        </div>
            <table>
                <thead>
                <tr>
                    <th>ISBN</th>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Nombre d'exemplaire disponible</th>
                    <c:if test="${admin != null}">
                        <th>Modify</th>
                        <th>Delete</th>
                    </c:if>
                </tr>
                </thead>
                <c:if test="${noSuchBook == null}">
                <tbody>
                <c:forEach items="${livres}" var="l">
                    <tr>
                        <td><c:out value="${l.isbn}" /></td>
                        <td><c:out value="${l.titre}" /></td>
                        <td><c:out value="${l.auteur}" /></td>
                        <td><c:out value="${l.nbrexemp}" /></td>
                        <c:if test="${admin != null}">
                            <td>
                                <form action="modifyLivre" method="post">
                                    <button type="submit" style="background: darkgreen;color: #ffffff">
                                        Modify
                                    </button>
                                    <input type="hidden" name="m_isbn" value="${l.isbn}">
                                    <input type="hidden" name="m_title" value="${l.titre}">
                                    <input type="hidden" name="m_auteur" value="${l.auteur}">
                                    <input type="hidden" name="m_nbrexemp" value="${l.nbrexemp}">
                                </form>
                            </td>
                            <td>
                                <form action="CrudLivre" method="post">
                                    <input type="hidden" name="_method" value="delete">
                                    <input type="hidden" name="d_isbn" value="${l.isbn}">
                                    <button type="submit" style="background:lightcoral;color: #ffffff">Delete</button>
                                </form>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
                </c:if>
            </table>
        <h1 style="color: red;align-self: center">${noSuchBook}</h1>
    </div>
</body>
</html>
