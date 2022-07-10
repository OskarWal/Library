
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<rapid:override name="content">
    <div class="fs-1 pt-5 text-center">Logowanie</div>
    <c:if test="${not empty validator}"><p><c:out value="${validator}"/></p></c:if>
    <form class="form-row text-center" method="post" style="margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%)">
        <form:form method="post">
            <div class="mb-3">
                <td><label for="username">Nazwa:</label></td>
                <td><input type="text" id="username" name="username" class="form-control"/></td>
            </div>
            <div class="mb-3">
                <td><label for="password">Hasło:</label></td>
                <td><input type="password" id="password" name="password" class="form-control"/></td>
            </div>
            <button type="submit" class="btn btn-danger my-sm-3 w-50 mt-3">Zaloguj</button>
        </form:form>
    </form>
</rapid:override>

<%@ include file="base.jsp" %>