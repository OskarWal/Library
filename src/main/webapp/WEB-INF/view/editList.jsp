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
                    <h5 class="card-title">${book.nazwa}</h5>
                    <p class="card-text">Kategoria: ${book.kategoria.nazwa}</p>
                    <p class="card-text">Cena: <fmt:formatNumber value = "${book.cena}" type = "currency" currencySymbol="zł"/></p>
                    <a class="mb-2 d-flex justify-content-between">
                    <form:form action="${pageContext.request.contextPath}/books/edit" method="get">
                        <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                        <button type="submit" class="btn btn-danger">Edytuj</button>
                    </form:form>

                    <form:form action="${pageContext.request.contextPath}/books/delete" method="post">
                         <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                        <button type="submit" class="btn btn-danger">Usuń</button>
                    </form:form>
                    </a>
                    <div class="card-footer">
                        Wydawnictwo:
                        <p class="card-text">${book.wydawnictwo}</p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>