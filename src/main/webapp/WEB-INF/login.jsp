<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 08/03/2023
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
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

        input {
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
         <h1>Sign in</h1>
        <form action="<%= request.getContextPath() %>/AdminLogin" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <input type="submit" value="Add">
        </form>
        <jsp:useBean id="login_error" class="java.lang.String" scope="request" />
        <h1 style="color: red">${login_error}</h1>
    </body>
</html>