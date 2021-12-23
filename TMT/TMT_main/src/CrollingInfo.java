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


	static Document doc ; // document 객체를 받아올 전역함수
	static ArrayList<String> info_db1 =new ArrayList() ;//연합뉴스 기사 내용 DB
	static String maininfo_href;//dom 객체 중 특정영역에서 받아온 href 정보;
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
	//웹사이트 주소로 Connecting
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
	// 특정문자열을 뺀 배열을 사용할 href 배열로 이동시키는 함수

	public static void crolling_href(String mainsec, String subsec) {

		int a = 0;


		Elements element = doc.select(mainsec);  //mainsec 예시) "div.mainArticle"







		Iterator<Element> ie1 = element.select(subsec).iterator(); //subsec 예시 "div.txtArea""strong.txtArea"

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


	// 하이퍼링크 정보 크롤링하는 함수
	public static void conhref(String a) {
		switch(a) {

			case "yunhap":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					String hreftunhap  = contentHref.get(roll).replaceAll("\" class=\"tit-wrap","");
					connect_url("https:"+hreftunhap);


					crolling_info("p","yunhap");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "segye":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url("http://www.segye.com"+contentHref.get(roll));

					crolling_info("p","segye");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "daily":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url(contentHref.get(roll));

					crolling_info("p","daily");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "moneyto":{
				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url(contentHref.get(roll));

					crolling_info("div#textBody","moneyto");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "tapa":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url("http://newstapa.org"+contentHref.get(roll));

					crolling_info("div.ce-paragraph","tapa");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "hani":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url("http://www.hani.co.kr"+contentHref.get(roll));

					crolling_info("div.text","hani");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "khan":{

				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url("https:"+contentHref.get(roll));

					crolling_info("p","khan");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;
			case "maeil":{
				for(int roll = 0;roll<=contentHref.size()-1;roll++ ) {

					connect_url(contentHref.get(roll));

					crolling_info("div.art_txt","maeil");

				} // contentHref에 담긴 href를 소진할때까지 href연결하여 내용 저장

			}break;

		}

	}
	// 하이퍼링크 정보를 통해 연결

	public static void crolling_info( String subsec,String newsname) {

		switch(newsname) {

			case "yunhap"	:
			{
				SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm");

				Elements element = doc.getElementsByTag(subsec);
				String content = element.text().replace("기상특보  송고시간","");
				content = content.replace("송고시간","");
				content = content.replace("재난포털 기사제보 자동완성 기능이 켜져 있습니다.","");
				content = content.replace("[연합뉴스 자료사진]", "");
				String DateSector = content.substring(0,17);
				content = content.replace(DateSector, "");

				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;

			case "segye" :
			{
				Elements element = doc.getElementsByTag(subsec);
				element.text();
				String content = element.text().replace("인쇄 메일 글씨 크기 선택 가장 작은 크기 글자 한 단계 작은 크기 글자 기본 크기 글자 한 단계 큰 크기 글자 가장 큰 크기 글자", "");
				String DateSector = content.substring(0,51);
				content = content.replace(DateSector,"");


				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;

			case "daily"	:
			{
				Elements element = doc.getElementsByTag(subsec);
				element.text();

				info_db1.add(element.text().replace("/사진=유튜브 채널 캡처","")+"\n");//mainsec 예시) "div.mainArticle"
			}break;

			case "moneyto"	:
			{
				Elements element = doc.select(subsec);
				String content = element.text().replace("/사진=유튜브 채널 캡처", "") ;
				content = content.replace("/사진=뉴스1 ","");
				content = content.replace("/사진=(왼쪽)뉴시스,","");
				content = content.replace("(서울=뉴스1)","");
				content = content.replace("(서울=뉴스1) = ","");
				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;
			case "tapa"	:
			{
				Elements element = doc.select(subsec);
				String content = element.text();

				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;
			case "hani"	:
			{
				Elements element = doc.select(subsec);
				String content = element.text();

				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;
			case "khan"	:
			{
				Elements element = doc.getElementsByTag(subsec);
				String content = element.text();

				info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
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
				//info_db1.add(content+"\n");//mainsec 예시) "div.mainArticle"
			}break;

		}

	}
	//내용 가져와서 DB 리스트에 저장

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
	// href 관련 변수 초기화
	//변수 초기화



	public static void AllinOne() {
		String url = "https://www.yna.co.kr/theme/topnews-history";//연합뉴스  탑뉴스 링크
		String url2 = "http://www.segye.com/news";//세계일보
		String url3 = "http://www.newdaily.co.kr/";//뉴스데일리
		String url4 = "https://news.mt.co.kr/theissue/";//머니투데이
		String url5 = "https://newstapa.org/recent";//뉴스타파
		String url6 = "http://www.hani.co.kr/arti/list.html";//한겨례
		String url7 = "http://www.khan.co.kr/bestnews/realtime_list.html";//경향
		String url8 = "https://www.mk.co.kr/news/bestclick/";//매일경제
		int a = 0;



		connect_url(url);
		crolling_href("div.news-con","[href]");// 연합뉴스 탑슈스 하이퍼링크 크롤링
		conhref("yunhap");
		insertCalum("연합뉴스");



		connect_url(url2);
		crolling_href("div#wps_layout2_box2","[href]");
		conhref("segye");

		insertCalum("세계일보");

	    /*
	     connect_url(url3);
		 crolling_href("article.nd-center-article","[href]");
		 conhref("daily");
		 insertCalum("뉴스데일리");*/
	    /*
		 connect_url(url5);
		 crolling_href("figure.article-image","[href]");
		 conhref("tapa");
		 insertCalum2("뉴스타파");
		 initVar();*/

		connect_url(url6);
		crolling_href("p.article-prologue","[href]");
		conhref("hani");
		insertCalum("한겨례");
		initVar();

		connect_url(url7);
		crolling_href("strong.hd_title","[href]");
		crolling_href("div.bestview_text_list","[href]");
		conhref("khan");
		insertCalum("경향");
		initVar();

		connect_url(url8);
		crolling_href("dt.tit","[href]");
		conhref("maeil");
		insertCalum("매일");
		initVar();


		connect_url(url4);
		crolling_href("strong.subject","[href]");
		conhref("moneyto");
		insertCalum("머니투데이");
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
		String url = "https://www.yna.co.kr/theme/topnews-history";//연합뉴스  탑뉴스 링크
		String url2 = "http://www.segye.com/news";//세계일보
		String url3 = "http://www.newdaily.co.kr/";//뉴스데일리
		String url4 = "https://news.mt.co.kr/theissue/";//머니투데이
		String url5 = "https://newstapa.org/recent";//뉴스타파
		String url6 = "http://www.hani.co.kr/arti/list.html";//한겨례
		String url7 = "http://www.khan.co.kr/bestnews/realtime_list.html";//경향
		String url8 = "https://www.mk.co.kr/news/bestclick/";//매일경제

		AllinOne();

		 /*for(String i:info_db1) {
			 int countnum =0;
			 System.out.println(countnum);
			 countnum ++;
			 System.out.println(i);}
		// insertCalum("머니투데이");
		 //System.out.println(info_db1.get(20));
		 initVar();*/
	}
}




