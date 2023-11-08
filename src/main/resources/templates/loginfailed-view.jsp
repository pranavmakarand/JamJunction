<%-- 
    Document   : user-view
    Created on : Feb 12, 2023, 6:41:32 PM
    Author     : Shrusti
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User View</title>
    </head>
    <body>
        Hi, ${requestScope.user.name} doesnt exist in db please register !<br/>
        <a href="http://localhost:8080/register">Click here to go register</a>
    </body>
</html>