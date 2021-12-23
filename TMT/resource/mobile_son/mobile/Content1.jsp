<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>TMT Take My Ten sentence news</title>
		<link rel="stylesheet" href="mobiletest.css">
		<meta name="viewport" content="width=device-width; initial-scale=1.0">

  		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css"/>
  		<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  		<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
  	<style type="text/css">
	#my_button { 

		display : block;

		margin:0 auto;

		width: 250px;

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
		width:70px;
		font-size: 37px;
	}
	.navbar ul li{
		margin 0 3px;
	}
	.navbar ul li a{
		font-size: 14px;
		margin: 0;
	}
	h4{
		text-align:left;
		margin-left: 15px;
		margin-right: 15px;
	}
	</style>
	</head>
	<body>
		<div style = "position:fixed; right: 12px; bottom: 12px; width:75px; z-index:1;">
			<button onclick="history.back()" >Back</button>
		</div>
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
<br><br><br><br>
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
                		out.println(rs.getString("sentenceText")+("<br><br><br>"));
                
        			}
                    %>
                </h4>
                    </br>
                    
                    
      
    <button id="my_button" onclick="location.href='https://search.naver.com/search.naver?query=<% out.print(cpage); %>&where=news&ie=utf8&sm=nws_hty'"><span></span><h3>네이버 뉴스에서 검색하기</h3></button>
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
		<br><br><br><br>
	</body>
</html>
