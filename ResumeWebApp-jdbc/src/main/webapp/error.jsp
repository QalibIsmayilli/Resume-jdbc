<%@ page import="java.io.InvalidObjectException" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Why are you here?</title>
</head>
<body>
<center>
    <h1><%=String.valueOf(request.getAttribute("errorMsg"))%></h1>
</center>

</body>
</html>
