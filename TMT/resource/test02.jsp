<%@ page import = "java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">



</head>

<body>
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
                out.println(rs.getString("sentenceText"));
                
                
        }
        rs.close();
        stm.close();
}
catch(Exception e) {
        out.println("rs.next() ERROR");
}
conn.close();
%>
</body>

</html>
