<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Usuwanie użytkownika</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Czy na pewno chcesz usunąć tego użytkownika?</h2>

    <a class="return" style="margin-left: 510px" href="<c:url value="/admin/users"/>">Powrót</a>
    <form action="<c:url value="/admin/user/deleting"/>" style="width: 400px" method="post">
        <div style="font-size: 18px; width: 400px">
            <p style="font-size: 18px"><b>Imię: ${appUser.firstName}</b>
                <br />
            </p>
            <p style="font-size: 18px"><b>Nazwisko: ${appUser.lastName}</b>
                <br />
            </p>
            <p style="font-size: 18px"><b>Email: ${appUser.email}</b>
                <br />
            </p>

            <div style="margin-bottom: 50px; text-align: center">
                <input type="hidden" name="userId" value="${appUser.id}" />
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <input type="submit" class="delete" value="Usuń użytkownika">
                <a class="return" href="<c:url value="/admin/users"/>">Anuluj</a>
            </div>
        </div>
    </form>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
