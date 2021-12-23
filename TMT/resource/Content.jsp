<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>TMT Take My Ten sentence news</title>
		<link rel="stylesheet" href="mobiletest.css">
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
            <h4>               
			<%
                String cpage = request.getParameter("keywords");
				Statement stm = null;
				ResultSet rs = null;
				Class.forName("com.mysql.jdbc.Driver");
				String myUrl = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(myUrl, "root", "Manager1");
				try {
        				stm = conn.createStatement();
        				if(stm.execute("select sentenceText from ContentText where keywords like '"+cpage+"' limit 0, 10")) {
                			rs = stm.getResultSet();
        			}
       			 while(rs.next()) {
                		out.println(rs.getString("sentenceText")+("</br>"));
                
        			}
                    %>
                </h4>
                    </br>
                    </br>
                    </br>
        <a href="https://search.naver.com/search.naver?query=<% out.println(cpage); %>&where=news&ie=utf8&sm=nws_hty"><h4 class="logo"><% out.println(cpage); %></h4></a>
                <%
       				 rs.close();
       				 stm.close();
				}
				catch(Exception e) {
       					 out.println("rs.next() ERROR");
				}
				conn.close();  %>
             
			</div>
		</div>
		
	</body>
</html>
