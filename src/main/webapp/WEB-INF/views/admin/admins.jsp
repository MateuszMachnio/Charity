<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Lista administratorów</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Lista administratorów</h2>

    <a class="action" href="<c:url value="/admin/add"/>">dodaj nowego administratora</a>

    <c:forEach items="${admins}" var="admin">
        <table class="tableData" style="width: 1200px">
            <tr>
                <th style="width: 10%">Imię</th>
                <th style="width: 15%">Nazwisko</th>
                <th style="width: 20%">Mail</th>
                <th style="width: 20%">Konto stworzono</th>
                <th style="width: 20%">Edytowano</th>
                <th>Akcje</th>
            </tr>
            <tr>
                <td>${admin.firstName}</td>
                <td>${admin.lastName}</td>
                <td>${admin.email}</td>
                <td>${admin.created}</td>
                <td>${admin.updated}</td>
                <td>
                    <form method="post" action="<c:url value="/admin/edit"/>">
                        <input type="hidden" name="adminId" value="${admin.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="edytuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/delete"/>">
                        <input type="hidden" name="adminId" value="${admin.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button-danger" value="usuń">
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
