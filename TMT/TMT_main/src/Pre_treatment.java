import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import org.snu.ids.*;
import org.snu.ids.kkma.index.Keyword;
import org.snu.ids.kkma.index.KeywordExtractor;
import org.snu.ids.kkma.index.KeywordList;

public class Pre_treatment {

	protected static String [] SC = { "!","@","#","$","%","^","&","*","(",")","_","-","=","+","[","{","]","}",";",":","\"","\'",">","<",",",".","/","?","\'","\"","`","~"};
	public static ArrayList<String> RepetitionWord1 = new ArrayList<>() ;
	public static ArrayList<String> slicewords = new ArrayList<String>();
	public static String wordsmean;
	public static String wordsshape;
	public static ArrayList<String> wordslist = new ArrayList<String>();
	public static ArrayList<String> LastSentence = new ArrayList<String>();
	public static ArrayList<String> newSentence = new ArrayList<String>();//키워드를 통해 추출된 문장
	//조사까지 끼우고 가장   작은 갯수(2개 부터)의 단어를 기준으로 포함되어있는 문장을 리스트에 저장
	
	public static void slicesentence(String keywords) {//다.을 통해 문장을 나누는 작업
		
		int sentencenumber = 1;
		int countnum = 0;
		ArrayList<String> jungbok = new ArrayList<String>(); 
		
		
		
		DBconnection abc23 = new DBconnection();
		abc23.getConnection3();
		abc23.readCalumdata2(keywords);
		
		for ( int i  = 0;i<abc23.contentText.size();i++) {
			
		
			String a [] = abc23.contentText.get(i).split("다[.]");
			
			 for(String insertdata : a) {
			    	insertdata = insertdata+"다";
				if( insertdata.contains(keywords))
				{
					newSentence.add(insertdata);
					
				}
				
			 }
		}	
		
	}
	public static void testDuplicate(String keywords) {
		ArrayList<String> checklist = new ArrayList<String>();
		int count =0;
		
		for(int countj =0;countj < newSentence.size();countj++) 
		{
			 KeywordExtractor ke = new KeywordExtractor();
	         // extract keywords
	         KeywordList kl = ke.extractKeyword(newSentence.get(countj), true);
	         // print result
	         for( int i = 0; i < kl.size(); i++ ) {
	        	 Keyword kwrd = kl.get(i);
	        	 if(checklist.size()==0) {
	        		
	        	 checklist.add(kwrd.getString());}
	        	 else {
	        		 for(String index : checklist) {
	        			 if(kwrd.getString()==index) {
	        				
	        				 count++; 
	        			 }
	        		 }
	        		 checklist.add(kwrd.getString());
	        	 }
	         }
	         if(count>5) {
	        	 count=0;
	        	 continue;
	         }
	        DBconnection abc23 = new DBconnection();
	 		abc23.getConnection3();
	 		abc23.insertLast(keywords,newSentence.get(countj));
	 		System.out.println(newSentence.get(countj));
	         LastSentence.add(newSentence.get(countj));
	         
			
		}
		newSentence.clear();
	}
	
	public static void selectsentece(String a){//순위를 매긴 wordsdata의 내용을 통해 문자 선택
		DBconnection abc23 = new DBconnection();
		abc23.getConnection3();
		abc23.readSentence(a);
		for (String abd: abc23.sentenceText) {
			
		
		}
		
		
	}
	
	
	
