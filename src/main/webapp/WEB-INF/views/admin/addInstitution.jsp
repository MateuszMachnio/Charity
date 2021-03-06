<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Dodawanie fundacji</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Dodawanie fundacji</h2>

    <a class="return" style="margin-left: 510px" href="<c:url value="/admin/institutions"/>">Powrót</a>

    <form:form modelAttribute="institution">

        <div style="font-size: 18px; width: 750px">
            <p style="font-size: 18px"><b><form:label path="name">Nazwa fundacji: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="name" required="true" />
                <form:errors path="name" cssClass="formError" /></p>
            <p style="font-size: 18px"><b><form:label path="description">Opis fundacji: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="description" required="true" />
                <form:errors path="description" cssClass="formError"/></p>
            <div style="margin-bottom: 50px; text-align: center">
                <input type="submit" class="submit" value="Zapisz fundację">
                <a class="return" href="<c:url value="/admin/institutions"/>">Anuluj</a>
            </div>
        </div>


    </form:form>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
