<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">
    <div>
        <c:if test="${cart_items.size() > 0}">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                <tr>
                    <th>Tytuł Książki</th>
                    <th>Cena za szt.</th>
                    <th>Kategoria</th>
                    <th>Autorzy</th>
                    <th>Ilość</th>
                    <th>Koszt</th>
                    <th>Usuń</th>
                </tr>
                </thead>
                <tbody  class="table-dark">
                <c:forEach var="item" items="${cart_items}">
                    <tr>
                        <td>${item.ksiazki_id.nazwa}</td>
                        <td><fmt:formatNumber value = "${item.ksiazki_id.cena}" type = "currency" currencySymbol="zł"/></td>
                        <td>${item.ksiazki_id.kategoria.nazwa}</td>
                        <td><c:forEach var="autor" items="${item.ksiazki_id.autorzy}">
                            ${autor.imie} ${autor.nazwisko},
                        </c:forEach>
                        </td>
                        <td><form:form action="editCartItem" method="post">
                            <input type="hidden" id="bookId" name="bookId" value="${item.ksiazki_id.id}"/>
                            <input type="number" value="${item.quantity}" name="quantity" id="quantity" onchange="this.form.submit()" min="1" max="1000"/>
                        </form:form></td>

                        <td><fmt:formatNumber value = "${item.ksiazki_id.cena * item.quantity}" type = "currency" currencySymbol="zł"/></td>

                        <td><form:form action="deleteCartItem" method="post">
                            <input type="hidden" id="bookId" name="bookId" value="${item.ksiazki_id.id}"/>
                            <button class="btn btn-outline-danger btn-sm" type="submit">Usuń</button>
                        </form:form></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <form:form action="${pageContext.request.contextPath}/order/makeOrder" method="get">
                <button class="btn btn-danger" type="submit">Złóż zamówienie</button>
            </form:form>
        </c:if>
        <c:if test="${cart_items.size() == 0}">
            <div class="fs-1 pt-5 text-center">Koszyk jest pusty</div>
        </c:if>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>