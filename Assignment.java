import java.time.Duration;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
	}

	@AfterTest
	public void tearDown() throws Exception {
		// Quit browser
		driver.quit();
	}
}
