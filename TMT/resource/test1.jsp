<%@ page language="java" contentType="text/html; charset=UTF-8"

   pageEncoding="UTF-8" import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>DB Connection Test</title>

    </head>

    <body>

           <%

              String DB_URL = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=UTC";

              String DB_USER = "root";

              String DB_PASSWORD = "Manager1";

              Connection conn;

              Statement stmt;

              PreparedStatement ps;

              ResultSet rs;

              try {

                     Class.forName("com.mysql.jdbc.Driver");

                     conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                     stmt = conn.createStatement();



                    /* SQL 처리 코드 추가 부분 */



                     conn.close();

                     out.println("MySQL JDBC Driver Connection Test Success!!!");

              } catch (Exception e) {

                     out.println(e.getMessage());
			out.println("안됨");

              }

          %>

    </body>

    </html>
