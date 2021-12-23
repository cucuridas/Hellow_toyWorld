import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class DBconnection {
	
	
	public static PreparedStatement pstmt = null;
	public static Connection conn = null;
	public static ArrayList<String> contentText = new ArrayList<>() ;
	public static ArrayList<String> newspapercompany = new ArrayList<>() ;
	public static ArrayList<String> wordsText = new ArrayList<>() ;
	public static ArrayList<String> sentenceText = new ArrayList<>() ;
	
	public static Connection getConnection3() {
		
		String user = "root";
		String pw = "Manager1";
		String url = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=UTC";
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url,user,pw);
			System.out.println("성공");
		}catch(SQLException e) {
			System.out.println("실패");
			System.out.print(e.getMessage());
		}
		
		return conn;
	}
	
	
	public static void inserDBdata(int a,String b,String c,String kewords)  {//DB에 저장하는 a는 인덱스번호 b는 기사내용 c는 신문사 d는 날짜정보
		
		
		pstmt = null;
		String sql;
		
		sql = "insert into CalumData (indexdata, contenttext, newspapercompany, datedata, keywords) values(?,?,?,?,?)";
		
		try {
		pstmt = conn.prepareStatement(sql);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy년 MM월 dd일");
		Calendar time = Calendar.getInstance();
		
		String datedata = format.format(time.getTime());
		
		
		pstmt.setInt(1, a);
		pstmt.setString(2,b);
		pstmt.setString(3,c);
		pstmt.setString(4,datedata);
		pstmt.setString(5,kewords);
	
		 int result  = pstmt.executeUpdate();
		
		if (result >0) {
			
			System.out.println("정상적으로 등록");
		}
		else {
			System.out.println("등록 실패");
		}
		
		
		}catch (SQLException ex ) {
			ex.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		
	}
	public static void insertSentence(int a,String b,int c,String d)  {//DB에 저장하는 a는 인덱스번호 b는 기사내용 c는 신문사 d는 날짜정보
		
		
		pstmt = null;
		String sql;
		
		sql = "insert into SentenceData (sentencenumber, setencecontents, indexnumber,newspapercompany) values(?,?,?,?)";
		
		try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, a);
		pstmt.setString(2,b);
		pstmt.setInt(3,c);
		pstmt.setString(4,d);
		
		 int result  = pstmt.executeUpdate();
		
		if (result >0) {
			
			System.out.println("정상적으로 등록");
		}
		else {
			System.out.println("등록 실패");
		}
		
		
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void inserDBdata(int a,String b)  {//DB에 저장하는 a는 인덱스번호 b는 기사내용 c는 신문사 d는 날짜정보
		
		
		pstmt = null;
		String sql;
		
		sql = "insert into WordsData (wordsnumber, wordscontents) values(?,?)";
		
		try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, a);
		pstmt.setString(2,b);
		
		
		
		 int result  = pstmt.executeUpdate();
		
		if (result >0) {
			
			//System.out.println("정상적으로 등록");
		}
		else {
			System.out.println("등록 실패");
		}
		
		
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
public static void insertLast(String a,String b)  {//DB에 저장하는 a는 인덱스번호 b는 기사내용 c는 신문사 d는 날짜정보
		
		
		pstmt = null;
		String sql;
		
		sql = "insert into ContentText (keywords, sentenceText) values(?,?)";
		
		try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setString(1, a);
		pstmt.setString(2,b);
		
		
		
		 int result  = pstmt.executeUpdate();
		
		if (result >0) {
			
			//System.out.println("정상적으로 등록");
		}
		else {
			System.out.println("등록 실패");
		}
		
		
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void insertwordsDB(int a,String b,int c)  {//DB에 저장하는 a는 인덱스번호 b는 기사내용 c는 신문사 d는 날짜정보
		
		
		pstmt = null;
		String sql;
		
		sql = "insert into WordsData2 (wordsNumber, wordsContents, wordscount) values(?,?,?)";
		
		try {
		pstmt = conn.prepareStatement(sql);
		
		
		pstmt.setInt(1, a);
		pstmt.setString(2,b);
		pstmt.setInt(3, c);
		
		
		 int result  = pstmt.executeUpdate();
		
		if (result >0) {
			
			//System.out.println("정상적으로 등록");
		}
		else {
			System.out.println("등록 실패");
		}
		
		
		}catch (SQLException e ) {
			e.printStackTrace();
		}
		try {
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static void deletedataC() {//DB 데이터를 삭제하는 명령어 시스템완성되면....사용x		

		pstmt = null;
		String sql;
		
		sql = "delete from CalumData";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			
			//int result = pstmt.executeUpdate();
			
			System.out.println(pstmt.executeUpdate());
			
			
		}catch(SQLException e) {
		
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		
		}
	public static void deletedataC2() {//DB 데이터를 삭제하는 명령어 시스템완성되면....사용x		

		pstmt = null;
		String sql;
		
		sql = "delete from ContentText";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			
			//int result = pstmt.executeUpdate();
			
			System.out.println(pstmt.executeUpdate());
			
			
		}catch(SQLException e) {
		
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		
		}
		
		
	public static void readCalumdata() {//DB 정보를 읽어오는 함수 속성값 필요없이 다 긁어옴;;;
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select * from CalumData ";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				contentText.add(res.getString("contenttext"));
				newspapercompany.add(res.getString("newspapercompany"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		
		
	}
	public static void readWordsdata() {//DB wordsdata를 읽어오는 함수
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select wordscontents,sum(wordscount)  from wordsdata2 where not REGEXP_LIKE(wordscontents,'신문|연합|뉴스|송고|사진') group by wordscontents order by sum(wordscount) desc";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				wordsText.add(res.getString("wordscontents"));
			}
			
			 int result  = pstmt.executeUpdate();
				
				if (result >0) {
					
					System.out.println("정상적으로 동작");
				}
				else {
					System.out.println("동작 실패");
				}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		}
	
	public static void readSentence(String a) {//kewords를 통해 keywords가 포함된 setence를 찾는 메소드
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select * from (select SETENCECONTENTS from sentencedata where SETENCECONTENTS like '%"+a+"%' order by dbms_random.value) where rownum  <=10" + 
				"";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				sentenceText.add(res.getString("setencecontents"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		}
	public static String plusSentence(String a) throws SQLException {
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select * from (select SETENCECONTENTS from sentencedata where SETENCECONTENTS like '%"+a+"%' order by dbms_random.value) where rownum =1" + 
				"";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		return res.getString("setencecontents");
	}
	
	public static ArrayList<String> readKeywords() {
		String keywords = null;
		 ArrayList<String> keywords1 = new ArrayList<>() ;
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select t1.keywords,count(*) from CalumData t1 group by t1.keywords having count(*) >1 order by count(*) desc" ;
				
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				keywords1.add(res.getString("keywords"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		
		
		return keywords1;
	}
	
	public static void readCalumdata2(String keywords) {//
		ResultSet res = null;
		pstmt = null;
		String sql;
		
		sql = "select contenttext,keywords from CalumData where keywords like '"+keywords+"'";
		try {
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while(res.next()) {
				contentText.add(res.getString("contenttext"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e){
				
			}
		}
		
		
	}
		
}
