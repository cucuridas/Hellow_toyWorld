<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>TMT Take My Ten sentence news</title>
		<link rel="stylesheet" href="styleindex.css">
		<meta name="viewport" content="width=device-width; initial-scale=1.0">

  		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
  		<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
	</head>
<style type="text/css">
	#my_button { 

		display : block;

		margin:0 auto;

		width: 200px;

		padding: 15px 0;

		text-align:center;

		margin-top:60px; 

		border-radius: 25px;

		font-weight: bold;

		border: 2px solid #00dcc8;

		background:transparent;

		color: black;

		cursor: pointer;

		position: relative;

		overflow: hidden;
	}
	.logo{
		width : 70px;
		font-size: 37px;
	}
	.navbar ul li{
		margin: 0 3 px;
  	}
	.navbar ul li a{
		margin: 0;
		font-size : 14px;
	}
</style>

	<body>
		<div>
			<div style="text-align: center; background-color: black">
				<div class="navbar">
					<a href="m.index.html"><h3 class="logo">TMT</h3></a>
					<ul>
						<li><a href="m.index.html" style="color: #00dcc8">HOME</a></li>
						<li><a href="listpage.jsp" style="color: #00dcc8">New List</a></li>
						<li><a href="https://news.naver.com/" style="color: #00dcc8">Another</a></li>
					</ul>
				</div>	
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


				<button id="my_button"  onclick="location.href='Content1.jsp?keywords=<%out.print(rs.getString("keywords"));%>'"><span></span>
				<h3><%out.println(rs.getString("keywords"));%></h3>
				</button>

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
<br><br>
		<div data-role="footer" data-position="fixed" data-fullscreen="true" class="foot">
			<p style="margin-left: 5%">&copy; Copyright  by 최충은, 손광호, 홍윤기
				<br>
				Tel : 010-2651-2398
				<br>
				E-mail	: cucuridas@gmail.com


			</p>
		</div>
	</body>
</html>
         
