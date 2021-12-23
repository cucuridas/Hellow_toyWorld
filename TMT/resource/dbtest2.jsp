<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>TMT Take My Ten sentence news</title>
		<link rel="stylesheet" href="style2.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
         
	</head>
	<body>
		<div class="banner">
			<div class="navbar">
				<a href="#"><h3 class="logo">TMT</h3></a>
				<ul>
					<li><a href="#">HOME</a></li>
					<li><a href="#">New List</a></li>
					<li><a href="#">Another news</a></li>
					<li><a href="#">Other</a></li>
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
             
          
          <button id="id01" type="button"><span></span><h3 id="keywords"><%out.println(rs.getString("keywords"));%></h3></button> 
				
            <%
            }%>
                    <script>
            $(function(){
                  $('#id01').on('click',function(){
                           
                $.ajax({    
                         url:'test02.jsp',  
                         type:'get',
                         data: {
                             keywords: $('#keywords').val()
                         },
                         success : function(t){  
                  
                         $("<h1></h1>").text($(t).text()).appendTo(".content");  
                        
                           },
                          error : function(){
                                  alert('연결실패 ㅠㅠ');
                                              }
                    });   
               });
  
            });

        </script>
                <%
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
            
            
