<%-- 
    Document   : oauth2
    Created on : 2015-6-18, 13:53:50
    Author     : Heisaman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>信息完善</title>
    </head>
    <body>
        <h1>请填写您的主管姓名</h1>
        <form action="<c:url value='formSubmitted' />" method='GET'>
            <input type='text' name='name' value=''>
            <input name="submit" type="submit" value="submit" />
        </form>
    </body>
</html>
