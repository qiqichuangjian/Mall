<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/3/14
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="add.jsp"><button style="width: 300px;height: 50px">添加</button></a>
<table border="1px" cellspacing="0">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>city</td>
        <td>price</td>
        <td>number</td>
        <td>picture</td>
        <td>action</td>
    </tr>
    <%--items="${list}来源于ItemsServlet的req.setAttribute的"list"--%>
    <c:forEach var="items" items="${pageBean.list}" varStatus="i">
        <tr>
            <td> ${i.index+1}</td>
            <td> ${items.name}</td>
            <td> ${items.city}</td>
            <td> ${items.price}</td>
            <td> ${items.number}</td>
            <td><img src="image/${items.picture}" width="100px" height="100px" alt="${items.name}"/></td>
            <td>
                <a href="ItemsServlet?method=delete&id=${items.id}">delete</a>
                <a href="ItemsServlet?method=queryOne&id=${items.id}">update</a>
            </td>
        </tr>
    </c:forEach>
    <%--<a href="add.jsp">add</a>--%>
    <tr>
        <td colspan="8">
            <c:if test="${pageBean.pageIndex>1}">
                <a href="ItemsServlet?method=queryPage&pageIndex=${pageBean.pageIndex-1}">上一页</a>
            </c:if>
                <c:choose>
                    <c:when test="${pageBean.pages<=10}">
                        <%--如果不够10页 ,开始页 1 ,结束页pages--%>
                        <c:set var="start" value="1"></c:set>
                        <c:set var="end" value="${pageBean.pages}"></c:set>
                    </c:when>
                    <c:otherwise>
                        <c:set var="start" value="${pageBean.pageIndex-5}"></c:set>
                        <c:set var="end" value="${pageBean.pageIndex+4}"></c:set>
                        <%--大于10页  当前页从7开始 ,开始页 = 当前页-5 ,结束页 = 当前页+4--%>
                        <c:if test="${pageBean.pageIndex-5 < 1}">
                            <c:set var="start" value="1"></c:set>
                            <c:set var="end" value="10"></c:set>
                            <%--如果头超了,开始页为1 ,结束页为10--%>
                        </c:if>
                        <c:if test="${pageBean.pageIndex+4>pageBean.pages}">
                            <c:set var="start" value="${pageBean.pages-9}"></c:set>
                            <c:set var="end" value="${pageBean.pages}"></c:set>
                            <%--如果尾超了 ,开始页  pages-9 ,结束页为 pages--%>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            <c:forEach var="i" begin="${start}" end="${end}" step="1">
                <c:if test="${pageBean.pageIndex==i}">
                    【${i}】
                </c:if>
                <c:if test="${pageBean.pageIndex!=i}">
                    <a href="ItemsServlet?method=queryPage&pageIndex=${i}">【${i}】</a>
                </c:if>
            </c:forEach>
            <c:if test="${pageBean.pageIndex<pageBean.pages}">
                <a href="ItemsServlet?method=queryPage&pageIndex=${pageBean.pageIndex+1}">下一页</a>
            </c:if>
        </td>
    </tr>
</table>
</body>
</html>
