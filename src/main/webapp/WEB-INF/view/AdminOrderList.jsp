<%@ taglib uri="http://www.rapid-framework.org.cn/rapid" prefix="rapid" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<rapid:override name="content">
    <div>
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
            <tr>
                <th>Id</th>
                <th>Użytkownik</th>
                <th>Status</th>
                <th>Szczegóły</th>
                <th>Zmień status</th>
            </tr>
            </thead>
            <tbody  class="table-dark">
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.user}</td>
                    <td>${order.status}</td>
                    <td><form:form action="showOrderItems" method="get">
                        <input type="hidden" id="orderId" name="orderId" value="${order.id}"/>
                        <button class="btn btn-danger btn-sm" type="submit">Szczegóły</button>
                    </form:form></td>
                    <td><form:form action="changeStatus" method="post">
                        <input type="hidden" id="orderId" name="orderId" value="${order.id}"/>
                        <select class="form-select-sm" name="status" id="status" onchange="this.form.submit()">
                            <option value="${order.status}" disabled selected>${order.status}</option>
                            <option value="Nowe">Nowe</option>
                            <option value="W trakcie realizacji">W trakcie realizacji</option>
                            <option value="Zrealizowane">Zrealizowane</option>
                        </select>
                    </form:form></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
</rapid:override>

<%@ include file="base.jsp" %>