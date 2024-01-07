<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">

    <div>
        <form:form action="saveAuthor" modelAttribute="author" method="POST" cssClass="form-row text-center" cssStyle="margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%)">

            <div class="fs-1 pt-5 text-center">Dodaj nowego autora</div>
            <div class="mb-3 mt-5">
                <label>Imię:</label>
                <form:input path="name" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Nazwisko:</label>
                <form:input path="surname" class="form-control"/>
            </div>
            <div class="mb-5 ">
                <a class="btn btn-danger w-25 float-start" href="${pageContext.request.contextPath}/authors/list "> Powrót </a>
                <button type="submit" class="btn btn-danger w-25 float-end">Dodaj</button>
            </div>

        </form:form>
    </div>


</rapid:override>

<%@ include file="base.jsp" %>