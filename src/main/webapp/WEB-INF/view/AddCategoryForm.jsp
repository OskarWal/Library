<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">

    <div>
        <form:form action="saveCategory" modelAttribute="category" method="POST" cssClass="form-row text-center" cssStyle="margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%)">

            <div class="fs-1 pt-5 text-center">Dodaj Kategorie</div>
            <div class="mb-3 mt-5">
                <label>Nazwa kategorii:</label>
                <form:input path="nazwa" class="form-control"/>
            </div>
            <div class="mb-5 ">
                <button type="submit" class="btn btn-danger w-50 ">Dodaj</button>
            </div>

        </form:form>
    </div>


</rapid:override>

<%@ include file="base.jsp" %>