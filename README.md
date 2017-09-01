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
5. many2one<br>
   参考how2j上的教程，对hibernate的关系进行了学习，包括：<br>
   一对多  多对一  多对多	<br>	
6. hibernate_jilian<br>
   级联可以总结为一对多中关系的关联，对“一”操作后，“多”也进行相应变换。包括4种类型：<br>
	*all:*所有操作都执行级联操作；<br> 
  	*none:*所有操作都不执行级联操作；<br>
   *delete:*删除时执行级联操作；<br>
   *save-update：*保存和更新时执行级联操作；<br>
7. hibernate_Criteria　**分页查询**<br> 
   ```
   Criteria c= s.createCriteria(Product.class);
   c.add(Restrictions.like("name", "%"+name+"%"));
   c.setFirstResult(2);
   c.setMaxResults(5);
   ```
   ```
   c.setFirstResult(2); 表示从第2条数据开始
   c.setMaxResults(5); 表示一共查询5条数据
   ```
8. hibernate_get_load<br>
   通过ID回去对象的方式有两种，分别是get和load：<br>
   他们的区别分别在于: **延迟加载**， **对于id不存在的时候的处理**<br>
   *延迟加载：*<br>
   　　load方式是延迟加载，只有属性被访问的时候才会调用sql语句<br>
   　　get方式是非延迟加载，无论后面的代码是否会访问到属性，马上执行sql语句<br>
   *对于id不存在的对象的处理:*<br>
   　　get方式会返回null <br>
　　load方式会抛出异常


 

   
