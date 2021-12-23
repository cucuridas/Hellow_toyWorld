<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>


<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="user-scalable=no">
		<title>TMT Take My Ten sentence news</title>
		<link rel="stylesheet" href="styleindex.css">
		<link rel="stylesheet" media="all and (min-width:768px) and (max-width:1199px)" href="mobiletest.css" >
	</head>
	<body>
		<div class="banner">
			<div class="navbar">
					<a href="index2.html"><h3 class="logo">TMT</h3></a>
				<ul>
					<li><a href="index2.html">HOME</a></li>
					<li><a href="listpage.jsp">New List</a></li>
					<li><a href="https://news.naver.com/">Another news</a></li>
				</ul>
			</div>
		
			<div class ="content">
       
    <%
            
            
	    Statement stm = null;
	    ResultSet rs = null;
	    Class.forName("com.mysql.jdbc.Driver");
	    String myUrl = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
	    Connection conn = DriverManager.getConnection(myUrl, "root", "Manager1");
	    try {
        	    stm = conn.createStatement();
        	    if(stm.execute("select keywords,count(*) from CalumData group by keywords order by count(*) desc limit 0,4")) {
                	    rs = stm.getResultSet();
            }
            while(rs.next()) {
                   %> 
          <button type="button" onclick="location.href='Content1.jsp?keywords=<%out.print(rs.getString("keywords"));%>'"><span></span><h3><%out.println(rs.getString("keywords"));%></h3></button> 
				
            <%
            }
            rs.close();
            stm.close();
    }
    catch(Exception e) {
            out.println("rs.next() ERROR");   
    }
    conn.close();
    %>
            
				
				
			</div>
		</div>
		
	</body>
</html>
            
            
