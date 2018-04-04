<%--
  Created by IntelliJ IDEA.
  User: 王晨
  Date: 2017/8/24
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>测试批量添加</title>
    <script src="//cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script>
        $(function(){
           $("#bt1").click(function(){
               alert("===");
              $.ajax({
                  method:"post",
                  url:"<%=basePath%>quickcommonController/addBatch",
                  data:{},
                  dataType:"json",
                  success:function(msg){
                      alert(msg.success);
                  }

              });
           });
        });
    </script>
</head>
<button type="button" id="bt1">点一下</button>
</body>
</html>
