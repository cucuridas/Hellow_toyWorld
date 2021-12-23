import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

import java.util.Date;




public class CrollingInfo {
	  
	
	static Document doc ; // document ��ü�� �޾ƿ� �����Լ�
	static ArrayList<String> info_db1 =new ArrayList() ;//���մ��� ��� ���� DB
	static String maininfo_href;//dom ��ü �� Ư���������� �޾ƿ� href ����;
	static String[] dividesec;  
	static ArrayList<String> contentHref = new ArrayList<>() ;
	static int addd = 0;
	

      
	
	public static void connect_url(String url){
         
         String url_info = url;
     //    System.out.println(url);
         
         try {
            doc = Jsoup.connect(url_info).get();
         }catch (IOException e){
            e.printStackTrace();
         }
      
		}
		//������Ʈ �ּҷ� Connecting
	public static void rmHrefWords(String b,String a) {
		
		
		
		if ( b.matches(a)) {
			
		}
		else
			
		{
			try {
			contentHref.add(dividesec[1]+"\n");
			
			}catch(NullPointerException e) {
				e.printStackTrace();
			}
			
			
			
			
		
		}addd= addd+1;
		
		
	}
		// Ư�����ڿ��� �� �迭�� ����� href �迭�� �̵���Ű�� �Լ�
	
