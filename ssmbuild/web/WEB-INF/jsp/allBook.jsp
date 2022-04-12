<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DoromvQAQ
  Date: 2022/3/4
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎${username}</title>

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
 <div class="container">
     <div class="row clearfix">
         <div class="col-md-12 column">
             <div class="page-header">
                 <h1>
                     <small>书籍列表————显示所有书籍</small>
                 </h1>
             </div>
         </div>
         <div class="row">
             <div class="col-md-4 colum">
                 <a class="btn btn-prmary" href="${pageContext.request.contextPath}/book/toAddBook">新增书籍</a>
                 <a class="btn btn-prmary" href="${pageContext.request.contextPath}/book/allBooks">显示所有书籍</a>
             </div>
             <div class="col-md-4 colum">
                 <form class="form-inline" action="${pageContext.request.contextPath}/book/queryByName" method="post">
                     <input type="text" placeholder="请输入要查询的书籍名称" class="form-control" name="bookName">
                     <input type="submit" value="搜索" class="btn btn-prmary">
                 </form>
             </div>
         </div>

     </div>

     <div class="row clearfix">
         <div class="col-md-12 column">
          <table class="table table-hover table-striped">
              <thead>
              <tr>
                  <th>书籍编号</th>
                  <th>书籍名称</th>
                  <th>书籍数量</th>
                  <th>书籍详情</th>
                  &nbsp;<th>操作</th>
              </tr>
              </thead>
              <tbody>
              <c:forEach var="book" items="${list}">
                  <tr>
                      <td>${book.bookID}</td>
                      <td>${book.bookName}</td>
                      <td>${book.bookCounts}</td>
                      <td>${book.detail}</td>
                      <td>
                          <a href="${pageContext.request.contextPath}/book/toUpdateBook/${book.bookID}">修改</a>
                          &nbsp; | &nbsp;
                          <a href="${pageContext.request.contextPath}/book/deleteBook/${book.bookID}">删除</a>
                      </td>
                  </tr>
              </c:forEach>
              </tbody>
          </table>
     </div>
 </div>
 </div>
</body>
</html>
