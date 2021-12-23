<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %> 

<html lang="ko">
 <head>
  <meta charset="UTF-8">
 
  <title>TMT Take My Ten sentence news</title>  
  

		<style>
	.width{margin: 0 auto; width:1200px; position:relative;}
	.width:after{content:'';display:block;clear:both;}
	body,div,p,ul,li,a,img,nav{margin:0; padding:0;}
	table{border-spacing:0;}

	#header{height:100px; position:relative; background:#000; z-index:2;}
	#top {padding:5px 0; }
	#header img{float:left; padding:3px 10px;}
	#header > #top> a{float:left;  color:white; font-size:12px; padding:3px; position:absolute; top:-35px; left:540px; text-decoration: none;}
	
	#top nav{float:right; padding:10px 0; position:relative; right:10px;}
	#top nav ul li{float:left; list-style:none; font-size:11px; padding: 3px;}
	#top nav ul li a {text-decoration:none; color:white;}

	#visual {background:url("") no-repeat 50% 0/cover; width:100%;height:150px;}

	
	#footer{height:40px; background:#f4f4f4;}
	#ftop{height:40px; border-bottom:1px solid #dadada;}
	#ftop li {list-style:none; font-size:12px; float:left; padding:12px 3px;}
	#ftop a {text-decoration:none; color:#990000;}
	
	#fbottom img{position:absolute; top:10px; left:3px;}
	#fbottom a {text-decoration:none; float:right; color:#990000;}
	#section  {width:1020px;height:605px;margin:20px;padding:25px 15px; box-sizing:border-box; }
	#section ul{font-size: 20px;}


	
	

	.logo{
		width: 120px;
		font-size: 50px;
		color: #0ABAB5;
		cursor: pointer
		}
		
	#service ul li {
		list-style:none;
		display: inline-block;
		margin:0 10px;
		position: relative;
		}
	#service ul li a{
		text-decoration: none;
		color: #0ABAB5;
		text-transform: uppercase;
		}
	#service ul li::after{
		content:'';
		height: 3px;
		width:0;
		background: #009688;
		position: absolute;
		left: 0;
		bottom: -10px;
		transition: 0.5s;
		}
	#service ul li:hover::after{
		width: 100%;
		}

	.content{
		width: 100%;
		position: absolute;
		top: 500px;
		transform: translateY(-70%);
		text-align: center;
		color:#000;
		transition: 2s;
		
		}
	.content h1{
		font-size: 50px;
		margin-top:80px;
		}
	.content p{
		margin: 20px auto;
		font-weight: 100;
		line-height: 25px;
		}
	
	#btn1{
		margin-right:100px;
		}
	
	
	
	button { 
	
			display: inline-block;
	
			margin:0 auto;
	
			width: 200px;
	
			padding: 3px;
	
			text-align:center;
	
			margin-top:60px; 
	
			border-radius: 25px;
	
			font-weight: bold;
	
			border: 2px solid #009688;
	
			background:transparent;
	
			color: #000;
	
			cursor: pointer;
	
			position: relative;
	
			overflow: hidden;
		}
	
	span{
		background: #009688;
		height: 100%;
		width: 0;
		border-radius: 25px;
		position: absolute;
		left: 0;
		bottom: 0;
		z-index: -1;
		transition : 0.5s;
		}
	button:hover span{
		width : 100%;
		
		}
	button:hover{
		border : none;	
		}	


	</style>
		
	</head>
<body> 
 <div id="header">

	<div id="top" class="width">
		<a href="test.html"><h3 class="logo">TMT</h3></a>
		
	
		
		<nav id="service">
			<ul>
			   <li><a href="test.html">HOME</a></li>
               <li><a href="pclist.jsp">New List</a></li>
               <li><a href="https://news.naver.com/">Another news</a></li>
			</ul>
		</nav>
	</div>
 </div>


<div class="width" >
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
                		out.println(rs.getString("sentenceText")+("</br></br>"));
                
        			}
                    %>    
                </h4>
                    </br>
                    </br>
                    </br>
      <div id="btncover">
    <button id="btn1" type="button" onclick="location.href='https://search.naver.com/search.naver?query=<% out.print(cpage); %>&where=news&ie=utf8&sm=nws_hty'"><span></span><h4>네이버 뉴스에서 검색하기</h4></button>
    <button type="button" onclick="location.href='pclist.jsp'"><span></span><h4>뒤로가기</h4></button>
    </div>
               <%
       				 rs.close();
       				 stm.close();
				}
				catch(Exception e) {
       					 out.println("rs.next() ERROR");
				}
				conn.close();  %>   
             
			</div>

<div id="section">
	


</div>


</div>


 <div id="visual">
 	
 </div>

 <div id="container">

	
	</div>
 </div>

<div id="footer">
	<div id="ftop">
		<div class="width">
			<ul>
				<li><a href="">Tel : 010-2651-2398</a></li><li>|</li>
				<li><a href="">E-mail : cucuridas@gmail.com</a></li><li>|</li>
				<li><a href="">Copyright © 최충은, 손광호, 홍윤기</a></li>
			</ul>
		</div>
	</div>

	  
 </body>
</html>
