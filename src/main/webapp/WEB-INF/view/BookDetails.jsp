<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<rapid:override name="content">
    <div class="container bg-dark text-white">
        <div class="card bg-secondary text-white">
            <div class="card-body">

                <div class="row">
                    <div class="col m-auto">
                        <div class="white-box text-center"><img src="${book.image_url}" width="430" height="600" class="img-responsive h-50"></div>
                    </div>
                    <div class="col m-auto">
                        <h1 class="card-title text-center">${book.nazwa}</h1>
                        <h4 class="box-title mt-5 m-auto">Opis:</h4>
                        <p>${book.opis}</p>
                        <h4 class="mt-5">Cena: <fmt:formatNumber value = "${book.cena}" type = "currency" currencySymbol="zł"/></h4>
                        <label for="quantity">Ilość: </label>
                        <sec:authorize access="isAuthenticated()">
                            <form:form action="${pageContext.request.contextPath}/addItemToCart" method="post">
                                <input type="hidden" id="bookId" name="bookId" value="${book.id}"/>
                                <input type="number" id="quantity" name="quantity" value="1"  min="1" max="1000"/>
                                <button type="submit" class="btn btn-danger">Do koszyka</button>
                            </form:form>
                        </sec:authorize>
                        <sec:authorize access="isAnonymous()">
                            <input type="number" id="quantity" name="quantity" value="1"  min="1" max="1000"/>
                            <button disabled="true" class="btn btn-danger">Zaloguj się aby dodać do koszyka</button>
                        </sec:authorize>

                    </div>
                    <div class="col-lg-12 col-md-12 col-sm-12">
                        <h3 class="box-title mt-5">Szczegóły</h3>
                        <div class="table-responsive">
                            <table class="table table-striped table-product ">
                                <tbody>
                                <tr>
                                    <td width="390">Kategoria</td>
                                    <td>${book.kategoria.nazwa}</td>
                                </tr>
                                <tr>
                                    <td>Wydawnictwo</td>
                                    <td>${book.wydawnictwo}</td>
                                </tr>
                                <tr>
                                    <td>Autorzy</td>
                                    <td>
                                        <c:forEach var="autor" items="${book.autorzy}">
                                            ${autor.imie} ${autor.nazwisko},
                                        </c:forEach>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>