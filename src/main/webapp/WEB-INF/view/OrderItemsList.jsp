<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">
    <div>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Ksiazka</th>
                <th>Cena za szt.</th>
                <th>Ilosc</th>
                <th>Koszt</th>
            </tr>
            </thead>
            <tbody  class="table-dark">
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><a class="text-danger" href="${pageContext.request.contextPath}/books/details?bookId=${item.book.id}">${item.book.title}</a></td>
                    <td><fmt:formatNumber value = "${item.book.price}" type = "currency" currencySymbol="zł"/></td>
                    <td>${item.quantity}</td>
                    <td><fmt:formatNumber value = "${item.book.price * item.quantity}" type = "currency" currencySymbol="zł"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</rapid:override>

<%@ include file="base.jsp" %>