
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.UserDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:useBean id="obj" class="com.example.User"></jsp:useBean>
<jsp:setProperty property="*" name="obj" />

<%
    int i = UserDao.save(obj);
    if (i > 0) {
        out.print("You are successfully registered");
    }    
%>
<html>
  <body> 
      注册成功:<br>
      <hr>
      使用Bean的属性方法<br>
  Id:<%=obj.getId()%><br>
      用户名: <%=obj.getName()%><br>
      密码: <%=obj.getPassword()%><br>
      年龄: <%=obj.getEmail()%><br>
      <hr>
      使用getProperty<br>
  Id:<jsp:getProperty name="obj" property="id"/><br>
      用户名:<jsp:getProperty name="obj" property="name"/><br>
      密码:  <jsp:getProperty name="obj" property="password"/><br>
      年龄:  <jsp:getProperty name="obj" property="email"/>
  </body>
</html>	