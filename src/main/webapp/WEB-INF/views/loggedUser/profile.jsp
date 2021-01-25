<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Profil użytkownika</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Twoje dane</h2>

    <form action="<c:url value="/logged-user/edit"/>" style="width: 400px">
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
                <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
                <input type="submit" class="submit" value="Edytuj dane">
            </div>
        </div>
    </form>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>