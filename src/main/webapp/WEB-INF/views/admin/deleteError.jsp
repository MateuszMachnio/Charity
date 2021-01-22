<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Błąd usuwania admina</title>
</head>
<body>
<header class="header--form-page">
    <%@include file="../constantParts/header.jsp"%>

    <div class="slogan container container--90">
        <h2>
            Adminitrator nie może usunąć swojego konta.
        </h2>
    </div>
</header>

<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
