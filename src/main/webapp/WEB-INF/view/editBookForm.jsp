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
                <form:input path="title" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Wydawnictwo:</label>
                <form:input path="publisher" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>cena:</label>
                <form:input path="price" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Kategoria:</label>
                <form:select path="category.id" class="form-control">

                    <c:forEach var="category" items="${categories}">
                        <form:option value="${category.id}" label="${category.name}"/>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label>Autorzy:</label>
                <select class="form-control" name="authors_id" multiple>
                    <c:forEach items="${authors}" var="author">
                        <c:if test="${book_authors.contains(author.id)}">
                            <option selected value="${author.id}">${author.name} ${author.surname}</option>
                        </c:if>
                        <c:if test="${!book_authors.contains(author.id)}">
                            <option value="${author.id}">${author.name} ${author.surname}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label>Link do okładki:</label>
                <form:input path="image_url" class="form-control"/>
            </div>
            <div class="mb-3">
                <label>Opis:</label>
                <form:textarea path="description" class="form-control"/>
            </div>
            <button type="submit" class="btn btn-danger my-sm-3 w-50 mt-3">Edytuj</button>
        </form:form>
    </div>


</rapid:override>

<%@ include file="base.jsp" %>