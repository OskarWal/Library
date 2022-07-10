<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="p-3 mb-2 bg-dark text-white">
<div class="row">
    <nav class="navbar navbar-dark bg-dark">
        <img src="https://i.imgur.com/KejfKMV.png" width="80" height="80" class="d-inline-block align-top" alt="">
        <a class="navbar-brand me-auto mb-2 mb-lg-0" href="${pageContext.request.contextPath}/">Book Me</a>

        <form class="d-flex" method="GET" action="${pageContext.request.contextPath}/search">
            <input class="form-control me-1" type="searchParam" placeholder="Szukaj" aria-label="Szukaj" name="searchParam">
            <button class="btn btn-outline-danger me-5" type="submit">Szukaj</button>
        </form>
        <sec:authorize access="isAuthenticated()">
            <li class="nav-item dropdown" style="list-style-type: none;">
                <a class="nav-link dropdown-toggle text-danger" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Witaj, <sec:authentication property="name"/></a>
                <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
                    <sec:authorize access="hasRole('ADMIN')">
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/registerAdmin">Stwórz admina</a></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/books/formadd">Dodaj książkę</a></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/editList">Edytuj książki</a></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/authors/list">Autorzy</a></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/categories/list">Kategorie</a></li>
                        <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/order/showAll">Zamówienia</a></li>

                        <li><hr class="dropdown-divider"></li>
                    </sec:authorize>
                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/cart">Koszyk</a></li>
                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/order/myOrders">Moje Zamówienia</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/logout">Wyloguj</a></li>
                </ul>
            </li>
        </sec:authorize>

        <sec:authorize access="!isAuthenticated()">
        <li class="nav-item dropdown" style="list-style-type: none;">
            <a class="nav-link dropdown-toggle text-danger" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">Logowanie</a>
            <ul class="dropdown-menu bg-dark" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/login">Zaloguj</a></li>
                <li><a class="dropdown-item text-danger" href="${pageContext.request.contextPath}/register">Zarejestruj</a></li>
            </ul>
        </li>
        </sec:authorize>

    </nav>
</div>

<div role="main" class="container">
    <div class="row">
        <div class="col">
            <c:if test="${goodMessages != null}">
                <c:forEach var="goodMessage" items="${goodMessages}">
                    <div class="alert alert-success">
                        ${goodMessage}
                    </div>
                </c:forEach>
                ${goodMessages.clear()}
            </c:if>
            <c:if test="${badMessages != null}">
                <c:forEach var="badMessage" items="${badMessages}">
                    <div class="alert alert-warning">
                            ${badMessage}
                    </div>
                </c:forEach>
                ${badMessages.clear()}
            </c:if>

        </div>
    </div>


    <rapid:block name="content">
    </rapid:block>
</div>


</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</html>

