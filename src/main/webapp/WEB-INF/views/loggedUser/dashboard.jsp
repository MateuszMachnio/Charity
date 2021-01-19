<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../constantParts/head.jsp"%>

    <title>Strona powitalna</title>
</head>
<body>
<header class="header--main-page">
    <%@include file="../constantParts/header.jsp"%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Witaj ${user}!<br/>
                Oddaj niechciane rzeczy w zaufane ręce.
            </h1>
        </div>
    </div>
</header>


<%@include file="../constantParts/footer.jsp"%>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
