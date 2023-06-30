package testng;

import java.time.Duration;
import java.util.List;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.JavascriptExecutor;

public class Assignment {
	private WebDriver driver;
	public static String baseUrl = "https://www.tokopedia.com/";

	@BeforeTest
	@Parameters("browser")
	public void setUp(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		// Open homepage
		driver.get(baseUrl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	}

	@Test
	public void testWeb() throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(3000);

		// Search “iphone 14 pro”
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("iphone 14 pro" + Keys.RETURN);
		Thread.sleep(3000);

		// Filter the price with min. 100.000
		driver.findElement(By.xpath("(//i[contains(@class,'css-576gz2')])[1]")).click();
		driver.findElement(By.xpath("(//i[contains(@class,'css-576gz2')])[2]")).click();
		driver.findElement(By.xpath("//input[contains(@name,'pmin')]")).sendKeys("100000" + Keys.RETURN);
		Thread.sleep(3000);

		// Filter the price with min. 100.000, max 30.000.000
		driver.findElement(By.xpath("(//i[contains(@class,'css-576gz2')])[1]")).click();
		driver.findElement(By.xpath("(//i[contains(@class,'css-576gz2')])[2]")).click();
		driver.findElement(By.xpath("//input[contains(@name,'pmax')]")).sendKeys("30000000" + Keys.RETURN);
		Thread.sleep(3000);

		// Filter only from the “Official Store”
		driver.findElement(By.xpath("(//span[contains(@class,'area e3y1k2n1')])[1]")).click();
		Thread.sleep(3000);

		// Sort from the “Harga Terendah”
		driver.findElement(By.xpath("(//p[@data-unify='Typography'][contains(.,'Paling Sesuai')])[1]")).click();
		driver.findElement(By.xpath("//p[@data-unify='Typography'][contains(.,'Harga Terendah')]")).click();
		Thread.sleep(3000);

		// Scroll down to get all the items on page 1
		js.executeScript("window.scrollBy(0,1000)");
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,4000)");
		Thread.sleep(3000);
		// Find all the item names on the page 1
		List<WebElement> pageOneItemNames = driver.findElements(By.xpath("//div[@data-testid='spnSRPProdName']"));
		// Print out the names of all the items on page 1
		System.out.println("----------LIST OF ITEM NAMES FOR PAGE 1----------");
		for (WebElement itemName : pageOneItemNames) {
			System.out.println(itemName.getText());
		}

		// Direct to page 2
		driver.findElement(By.xpath("//button[@class='css-bugrro-unf-pagination-item'][contains(.,'2')]")).click();
		Thread.sleep(3000);
		// Scroll down to get all the items on page 2
		js.executeScript("window.scrollBy(0,4000)");
		Thread.sleep(3000);
		// Find all the item names on the page 2
		List<WebElement> pageTwoItemNames = driver.findElements(By.xpath("//div[@data-testid='spnSRPProdName']"));
		// Print out the names of all the items on page 2
		System.out.println("----------LIST OF ITEM NAMES FOR PAGE 2----------");
		for (WebElement itemNameTwo : pageTwoItemNames) {
			System.out.println(itemNameTwo.getText());
		}

		// Direct to page 3
		driver.findElement(By.xpath("//button[@class='css-bugrro-unf-pagination-item'][contains(.,'3')]")).click();
		Thread.sleep(3000);
		// Scroll down to get all the items on page 3
		js.executeScript("window.scrollBy(0,3000)");
		Thread.sleep(3000);
		// Find all the item names on the page 3
		List<WebElement> pageThreeItemNames = driver.findElements(By.xpath("//div[@data-testid='spnSRPProdName']"));
		// Print out the names of all the items on page 3
		System.out.println("----------LIST OF ITEM NAMES FOR PAGE 3----------");
		for (WebElement itemNameThree : pageThreeItemNames) {
			System.out.println(itemNameThree.getText());
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		// Quit browser
		driver.quit();
	}
}
