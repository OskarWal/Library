<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">
    <div>
        <form:form action="formadd" method="get">
            <button class="btn btn-danger float-start mb-3" type="submit">Dodaj Kategorie</button>
        </form:form>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Nazwa kategorii</th>
                <th></th>
            </tr>
            </thead>
            <tbody class="table-dark">
            <c:forEach var="category" items="${categories}">
                <tr>
                    <td>${category.name}</td>
                    <td>
                        <form:form action="formedit" method="get">
                            <input type="hidden" id="categoryId" name="categoryId" value="${category.id}"/>
                            <button class="btn btn-outline-danger btn-sm float-start" type="submit">Edytuj</button>
                        </form:form>
                        <form:form action="deleteCategory" method="post">
                            <input type="hidden" id="categoryId" name="categoryId" value="${category.id}"/>
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

