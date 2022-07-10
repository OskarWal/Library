<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<rapid:override name="content">
        <div class="fs-1 pt-5 text-center">Rejestracja Administratora</div>
        <c:if test="${not empty validator}"><p><c:out value="${validator}"/></p></c:if>
    <form class="form-row text-center" method="post" style="margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%)">
        <form:form action="registerAdmin" method="post" modelAttribute="user">
            <div class="mb-3 mt-3">
                <label for="username" class="form-label">Nazwa:</label>
                <form:input cssClass="form-control" path="username" id="username" class="form-control"/>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Hasło:</label>
                <form:password  path="password" id="password" class="form-control" cssClass="form-control"/>
            </div>
            <div>
                <label for="superPassword" class="form-label">Kod:</label>
                <input type="password" class="form-control" name="superPassword" id="superPassword"/>
            </div>
            <button type="submit" class="btn btn-danger my-sm-3 w-50 mt-3">Zarejestruj</button>
        </form:form>
    </form>
</rapid:override>

<%@ include file="base.jsp" %>