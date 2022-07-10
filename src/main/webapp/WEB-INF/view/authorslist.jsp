<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">
    <div>
        <form:form action="formadd" method="get">
            <button class="btn btn-danger float-start mb-3" type="submit">Dodaj Autora</button>
        </form:form>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="table-dark">
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.imie}</td>
                    <td>${author.nazwisko}</td>

                    <td>
                        <form:form action="formedit" method="get">
                            <input type="hidden" id="authorId" name="authorId" value="${author.id}"/>
                            <button class="btn btn-outline-danger btn-sm float-start" type="submit">Edytuj</button>
                        </form:form>
                        <form:form action="deleteAuthor" method="post">
                            <input type="hidden" id="authorId" name="authorId" value="${author.id}"/>
                            <button class="btn btn-outline-danger btn-sm float-end" type="submit">Usuń</button>
                        </form:form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</rapid:override>

<%@ include file="base.jsp" %>
