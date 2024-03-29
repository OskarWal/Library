<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<rapid:override name="content">
    <div style="display: flex; flex-direction: row; flex-wrap: wrap">
        <c:forEach var="book" items="${books}">
        <div class="card bg-secondary text-white "style=" transform: scale(0.8);width: 18rem" >
            <img src="${book.image_url}" class="card-img-top" alt="image">
            <div class="card-body">
                <h5 class="card-title">${book.title}</h5>
                <p class="card-text">Kategoria: ${book.category.name}</p>
                <p class="card-text">Cena: <fmt:formatNumber value = "${book.price}" type = "currency" currencySymbol="zł"/></p>
                <a class="mb-2 d-flex justify-content-between">
                <form:form action="${pageContext.request.contextPath}/books/details" method="get">
                    <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                    <button type="submit" class="btn btn-danger">Szczegóły</button>
                </form:form>

            <sec:authorize access="isAuthenticated()">
                <form:form action="${pageContext.request.contextPath}/addItemToCart" method="post">
                    <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                    <input type="hidden" id="quantity" name="quantity" value="1"/>
                    <button type="submit" class="btn btn-danger">Do koszyka</button>
                </form:form>
            </sec:authorize>
                </a>
                <div class="card-footer">
                    Wydawnictwo:
                    <p class="card-text">${book.publisher}</p>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>