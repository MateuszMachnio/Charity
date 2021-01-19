<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="constantParts/head.jsp"%>

    <title>Rejestracja</title>
</head>
<body>
<header>
    <%@include file="constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Logowanie</h2>
    <form action="<c:url value="/login"/>" method="post">

        <c:if test="${param.error != null}">
            <div class="alert-danger">Nieprawidłowy login lub hasło.</div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="alert-success">Zostałeś pomyślnie wylogowany.</div>
        </c:if>

        <div class="form-group">
            <input type="email" name="email" placeholder="Email" required />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" required />
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zaloguj się</button>
            <a href="<c:url value="/register"/>" class="btn btn--without-border">Załóż konto</a>
        </div>
    </form>

    <c:if test="${param.error != null}">
        <div>Jeśli nie masz jeszcze konta, proszę <a href="<c:url value="/register"/>">zarejestruj się</a>.</div>
    </c:if>

</section>

<%@include file="constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
