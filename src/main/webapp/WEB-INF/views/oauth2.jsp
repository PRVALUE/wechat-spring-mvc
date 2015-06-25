<%-- 
    Document   : oauth2
    Created on : 2015-6-18, 13:53:50
    Author     : Heisaman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>信息完善</title>
        <style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}
</style>
    </head>
    <body>
        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${not empty name}">
            <div class="msg">您好${name}，请填写您的主管姓名</div>
            <form action="<c:url value='formSubmitted' />" method='GET'>
                <input type='text' name='manager' value=''>
                <input name="submit" type="submit" value="submit" />
            </form>
        </c:if>
    </body>
</html>
