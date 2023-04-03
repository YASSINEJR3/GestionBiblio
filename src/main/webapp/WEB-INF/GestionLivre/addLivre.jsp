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
<h1>Add New Book</h1>
<form action="CrudLivre" method="post">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required>
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>
    <label for="auteur">Author:</label>
    <input type="text" id="auteur" name="auteur" required>
    <label for="nbrexemp">Nombre d'exemplaire:</label>
    <input type="number" id="nbrexemp" name="nbrexemp" required>
    <input type="submit" value="Add">
</form>
<jsp:useBean id="add_error" class="java.lang.String" scope="request" />
<h1 style="color: red">${add_error}</h1>
</body>
</html>