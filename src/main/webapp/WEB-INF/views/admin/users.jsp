<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Lista użytkowników</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Lista użytkowników</h2>

    <c:forEach items="${users}" var="user">
        <table class="tableData" style="width: 1200px">
            <tr>
                <th style="width: 10%">Imię</th>
                <th style="width: 10%">Nazwisko</th>
                <th style="width: 20%">Mail</th>
                <th style="width: 20%">Konto stworzono</th>
                <th style="width: 20%">Edytowano</th>
                <th>Akcje</th>
            </tr>
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
                <td>${user.created}</td>
                <td>${user.updated}</td>
                <td>
                    <form method="post" action="<c:url value="/admin/user/edit"/>">
                        <input type="hidden" name="userId" value="${user.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="edytuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/user/block"/>">
                        <input type="hidden" name="userId" value="${user.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="zablokuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/user/delete"/>">
                        <input type="hidden" name="userId" value="${user.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button-danger" value="usuń">
                    </form>
                </td>
            </tr>
        </table>
    </c:forEach>

    <c:if test="${blocked.size() > 0}">
        <h2>Lista zablokowanych użytkowników</h2>
    </c:if>

    <c:forEach items="${blocked}" var="blockedUser">
        <table class="tableData" style="width: 1200px">
            <tr>
                <th style="width: 10%">Imię</th>
                <th style="width: 10%">Nazwisko</th>
                <th style="width: 20%">Mail</th>
                <th style="width: 20%">Konto stworzono</th>
                <th style="width: 20%">Edytowano</th>
                <th>Akcje</th>
            </tr>
            <tr>
                <td>${blockedUser.firstName}</td>
                <td>${blockedUser.lastName}</td>
                <td>${blockedUser.email}</td>
                <td>${blockedUser.created}</td>
                <td>${blockedUser.updated}</td>
                <td>
                    <form method="post" action="<c:url value="/admin/user/edit"/>">
                        <input type="hidden" name="userId" value="${blockedUser.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="edytuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/user/unlock"/>">
                        <input type="hidden" name="blockedUserId" value="${blockedUser.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="odblokuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/user/delete"/>">
                        <input type="hidden" name="userId" value="${blockedUser.id}" />
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
