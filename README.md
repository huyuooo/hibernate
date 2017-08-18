# hibernate
hibernate学习

1. HibernateDemo
   通过控制台简单实现一个hibernate应用程序，参考教程http://www.yiibai.com/hibernate/first-hibernate-application.html
   本例子中使用了注释代替了.hbm.xml文件；
   另外，存储持久对象的类中，初始化语句不同。
  
2. hibernate_web
   使用hibernate创建一个Web应用程序
   创建注册页面，获取注册信息，存储到数据库中。
   参考教程http://www.yiibai.com/hibernate/web-application-with-hibernate.html

3. web_maven
   使用maven构建web项目
   通过maven构建了一个简单的web项目 
   参考教程http://www.imooc.com/learn/443

4. HibernateQuickStart
   参考易百教程使用mvn构建工程，包括了通用的hibernate数据库操作方法：<br>
   **HQL语句查询：**查询读取多列数据、查询检索唯一结果；<br>
   **hibernate生命周期 瞬态、持久好分离的理解和操作：**<br>
	   *瞬态——持久化：* persis()、save（）、saveOrUpdate()、merge（）<br>
	   *持久化——分离：*evict()、clear() <br>
	   *分离——持久:* update()、saveOrUpdate()、merge（）、refresh()、lock()<br>		

   