	public static void slicewords() {// 단어를 잘라내는 작업 후 kkoma통해 명사 추출
			
		DBconnection abc23 = new DBconnection();
		abc23.getConnection3();
		abc23.readCalumdata();
		
		//System.out.println(abc23.contentText);
		//System.out.println(abc23.contentText.get(1));
		String Rep = null;
		String Rep2 = null;
		
		//p = Pattern.compile(SC);
	
		
		for ( int i  = 0;i<abc23.contentText.size();i++) {
		 Rep = abc23.contentText.get(i).replace(SC[0],"") ;
	
			for (int j = 1 ; j < SC.length; j++) {
				
				
				Rep= Rep.replace(SC[j],"");
				
			}
			
				Rep = Rep.replace("▶ 여기를 누르시면 크게 보실 수 있습니다","");
				Rep = Rep.replace("무단전재","");
				Rep = Rep.replace("재배포","");
				Rep = Rep.replace("Copyrights ⓒ 연합뉴스 무단 전재 및 금지","");
				Rep = Rep.replace("ⓒ 매일경제  mkcokr  및  금지","");
				
				
			RepetitionWord1.add(Rep);
			Rep = null;
		}//특수문자 제거 처리
		

	
		for(int k =0; k < RepetitionWord1.size(); k++) {
			
			
			
			String a [] =RepetitionWord1.get(k).split(" ");
		
				for(int i = 0 ; i < a.length; i++) {
			
			
						slicewords.add(a[i]);
			
			
					} a= null;
		/*for(int l= 0 ;l<a.length-1;l++)
			
			
			if(test[k][0]!=null) {
			
				test [k+1][a.length]= a[l];
			}
			else {
			test [k][a.length]=a[l];
		
		}
		}*/
	
		
		
		/*for (String i : abc23.contentText) {
			String a = ;
			System.out.println(abc23.contentText.get(i));
			
			if(	abc23.contentText.contains(match)) {
				
				//String a = abc23.contentText.toString();
				
			
				
			}
			
		}	*/							
		}
	}
	
	public static void kkomatest (ArrayList<String> wordslist) {// kkoma를 이용한 명사 추출
		
		int countnum = 1;
		try {
		for(String strToExtrtKwrd:wordslist  ) {
		
		
         // init KeywordExtractor
         KeywordExtractor ke = new KeywordExtractor();
         // extract keywords
         KeywordList kl = ke.extractKeyword(strToExtrtKwrd, true);
         // print result
         for( int i = 0; i < kl.size(); i++ ) {
               Keyword kwrd = kl.get(i);
              
               if(kwrd.getString().length()==1) {
   				continue;
   				}
   		
   				else {
   						DBconnection abc = new DBconnection();
   						abc.getConnection3();
   						abc.insertwordsDB(countnum,kwrd.getString(),kwrd.getCnt());
   			
   						System.out.println(kwrd.getString() + "\t" + kwrd.getCnt());
   				 
   						countnum++;
   						}
		
         			}
				}
		
			DBconnection abc = new DBconnection();
			abc.getConnection3();
			abc.readWordsdata();
			}catch(NullPointerException e) {
			e.printStackTrace();
		}
	} 
	public static void jungbokSentence (String keywords) throws SQLException {// kkoma를 이용한 명사 추출
		
		ArrayList<String> jungbok = new ArrayList<String>(); 
		int countnum = 0;
		String key = keywords;
		DBconnection abc2 = new DBconnection();
		 abc2.getConnection3();
		 abc2.readSentence(keywords);
		 
		 for(String contents:abc2.sentenceText) {
			
			   // init KeywordExtractor
	         KeywordExtractor ke = new KeywordExtractor();
	         // extract keywords
	         KeywordList kl = ke.extractKeyword(contents, true);
	         // print result
	         for( int i = 0; i < kl.size(); i++ ) {
	        	    Keyword kwrd = kl.get(i);
	        	    for(String jungbokWords:jungbok) {
	        	    	
	        	    	if(kwrd.getString()==jungbokWords) countnum++;
	        	    }
	        	
	        	  
	        	    jungbok.add(kwrd.getString());
	        	    
	        	   
	        	}
	         if(countnum >= 2) 
 	    	{
 	    		jungbok.clear();
 	    		countnum = 0;
 	    		continue;
 	    	}
	         LastSentence.add(contents);
	         int countnum2 =0;
	        while(LastSentence.size()>11) {
	        	ArrayList<String> jungbok2 = new ArrayList<String>(); 
	   		 	abc2.getConnection3();
	   		 	
	   		 	for(String contents2 :LastSentence ) {
	   		 		
	   		 	 KeywordExtractor ke2 = new KeywordExtractor();
		         // extract keywords
		         KeywordList kl2 = ke2.extractKeyword(contents2, true);
		         // print result
		         for( int i = 0; i < kl2.size(); i++ ) {
		        	    Keyword kwrd = kl2.get(i);
		        	 
		        	    jungbok2.add(kwrd.getString());        	    
		        	   
		        	}
		         abc2.getConnection3();
		         KeywordExtractor ke3 = new KeywordExtractor();
		        
		         // extract keywords
		         KeywordList kl3 = ke3.extractKeyword(abc2.plusSentence(key), true);
		         // print result
		         for( int i = 0; i < kl3.size(); i++ ) {
		        	    Keyword kwrd2 = kl3.get(i);
		        	 
		        	  for(String b : jungbok2) {
		        		  
		        		  if(b == kwrd2.getString()) { 
		        			  countnum2++;
		        		  }
		        	  }      	    
		        	   
		        	}
		     
	        
	       
			 System.out.println("되고있는가요?");
	   		 		}
	   		 if(countnum2 >= 2) 
	 	    	{
		        	
	 	    		countnum = 0;
	 	    		continue;
	 	    	}
	        	}
	       
	        }
		 }
	        
		 
	
		

	
	
