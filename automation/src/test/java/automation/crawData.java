package automation;
import org.testng.annotations.Test;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import setup.SetUp;



public class crawData extends SetUp{

	
	static int endPageNum;
	
	List<WebElement> listAll = new ArrayList<WebElement>();	
	
	
	
	@Test
	public void Crawl_01 () throws InterruptedException {
			
		
		driver.findElement(By.xpath("//a[@class='btn nextL']")).click();
		Thread.sleep(2000);
		System.out.println(driver.getCurrentUrl());
		endPageNum = Integer.parseInt(driver.findElement(By.xpath("//div[@class='paging']/a[@class='selected']")).getText());
		System.out.println("Number of pages : "+endPageNum);

		
		LinkedList<Video> videoList = new LinkedList<Video>();
		String currentURL = driver.getCurrentUrl();
		
		int currentPage = endPageNum;
		
		while(currentPage!=0) {
			
			driver.get(currentURL);
			
			List<WebElement> list = new ArrayList<WebElement>();	
			list = (List<WebElement>) driver.findElements(By.xpath("//ul[@class='videolist']/li/a[2]"));
						
			for(int j = 0; j<list.size(); j++) {
				
				String url = list.get(j).getAttribute("href");
				String title = list.get(j).getText();
				String country = findCountry(title);
				videoList.add(new Video(title, url, country));
				
			}
			
			currentURL = currentURL.replaceFirst("page="+currentPage, "page="+(currentPage-1));
			currentPage -= 1;
			
		}

		Iterator<Video> iter = videoList.iterator();
		
		while(iter.hasNext())
		{
			Video video = iter.next();			
			String title = video.title;
			String url = video.url;
			String country = video.country;
			
			System.out.println(title +  "-> " + url + "->"+country);
		}
		
			
		driver.quit();
    }
	
	public String findCountry (String title) {
		
		if(title.contains("베트남") || title.contains("하노이") ) {
			return "베트남";
		}else if(title.contains("멕시코")) {
			return "멕시코";

		}else if(title.contains("터키") || title.contains("이스탄불")) {
			return "터키";

		}else if(title.contains("시안") || title.contains("섬서성")) {
			return "중국(시안)";
			
		}else if(title.contains("미국") || title.contains("뉴욕") || title.contains("뉴요")) {
			return "미국";
		}else if(title.contains("타이베이") || title.contains("타이완")) {
			return "대만";
		}else if(title.contains("우한") || title.contains("후베이")) {
			return "중국(우한)";
		}else if(title.contains("시칠리아") || title.contains("부치리아") || title.contains("팔레르모")) {
			return "이태리(시칠리아)";
		}else if(title.contains("연변") || title.contains("연길")) {
			return "중국(연변)";
		}else if(title.contains("페낭") || title.contains("말레이시아")) {
			return "말레이시아(페낭)";
		}else {
			return "기타";

		}

		
	}
	
}

class Video {
	String title;
	String url;
	String country;
	
	Video(String t, String u, String c)
	{
		this.title = t;
		this.url = u;
		this.country = c;
	}	
}
