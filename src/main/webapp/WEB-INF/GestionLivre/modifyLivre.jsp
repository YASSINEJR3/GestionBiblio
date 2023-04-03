<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Add New Book</title>
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
        input[type="number"] {
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
<h1>Modify the Book</h1>
<% String isbn = request.getParameter("m_isbn");
   String title = request.getParameter("m_title");
   String auteur = request.getParameter("m_auteur");
   String nbrexemp = request.getParameter("m_nbrexemp");
%>
<form action="CrudLivre" method="post">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="m_isbn"  value="<%= isbn %>" readonly required>
    <label for="title">Title:</label>
    <input type="text" id="title" name="m_titre" value="<%= title %>" required>
    <label for="auteur">Author:</label>
    <input type="text" id="auteur" name="m_auteur" value="<%= auteur %>" required>
    <label for="nbrexemp">Nombre exemplaire:</label>
    <input type="number" id="nbrexemp" name="m_nbrexemp" value="<%= nbrexemp %>" required>
    <input type="submit" value="modify">
    <input type="hidden" name="_method" value="put">
</form>
</body>
</html>