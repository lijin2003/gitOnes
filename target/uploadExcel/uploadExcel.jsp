<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/4/8 0008
  Time: 下午 2:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form id="myForm" action="" method="post" enctype="multipart/form-data">
        <input type="file" name="file" id="myFile">
        <input type="submit" value="确定提交">
    </form>
</body>
<script src="js/jquery.min.js"></script>
<script>
    $("#myFile").on("input",function () {
       var formData = new FormData($("#myForm")[0]);
       alert(formData)
        $.ajax({
            type:"POST",
            url:"upload",
            data:formData,
            processData: false,
            contentType: false,
            success : function (msg) {
                alert(msg)
            }
        })
    });

</script>
</html>
