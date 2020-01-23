package seleniumjavaframework;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSearchTest {
	
	
		static String googleSearch(String searchedString,int resultPosition) {
		
			// Optional. If not specified, WebDriver searches the PATH for chromedriver.
			String projectPath = System.getProperty("user.dir");
			System.setProperty("webdriver.chrome.driver", projectPath + "\\Drivers\\chromedriver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			
			//Open google engine and search for the given string
	        driver.get("http://www.google.com/xhtml");
	        WebElement searchBox = driver.findElement(By.name("q"));
	        searchBox.sendKeys(searchedString);
	        searchBox.submit();
	        
	        //store all google first page searched results
	        List <WebElement> listResult= driver.findElements(By.xpath("//div[@class= \"r\"]/a"));
	        //store the "People Also Asked" elements
	        List <WebElement> toRemove= driver.findElements(By.xpath("//*[(contains(@class,'kno-kp mnr-c'))]//descendant::div[@class='r']/a"));
	        
	        //Remove PAA Elements from the search results
	        List <WebElement> realResults = new ArrayList<WebElement>(listResult.size());
	        realResults.addAll(listResult);
	        realResults.removeAll(toRemove);
	        
	        String ResultUrl = realResults.get(resultPosition-1).getAttribute("href");
	        driver.get(ResultUrl);
	        String title = driver.getTitle();
	        driver.close();
	        return title;
	        
			}
        
        public static void main(String[] args){
        	String answer = googleSearch("lexisnexis",3);
        	System.out.print(answer);
	}
}