import org.apache.poi.ss.formula.functions.T;
import org.apache.xmlbeans.impl.common.XPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Google {// class head 
	

	// Variables 
	private String Url = "https://translate.google.com/?hl=iw&tab=TT&authuser=0#view=home&op=translate&sl=en&tl=iw";
	private WebDriver chrome;
	
	// Constructor 
	public Google() {
		System.setProperty("webdriver.chrome.driver", "/Users/nassarmahmood/Documents/chromedriver3");
		this.chrome = new ChromeDriver();
		getPage(this.Url);
		
	}
	
	
	String Translate(String wordToTranslate) throws InterruptedException { //start method
	
		String str;
		//clear
		this.chrome.findElement(By.xpath("//*[@id=\"source\"]")).clear();;
		//wait
		Thread.sleep(1000);
		//send to translate
		this.chrome.findElement(By.xpath("//*[@id=\"source\"]")).sendKeys(wordToTranslate);
		// wait 
		Thread.sleep(2000);
		// get the translated word
		str=this.chrome.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div[2]")).getText();
		
		return str;
	}//end method Translate
	
	
	// get google translate page
	private void getPage(String Url) { //start method
		this.chrome.get(Url);
		
	}//end method get page
	
	// finally close the page 
		public void closePage() throws InterruptedException {// start method to close page
			// wait if the program still copying 
			Thread.sleep(500);
			//close ///
			this.chrome.close();
			this.chrome.quit();
			
		}//end close page method 
		
		
	
	
}// end the class 
