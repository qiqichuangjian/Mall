<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 2019/3/14
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <c:redirect url="${request.contextPath}/ItemsServlet?method=queryPage"></c:redirect>
  </body>
</html>
