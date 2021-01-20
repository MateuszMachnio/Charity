<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Edycja admina</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Edycja admina</h2>

    <a class="return" style="margin-left: 510px" href="<c:url value="/admin/admins"/>">Powrót</a>

    <form:form modelAttribute="appUser" action="/admin/editing" method="post">

        <form:hidden path="id" />
        <form:hidden path="updated" />
        <form:hidden path="created" />
        <form:hidden path="role" />


        <div style="font-size: 18px; width: 750px">
            <p style="font-size: 18px"><b><form:label path="firstName">Imię: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="firstName" required="true" />
                <form:errors path="firstName" cssClass="formError" />
            </p>
            <p style="font-size: 18px"><b><form:label path="lastName">Nazwisko: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="lastName" required="true" />
                <form:errors path="lastName" cssClass="formError"/>
            </p>
            <p style="font-size: 18px"><b><form:label path="email">Email: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="email" required="true" />
                <form:errors path="email" cssClass="formError"/>
            </p>
            <p style="font-size: 18px"><b><form:label path="oldPassword">Stare hasło: </form:label></b>
                <br />
                <form:input type="password" cssStyle="width: 580px; margin-top: 5px" path="oldPassword" required="true" />
                <form:errors path="oldPassword" cssClass="formError"/>
            </p>
            <p style="font-size: 18px"><b><form:label path="password">Nowe hasło: </form:label></b>
                <br />
                <form:input type="password" cssStyle="width: 580px; margin-top: 5px" path="password" required="true" />
                <form:errors path="password" cssClass="formError"/>
            </p>
            <p style="font-size: 18px"><b><form:label path="repeatPassword">Powtórz nowe hasło: </form:label></b>
                <br />
                <form:input type="password" cssStyle="width: 580px; margin-top: 5px" path="repeatPassword" required="true" />
                <form:errors path="repeatPassword" cssClass="formError"/>
            </p>
            <div style="margin-bottom: 50px; text-align: center">
                <input type="submit" class="submit" value="Zapisz admina">
                <a class="return" href="<c:url value="/admin/admins"/>">Anuluj</a>
            </div>
        </div>

    </form:form>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
