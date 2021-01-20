<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="container container--70">
    <ul class="nav--actions">

        <c:if test="${user == null}">
        <li><a href="<c:url value="/login"/>" class="btn btn--small btn--without-border">Zaloguj</a></li>
        <li><a href="<c:url value="/register"/>" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </c:if>

        <c:if test="${user != null}">
        <li class="logged-user">
            Witaj ${user}
            <ul class="dropdown">
                <li><a href="#">Profil</a></li>
                <li><a href="#">Moje zbiórki</a></li>
                <li><a href="#">Wyloguj</a></li>
            </ul>
        </li>
        </c:if>

    </ul>

    <ul>
        <c:if test="${user == null}">
        <li><a href="<c:url value="/"/>" class="btn btn--without-border active">Start</a></li>
        <li><a href="<c:url value="/#steps"/>" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="<c:url value="/#about-us"/>" class="btn btn--without-border">O nas</a></li>
        </c:if>
        <c:if test="${user == null || role=='ADMIN'}">
        <li><a href="<c:if test="${user == null}"><c:url value="/#help"/></c:if><c:if test="${role == 'ADMIN'}"><c:url value="/admin/institutions"/></c:if>" class="btn btn--without-border">Fundacje i organizacje</a></li>
        </c:if>
        <li><a href="<c:url value="/logged-user/donation/new"/>" class="btn btn--without-border">Przekaż dary</a></li>
        <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
