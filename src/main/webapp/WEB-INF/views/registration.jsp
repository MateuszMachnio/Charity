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
    <h2>Rejestracja</h2>
    <form:form modelAttribute="appUser">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię" required="true" />
            <form:errors path="firstName" class="formError" element="div" />
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko" required="true" />
            <form:errors path="lastName" class="formError" element="div" />
        </div>
        <div class="form-group">
            <form:input path="email" type="email" placeholder="Email" required="true" pattern="[_a-zA-Z0-9-]+(\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.([a-zA-Z]{2,}){1}" title="Podaj prawidłowy adres email." />
            <form:errors path="email" class="formError" element="div" />
        </div>
        <div class="form-group">
            <form:input path="password" type="password" placeholder="Hasło" required="true" />
            <form:errors path="password" class="formError" element="div" />
        </div>
        <div class="form-group">
            <form:input path="repeatPassword" type="password" placeholder="Powtórz hasło" required="true" />
            <form:errors path="repeatPassword" class="formError" element="div" />
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Załóż konto</button>
            <a href="<c:url value="/login"/>" class="btn btn--without-border">Zaloguj się</a>
        </div>
    </form:form>
</section>

<%@include file="constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