	public static  void checkingDictionary(String words) {//사전 api를 통한 명사추출 
		
		String url = "https://opendict.korean.go.kr/api/search?certkey_no=2440&key=33FA287C1FC2F0F4222C716DC8EFAA36&target_type=search&part=word&q=";
		String searchword = words; 
		String query = null;
		int bugcounter = 0;
		try {
			   query = URLEncoder.encode(searchword,"UTF-8");
			  } catch (UnsupportedEncodingException e) {
			   e.printStackTrace();
			  }  
		
		
		
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = factory.newDocumentBuilder();
			Document document = documentBuilder.parse(url+query);
			
			Element root = document.getDocumentElement();
			
			NodeList test = root.getElementsByTagName("word");
			NodeList test2 = root.getElementsByTagName("pos");
			Node ntet = (Node) test.item(0);
			Node ntet2 = (Node) test2.item(0);
			
			wordsmean = ntet.getTextContent();
			wordsshape = ntet2.getTextContent();
			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SAXException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}catch(NullPointerException e) {
			int test = searchword.length()-1;
			if(test>0)
			checkingDictionary(searchword.substring(0,test));
			else ;
		}
		
		
		
	
		
	}// 가져올 항목 체킹 해서 다시 조절 지금은 단어명만 가져오게끔 되어있음
	
	public static void Normalization() {//사전 api를 통한 명사추출 2

		for( int a = 0 ; a < slicewords.size();a++) {
			checkingDictionary(slicewords.get(a));
				if(wordsmean.length()==1||wordsmean.contains("^")) {
				continue;
				}
			
			
			
				else {
					DBconnection abc = new DBconnection();
					abc.getConnection3();
					abc.inserDBdata(a,wordsmean);
			
					System.out.println(wordsmean+" "+wordsshape);
		//	if(wordsmean == null) {
				}	
				/*
				String bbc = slicewords.get(a);
				bbc	= bbc.substring(0,bbc.length()-1);
				checkingDictionary(bbc);
				*/
			}
		
		/*for(String i : wordslist) {
			int wordsnumber = 0 ;
			
			DBconnection abc = new DBconnection();
			abc.getConnection3();
			abc.inserDBdata(wordsnumber,i);
			
			wordsnumber = wordsnumber+1;
			
			
		}*/
	//	}//단어명이 나오지않을때 뒤에서부터 잘라서 다시검색하는거 만드셈
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBconnection abc = new DBconnection();
		abc.getConnection3();
		
		
		
		
		
		
		
		for(String a : abc.readKeywords()) {
			
			slicesentence(a);
			testDuplicate(a);
		}
	
		
		
		//for(String i : LastSentence) {System.out.println(i);}
}
}
	
	
	
