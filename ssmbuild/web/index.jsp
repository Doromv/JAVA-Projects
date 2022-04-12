<%--
  Created by IntelliJ IDEA.
  User: DoromvQAQ
  Date: 2022/3/4
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>进入书籍页面</title>
    <style>
      a{
        text-decoration: none;
        color: black;
      }
      h3{
        width: 180px;
        height: 38px;
        margin: 250px auto;
        text-align: center;
        line-height: 38px;
        background: deepskyblue;
        border-radius: 5px;
      }
    </style>
  </head>
  <body>
  <h3>
    <a href="${pageContext.request.contextPath}/book/allBooks">进入书籍页面</a>
  </h3>
  <h3>
    <a href="${pageContext.request.contextPath}/user/tologin">登入</a>
  </h3>
  </body>
</html>
