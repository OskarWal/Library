<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">

    <div>
        <form:form action="updateBook" modelAttribute="book" method="POST" cssClass="form-row text-center" cssStyle="margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    margin-right: -50%;
    transform: translate(-50%, -50%)">
            <div class="fs-1 pt-5 text-center">Edytuj istniejącą książkę</div>
           <input hidden name="bookId" id="bookId" value="${book.id}"/>
            <div class="mb-3 mt-5">
                <label>Tytuł:</label>
                <form:input path="nazwa" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Wydawnictwo:</label>
                <form:input path="wydawnictwo" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>cena:</label>
                <form:input path="cena" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Kategoria:</label>
                <form:select path="kategoria.id" class="form-control">

                    <c:forEach var="category" items="${categories}">
                        <form:option value="${category.id}" label="${category.nazwa}"/>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label>Autorzy:</label>
                <select class="form-control" name="authors_id" multiple="true">
                    <c:forEach items="${authors}" var="author">
                        <option value="${author.id}">${author.imie} ${author.nazwisko}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label>Link do okładki:</label>
                <form:input path="image_url" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Opis:</label>
                <form:textarea path="opis" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-danger my-sm-3 w-50 mt-3">Dodaj</button>
        </form:form>
    </div>


</rapid:override>

<%@ include file="base.jsp" %>