import java.util.regex.Pattern;


public class test1233 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String tes ="Starting on October 4, 2021, KCIJ-Newstapa reports the findings of the project “The Pandora Papers: Koreans who Fled to Tax Havens 2021.” It is a collaborative project led by the International\r\n"
				+ " Consortium of Investigative Journalists(ICIJ) which was joined by more than 600 journalists from all over the world. This global team of journalists has been investigating over 11.9 million documents leake\r\n"
				+ "d from 14 offshore service providers, including Trident Trust, Alcogal and IL SHIN CPA and IL SHIN Corporate Consulting. – Editor’s Note - A cross-border investigation by the Korea Center for Investigative\r\n"
				+ "Journalism(KCIJ)-Newstapa and International Consortium of Investigative Journalists(ICIJ) has confirmed for the first time that Lee Jae-yong, vice chairman of Samsung Electronics, set up an offshore company\r\n"
				+ " in the British Virgin Islands. The offshore shell company was found to be incorporated between March and May 2008. It overlaps the period when the allegation of Samsung’s slush funds, which had been expose\r\n"
				+ "d by the conglomerate's former chief lawyer Kim Yong-chul, triggered a full-scale investigation by special prosecutors in South Korea. Over the years, the Samsung group has been repeatedly alleged of creati\r\n"
				+ "ng and hiding slush funds overseas. But this is the first time that its head has been confirmed to have set up a shell company in a tax haven. An offshore shell company held by Samsung Group’s de facto chie\r\n"
				+ "f Lee Jae-yong was identified from the Pandora Papers documents. Documents about Lee’s offshore company came from the leaked files from Trident Trust, one of the world’s largest offshore service providers.\r\n"
				+ "In the database of Pandora Papers project, more than 200 files come out when searching with the keyword ‘Lee Jae Yong.’ After reviewing every single file that included the keyword ‘Lee Jae Yong,’ Newstapa f\r\n"
				+ "ound an individual named Lee Jae-yong who was born in 1968 and resides in Hannam 2-dong, Yongsan District, Seoul. His information matched with that of the Vice Chairman of Samsung Electronics. Newstapa also\r\n"
				+ " identified a passport copy, which was attached to one of the incorporation documents. It was a black-and-white and blurry copy sent through fax, but it was certainly the Samsung chief with the same date of\r\n"
				+ " birth and autograph ‘Jay Y. Lee.";
		String tes2  ="Starting on October 4, 2021, KCIJ-Newstapa reports the findings of the project “The Pandora Papers: Koreans who Fled to Tax Havens 2021.” It is a collaborative project led by the International\r\n ";
		String pattern = "^[[a-zA-Z]\s^[0-9]*$[!“”\'\\,\\-\\(\\)\\.\\:\\n\\s]]*$";
		boolean regex = Pattern.matches(pattern,tes);
        System.out.println(regex);
	}

}
