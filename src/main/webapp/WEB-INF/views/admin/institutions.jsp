<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Lista fundacji</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Lista wszystkich fundacji</h2>

    <a class="action" href="<c:url value="/admin/institution/add"/>">dodaj nową fundację</a>

    <c:forEach items="${institutions}" var="institution">
        <table class="tableData">
            <tr>
                <th style="width: 40%">Nazwa fundacji</th>
                <th style="width: 20%">Opis fundacji</th>
                <th>Akcje</th>
            </tr>
            <tr>
                <td>${institution.name}</td>
                <td>${institution.description}</td>
                <td>
                    <form method="post" action="<c:url value="/admin/institution/edit"/>">
                        <input type="hidden" name="institutionId" value="${institution.id}" />
                        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                        <input type="submit" class="button" value="edytuj">
                    </form>
                    <form method="post" action="<c:url value="/admin/institution/delete"/>">
                        <input type="hidden" name="institutionId" value="${institution.id}" />
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
