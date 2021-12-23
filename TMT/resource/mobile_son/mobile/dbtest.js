<%@ page import = "java.sql.*" %>
<%
	Statement stm = null;
	ResultSet rs = null;
	Class.forName("com.mysql.jdbc.Driver");
	String myUrl = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
	Connection conn = DriverManager.getConnection(myUrl, "root", "Manager1");
	try {
        	stm = conn.createStatement();
        	if(stm.execute("select * from CalumData")) {
                	rs = stm.getResultSet();
        }
        while(rs.next()) {
                out.println(rs.getString("indexdata"));
                out.println(rs.getString("contenttext"));
                out.println(rs.getString("newspapercompany"));
                out.println(rs.getString("datedata"));
                out.println(rs.getString("keywords"));
                out.write("<br>");
        }
        rs.close();
        stm.close();
}
catch(Exception e) {
        out.println("rs.next() ERROR");
}
conn.close();
%>