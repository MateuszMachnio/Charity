<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Potwierdź usunięcie fundacji</title>
</head>
<body>
<header>
    <%@include file="../constantParts/header.jsp"%>
</header>

<section class="login-page">
    <h2>Czy na pewno chcesz usunąć tą fundację?</h2>

    <a class="return" style="margin-left: 510px" href="<c:url value="/admin/institutions"/>">Powrót</a>

    <form:form modelAttribute="institution" action="/admin/institution/deleting" method="post">

        <form:hidden path="id" />

        <div style="font-size: 18px; width: 750px">
            <p style="font-size: 18px"><b><form:label path="name">Nazwa fundacji: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="name" readonly="true" />
            </p>
            <p style="font-size: 18px"><b><form:label cssStyle="text-align: start" path="description">Opis fundacji: </form:label></b>
                <br />
                <form:input cssStyle="width: 580px; margin-top: 5px" path="description" readonly="true" />
            </p>
            <div style="margin-bottom: 50px; text-align: center">
                <input type="submit" class="delete" value="Usuń fundację">
                <a class="return" href="<c:url value="/admin/institutions"/>">Anuluj</a>
            </div>
        </div>


    </form:form>

</section>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