	public static void crolling_href(String mainsec, String subsec) {
	 
		int a = 0;
		
		
	 Elements element = doc.select(mainsec);  //mainsec ����) "div.mainArticle"
	
	
	




	 Iterator<Element> ie1 = element.select(subsec).iterator(); //subsec ���� "div.txtArea""strong.txtArea"
	 
	/*
	 while (ie1.hasNext()) {
		 System.out.println(ie1.next().toString());
		 
	 }*/
	 
	
	 
	 while (ie1.hasNext()) {
		
		 maininfo_href = ie1.next().toString();
		 

		 
		 dividesec = maininfo_href.split("<a href=\"|\">" );
		
		 rmHrefWords(dividesec[1], ".*query.*");
		
				
	 			}	//System.out.println(contentHref);
	 
	 	
	
	
	
	 
      
}
	
	
		// �����۸�ũ ���� ũ�Ѹ��ϴ� �Լ�
	public static void conhref(String a) {
		switch(a) {
		
		case "yunhap":{
		
		for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
			 
			String hreftunhap  = contentHref.get(roll).replaceAll("\" class=\"tit-wrap","");
			connect_url("https:"+hreftunhap);
			 
			
			 crolling_info("p","yunhap");
			 
		 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
		
		}break;
		case "segye":{
			
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url("http://www.segye.com"+contentHref.get(roll));
				
				 crolling_info("p","segye");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		case "daily":{
			
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url(contentHref.get(roll));
				
				 crolling_info("p","daily");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		case "moneyto":{
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url(contentHref.get(roll));
				 
				 crolling_info("div#textBody","moneyto");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		case "tapa":{
			
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url("http://newstapa.org"+contentHref.get(roll));
				
				 crolling_info("div.ce-paragraph","tapa");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		case "hani":{
			
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url("http://www.hani.co.kr"+contentHref.get(roll));
				
				 crolling_info("div.text","hani");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		case "khan":{
			
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url("https:"+contentHref.get(roll));
				
				 crolling_info("p","khan");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;	
		case "maeil":{
			for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {
				 
				 connect_url(contentHref.get(roll));
				 
				 crolling_info("div.art_txt","maeil");
				 
			 } // contentHref�� ��� href�� �����Ҷ����� href�����Ͽ� ���� ����
			
			}break;
		
		}
		
		}
		// �����۸�ũ ������ ���� ����
		
	public static void crolling_info( String subsec,String newsname) {
		 
		switch(newsname) { 
			
			case "yunhap"	:
			{
					SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");
		
					Elements element = doc.getElementsByTag(subsec);
					String content = element.text().replace("���Ư��  �۰�ð�","");
					content = content.replace("�۰�ð�","");
					content = content.replace("�糭���� ������� �ڵ��ϼ� ����� ���� �ֽ��ϴ�.","");
					content = content.replace("[���մ��� �ڷ����]", "");
					String DateSector = content.substring(0,17);
					content = content.replace(DateSector, "");
					
					info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;	
			
			case "segye" :
			{
				Elements element = doc.getElementsByTag(subsec);
				element.text();
				String content = element.text().replace("�μ� ���� �۾� ũ�� ���� ���� ���� ũ�� ���� �� �ܰ� ���� ũ�� ���� �⺻ ũ�� ���� �� �ܰ� ū ũ�� ���� ���� ū ũ�� ����", "");
				String DateSector = content.substring(0,51);
				content = content.replace(DateSector,"");
				
				
				info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;
		 
			case "daily"	:
			{
					Elements element = doc.getElementsByTag(subsec);
					element.text();
					
					 info_db1.add(element.text().replace("/����=��Ʃ�� ä�� ĸó","")+"\n");//mainsec ����) "div.mainArticle"
			}break;
			
			case "moneyto"	:
			{	
				 Elements element = doc.select(subsec);   
				 String content = element.text().replace("/����=��Ʃ�� ä�� ĸó", "") ;
				 content = content.replace("/����=����1 ","");
				 content = content.replace("/����=(����)���ý�,","");
				 content = content.replace("(����=����1)","");
				 content = content.replace("(����=����1) = ","");
				 info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;		
			case "tapa"	:
			{
				 Elements element = doc.select(subsec);  
				 String content = element.text();
				 
				 info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;		
			case "hani"	:
			{
				 Elements element = doc.select(subsec);   
				 String content = element.text();
				 
				 info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;		
			case "khan"	:
			{
					Elements element = doc.getElementsByTag(subsec);
					 String content = element.text();
					 
					 info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;
			case "maeil"	:
			{
				int countnum = 0;
				int startnum = 0;
				int lastnum = 0;
				ArrayList<String> testwords1 = new ArrayList<>() ;
				
				 Elements element = doc.select(subsec);   
				 String content = element.text();
				
			     for(int a= 0; a < content.length(); a ++) {
			    	 String check = "";
			    	 check =String.valueOf(content.charAt(a));
			    	 //System.out.println(check);
			    	 countnum ++;  
				 if(check.equals("[")) {
					 
					 startnum=a+1;
				 }
				 if(check.equals("]")) {
					 lastnum=a+1;
				 } 
				 if(startnum !=0&&lastnum !=0) {
					 String Deletewords = content.substring(startnum-1,lastnum);
					
				     content = content.replace(Deletewords, "");
				    //System.out.println(Deletewords);
				     startnum =0;
				     lastnum =0;
					 
				  }
				   
			     }
			     
			   
			     //content=content.replace(Deletewords, "");
			     
				 System.out.println(content);
				 //info_db1.add(content+"\n");//mainsec ����) "div.mainArticle"
			}break;

		}
	 
		}
	 	//���� �����ͼ� DB ����Ʈ�� ����
	
	public static void initVar () {
		
	
		maininfo_href = null;
	    dividesec = null;
	    info_db1.clear();;
	    contentHref.clear();
	      
		
		
	}
	
	public static void insertCalum(String a) {
		
		int indexnumber = 0 ;
		Date test = new Date();
		

		ArrayList<Integer> countwords = new ArrayList<>() ;
		ArrayList<String> words = new ArrayList<>() ;
		String keywords = null;
		int numbaercount = 0;
		
		
		  
	    for(int countnum = 0; countnum < info_db1.size(); countnum++) {
	    	
	    	System.out.println(numbaercount);
	   
			
	    	numbaercount++;
	    	
	    	try {
	    	String strToExtrtKwrd = info_db1.get(countnum);
			// init KeywordExtractor
			KeywordExtractor ke = new KeywordExtractor();
			// extract keywords
			KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
			// print result
			for( int count = 0; count < kl.size(); count++ ) {
			    Keyword kwrd = kl.get(count);
			    char  tmp = kwrd.getString().charAt(0);
			    
			    if(kwrd.getString().length()==1) {
	   				continue;
			    }
			    if(Character.isDigit(tmp))continue;
			   
			    countwords.add(kwrd.getCnt());
			}
			Collections.sort(countwords,Collections.reverseOrder());
			
			
			
			for( int count = 0; count < kl.size(); count++ ) {
			    Keyword kwrd = kl.get(count);
			    char  tmp = kwrd.getString().charAt(0);
			    
			    if(kwrd.getString().length()==1) {
	   				continue;
			    } 
			    if(Character.isDigit(tmp))continue;
			    
			    if(kwrd.getCnt()==countwords.get(0)||kwrd.getCnt()==countwords.get(1)) {
			
			    	words.add(kwrd.getString());
			    }
			}
			
				
			keywords = words.get(0);
			
			indexnumber = indexnumber+1;
		    
			
			DBconnection abc = new DBconnection();
			abc.getConnection3();
			System.out.println(info_db1.get(countnum));
			
	    	abc.inserDBdata(indexnumber,info_db1.get(countnum),a,keywords);
	    	//System.out.println(indexnumber+" "+keywords+" " );
	    	countwords.clear();
	    	words.clear();
	    }
	    catch(NullPointerException ed) {
	    	continue;
	    }
	   
	    
	   
	    }	
	    initVar ();
		
	}
	public static void insertCalum2(String a) {
		
		int indexnumber = 0 ;
		Date test = new Date();
		

		ArrayList<Integer> countwords = new ArrayList<>() ;
		ArrayList<String> words = new ArrayList<>() ;
		String keywords = null;
		
		
		
		  
	    for(int countnum = 0; countnum < info_db1.size(); countnum++) {
	    	
	    	
	    	String strToExtrtKwrd = info_db1.get(countnum);
			// init KeywordExtractor
			KeywordExtractor ke = new KeywordExtractor();
			// extract keywords
			KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
			// print result
			for( int count = 0; count < kl.size(); count++ ) {
			    Keyword kwrd = kl.get(count);
			    
			    
			    if(kwrd.getString().length()==1) {
	   				continue;
			    }   		
			   
			    countwords.add(kwrd.getCnt());
			}
			Collections.sort(countwords,Collections.reverseOrder());
			
			
			
			for( int count = 0; count < kl.size(); count++ ) {
			    Keyword kwrd = kl.get(count);
			    
			    
			    if(kwrd.getString().length()==1) {
	   				continue;
			    }   		
			    if(kwrd.getCnt()==countwords.get(0)||kwrd.getCnt()==countwords.get(1)) {
			
			    	words.add(kwrd.getString());
			    }
			}
			
				
			keywords = words.get(0);
			
			indexnumber = indexnumber+1;
		    
			
			DBconnection abc = new DBconnection();
			abc.getConnection3();
	    	abc.inserDBdata(indexnumber,info_db1.get(countnum),a,keywords);
	    	//System.out.println(indexnumber+" "+keywords+" " );
	    	countwords.clear();
	    	words.clear();
	    }
	    
	    
	   
		
	    initVar ();
		
	}
	// href ���� ���� �ʱ�ȭ 
		//���� �ʱ�ȭ


	
	public static void AllinOne() {
		String url = "https://www.yna.co.kr/theme/topnews-history";//���մ���  ž���� ��ũ
		String url2 = "http://www.segye.com/news";//�����Ϻ�  
		String url3 = "http://www.newdaily.co.kr/";//�������ϸ�
		String url4 = "https://news.mt.co.kr/theissue/";//�Ӵ�������
		String url5 = "https://newstapa.org/recent";//����Ÿ��
		String url6 = "http://www.hani.co.kr/arti/list.html";//�Ѱܷ�
		String url7 = "http://www.khan.co.kr/bestnews/realtime_list.html";//����
		String url8 = "https://www.mk.co.kr/news/bestclick/";//���ϰ���
		int a = 0;
		
		 
	
		 connect_url(url);
	     crolling_href("div.news-con","[href]");// ���մ��� ž���� �����۸�ũ ũ�Ѹ�
	     conhref("yunhap");
	     insertCalum("���մ���");
	    
		
	   
	     connect_url(url2);
	     crolling_href("div#wps_layout2_box2","[href]");
	     conhref("segye");
	     
	     insertCalum("�����Ϻ�");
	     
	    /*
	     connect_url(url3);
		 crolling_href("article.nd-center-article","[href]");  
		 conhref("daily");
		 insertCalum("�������ϸ�");*/
	    /*
		 connect_url(url5);
		 crolling_href("figure.article-image","[href]"); 
		 conhref("tapa");
		 insertCalum2("����Ÿ��");
		 initVar();*/
	   
		 connect_url(url6);
		 crolling_href("p.article-prologue","[href]"); 
		 conhref("hani");
		 insertCalum("�Ѱܷ�");
		 initVar();
		 
		 connect_url(url7);
		 crolling_href("strong.hd_title","[href]"); 
		 crolling_href("div.bestview_text_list","[href]"); 
		 conhref("khan");
		 insertCalum("����");
		 initVar();
		 
		 connect_url(url8);
		 crolling_href("dt.tit","[href]");
		 conhref("maeil");
		 insertCalum("����");
		 initVar();
		 
		 
		 connect_url(url4);
		 crolling_href("strong.subject","[href]");  
		 conhref("moneyto");
		 insertCalum("�Ӵ�������");
		 initVar();
			
		

		
		
	}
	public static String test(String contents) {
		String keyword = null;
		
		
		String strToExtrtKwrd = contents;
		// init KeywordExtractor
		KeywordExtractor ke = new KeywordExtractor();
		// extract keywords
		KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
		// print result
		for( int i = 0; i < kl.size(); i++ ) {
		    Keyword kwrd = kl.get(i);
		    System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
		}

		
		
		return keyword;
		
		
		
	}
	
	public static void main(String[] args) throws IOException  {
	// TODO Auto-generated method stub
		String url = "https://www.yna.co.kr/theme/topnews-history";//���մ���  ž���� ��ũ
		String url2 = "http://www.segye.com/news";//�����Ϻ�  
		String url3 = "http://www.newdaily.co.kr/";//�������ϸ�
		String url4 = "https://news.mt.co.kr/theissue/";//�Ӵ�������
		String url5 = "https://newstapa.org/recent";//����Ÿ��
		String url6 = "http://www.hani.co.kr/arti/list.html";//�Ѱܷ�
		String url7 = "http://www.khan.co.kr/bestnews/realtime_list.html";//����
		String url8 = "https://www.mk.co.kr/news/bestclick/";//���ϰ���
	   
		AllinOne();
		
		 /*for(String i:info_db1) {
			 int countnum =0;
			 System.out.println(countnum);
			 countnum ++;
			 System.out.println(i);}
		// insertCalum("�Ӵ�������");
		 //System.out.println(info_db1.get(20));
		 initVar();*/
	   }
}




