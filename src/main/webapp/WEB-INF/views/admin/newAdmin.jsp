<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Dodawanie admina</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Dodawanie admina</h2>

    <a class="return" style="margin-left: 510px" href="<c:url value="/admin/admins"/>">Powrót</a>

    <form:form modelAttribute="admin">

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
            <p style="font-size: 18px"><b><form:label path="password">Hasło: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="password" required="true" />
                <form:errors path="password" cssClass="formError"/>
            </p>
            <p style="font-size: 18px"><b><form:label path="repeatPassword">Powtórz hasło: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="repeatPassword" required="true" />
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
