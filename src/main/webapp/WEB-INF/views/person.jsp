<%--
    Document   : user
    Created on : 2015-6-9, 17:57:27
    Author     : Heisaman
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="true" %>
<html>
<head>
    <title>Person Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
        .red {color: red}
    </style>
</head>
<body>
<div align='center'>
<h1>Sumo 企业号</h1>
<h2>
    新增用户：
</h2>
<c:url var="addAction" value="/person/add" ></c:url>
<form:form action="${addAction}" commandName="person">
<table>
    <c:if test="${!empty person.name}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8"  disabled="true" />
            <form:hidden path="id" />
        </td>
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="userid">
                <spring:message text="UserId"/>
            </form:label>
        </td>
        <td>
            <form:input path="userid" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="position">
                <spring:message text="Position"/>
            </form:label>
        </td>
        <td>
            <form:input path="position" />
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="status">
                <spring:message text="Status"/>
            </form:label>
        </td>
        <td>
            <form:input path="status" />
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <c:if test="${!empty person.name}">
                <input type="submit"
                    value="<spring:message text="Edit Person"/>" />
            </c:if>
            <c:if test="${empty person.name}">
                <input type="submit"
                    value="<spring:message text="Add Person"/>" />
            </c:if>
        </td>
    </tr>
</table>
</form:form>
<br>
<h3>用户列表</h3>
<c:if test="${!empty listPersons}">
    <table class="tg">
    <tr>
        <th width="80">ID</th>
        <th width="120">姓名</th>
        <th width="120">性别</th>
        <th width="120">职位</th>
        <th width="120">部门</th>
        <th width="180">邮箱</th>
        <th width="200">微信号</th>
        <th width="60">状态</th>
        <th width="60">主管姓名</th>
        <th width="60">通过验证</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${listPersons}" var="person">
        <c:if test="${person.status == 2}">
            <tr class="red">
        </c:if>
        <c:if test="${person.status != 2}">
            <tr>
        </c:if>
            <td>${person.id}</td>
            <td>${person.name}</td>
            <td>${person.gender}</td>
            <td>${person.position}</td>
            <td></td>
            <td>${person.email}</td>
            <td>${person.weixinid}</td>
            <td>${person.status}</td>
            <td>${person.manager}</td>
            <td><a href="<c:url value='/approve/${person.userid}' />" >Approve</td>
            <td><a href="<c:url value='/remove/${person.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<c:url value="j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
                    document.getElementById("logoutForm").submit();
		}
	</script>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
            <h2>
                    Welcome : ${pageContext.request.userPrincipal.name} | <a
                            href="javascript:formSubmit()"> Logout</a>
            </h2>
	</c:if>
</div>
</body>
</html>

