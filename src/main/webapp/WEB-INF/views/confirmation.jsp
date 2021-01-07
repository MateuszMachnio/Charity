<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="constantParts/head.jsp"%>

    <title>Potwierdzenie przekazania daru</title>
</head>
<body>
<header class="header--form-page">
    <%@include file="constantParts/loggedUserHeader.jsp"%>

    <div class="slogan container container--90">
        <h2>
            Dziękujemy za przesłanie formularza. Na maila prześlemy szczegóły odbioru.
        </h2>
    </div>
</header>

<%@include file="constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
