<%--
  Created by IntelliJ IDEA.
  User: DoromvQAQ
  Date: 2022/3/5
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改书籍</title>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    <small>修改书籍</small>
                </h1>
            </div>
        </div>
    </div>
<form action="${pageContext.request.contextPath}/book/updateBook" method="post">
    <div class="form-group">
        <input type="hidden" name="bookID" class="form-control" value="${Ubooks.bookID}">
    </div>
    <div class="form-group">
        <label for="bname">书籍名称：</label>
        <input type="text" name="bookName" class="form-control" id="bname" required value="${Ubooks.bookName}">
    </div>
    <div class="form-group">
        <label for="bcount">书籍数量：</label>
        <input type="text" name="bookCounts" class="form-control" id="bcount" required value="${Ubooks.bookCounts}">
    </div>
    <div class="form-group">
        <label for="ms">书籍描述：</label>
        <input type="text" name="detail" class="form-control" id="ms" required value="${Ubooks.detail}">
    </div>
    <div class="form-group">
        <input type="submit" class="form-control" value="修改">
    </div>
</form>
</div>
</body>
</html>
