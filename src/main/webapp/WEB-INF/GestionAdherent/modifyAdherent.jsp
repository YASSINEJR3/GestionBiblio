<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 06/03/2023
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modify Adherent</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-top: 50px;
        }

        form {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            margin: 0 auto;
            max-width: 500px;
            box-shadow: 0px 0px 5px #ccc;
        }

        label {
            display: block;
            font-size: 18px;
            color: #333;
            margin-bottom: 5px;
        }

        input[type="text"] {
            padding: 10px;
            border-radius: 5px;
            border: none;
            box-shadow: 0px 0px 5px #ccc;
            width: 100%;
            margin-bottom: 20px;
            font-size: 16px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
        }

        input[type="submit"]:hover {
            background-color: #3e8e41;
        }
    </style>
</head>
<body>
    <h1>Modify the Adherent</h1>
    <%  String idClient = request.getParameter("m_idClient");
        String nom = request.getParameter("m_nom");
        String prenom = request.getParameter("m_prenom");
        String cin = request.getParameter("m_cin");
    %>
    <form action="CrudAdherent" method="post">
        <label for="idClient">id client:</label>
        <input type="text" id="idClient" name="m_idClient"  value="<%= idClient %>" readonly required>
        <label for="nom">Nom:</label>
        <input type="text" id="nom" name="m_nom" value="<%= nom %>" required>
        <label for="prenom">Prenom:</label>
        <input type="text" id="prenom" name="m_prenom" value="<%= prenom %>" required>
        <label for="cin">Cin:</label>
        <input type="text" id="cin" name="m_cin" value="<%=cin %>" readonly required>
        <input type="submit" value="modify">
        <input type="hidden" name="_method" value="put">
    </form>
</body>
</html>
