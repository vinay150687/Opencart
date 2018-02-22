package Com.OpenCart.Testcases;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import Com.OpenCart.ExtendReports.ExtentReportsBaseClass;
import Com.OpenCart.Pages.RegistrationPage;
import Com.OpenCart.Utils.DataDriven;
import Com.OpenCart.Utils.GenericMethods;

import com.relevantcodes.extentreports.LogStatus;

public class OpenCart_RegestrationTest3 extends ExtentReportsBaseClass {
	private static final String depends = null;
	private static final String dependsonMethods = null;
	private WebDriver driver;
	private Properties prop;
	private static int WAIT_TIME = 10000;
	RegistrationPage RegistrationPage;
	DesiredCapabilities capability;
	public String nodeURL = "http://10.159.34.191:4444/wd/hub";
	//@Parameters("browser")
	@BeforeTest
	//@Parameters("browser")
	public void intialize() throws MalformedURLException

	{ 
		
		/*System.setProperty("webdriver.chrome.driver",
				"D:\\ChangeHealthCare_Project\\Selenium Jars\\chromedriver.exe");
		driver = new ChromeDriver();*/
		
		
		capability = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        capability.setCapability(ChromeOptions.CAPABILITY, options);

        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL(nodeURL), capability);
	
		
	
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(WAIT_TIME, TimeUnit.MILLISECONDS);
		prop = new Properties();
		InputStream input;
		try {
			input = new FileInputStream(
					System.getProperty("user.dir")
							+ "/src/test/java/Com/OpenCart/TestSource/GlobalProperties.properties");
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* This is for launching the web applo */
	@Test(description = "Launch openCart application")
	public void openApp() throws InterruptedException {
		logger = extent.startTest("openApp");
		// Test Step :001
		driver.get(prop.getProperty("url"));
		System.out.println("opencart app launched");
		
		// logger.log(LogStatus.PASS, "Method \"openApp\" is passed");
		// Thread.sleep(5000);
		// driver.findElement(By.xpath("(//a[text()='login'])")).click();
		// Thread.sleep(3000);
	}

	/*---------------------------------------------------------------*/

	// Data Provider
	@DataProvider(name = "Mydatprovide")
	public static Object[][] ReadingData() throws IOException {
		Object[][] obj = DataDriven
				.readExcel(
						"Register",
						System.getProperty("user.dir")
								+ "/src/test/java/Com/OpenCart/TestSource/OpenCart_Data.xlsx");
		return obj;
	}

	@DataProvider(name = "Reviewproduct")
	public static Object[][] ReadingReviewData() throws IOException {
		Object[][] obj = DataDriven
				.readExcel(
						"review",
						System.getProperty("user.dir")
								+ "/src/test/java/Com/OpenCart/TestSource/OpenCart_Data.xlsx");
		return obj;
	}

	/* This method is for registration */
	@Test(dataProvider = "Mydatprovide", dependsOnMethods = "openApp")
	public void registration(String fname, String lname, String email,
			String Telephone, String fax, String Company, String companyid,
			String add1, String add2, String city, String Postcode,
			String Country, String Region, String Password,
			String confirmpassword) throws InterruptedException {

		String EmailAdd = System.nanoTime() + email;
		System.out.println(EmailAdd);
		logger = extent.startTest("registration");
		// //Test Step :002
		RegistrationPage = new RegistrationPage(driver);
		RegistrationPage.Register.click();
		GenericMethods GenericMethods = new GenericMethods();
		GenericMethods.myClick(RegistrationPage.Register);

		driver.findElement(By.xpath("//*[contains(text(),'account')]")).click();
		WebDriverWait wdw = new WebDriverWait(driver, 30);
		wdw.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath(".//*[@id='logo']/a/img")));
		// Test Step : 003

		// RegistrationPage.fnameR.sendKeys(fname);

		GenericMethods.SendText(RegistrationPage.fnameR, fname);
		GenericMethods.SleepW(1000);
		RegistrationPage.LnameR.sendKeys(lname);
		RegistrationPage.emailR.sendKeys(EmailAdd);
		RegistrationPage.telephoneR.sendKeys(Telephone);
		RegistrationPage.faxR.sendKeys(fax);
		RegistrationPage.companyR.sendKeys(Company);
		RegistrationPage.company_idR.sendKeys(companyid);
		RegistrationPage.address_1R.sendKeys(add1);
		RegistrationPage.address_2R.sendKeys(add2);
		RegistrationPage.city_R.sendKeys(city);
		RegistrationPage.postcode_R.sendKeys(Postcode);

		GenericMethods.DropDwnText(RegistrationPage.country_id_R, Country);
		GenericMethods.DropDwnText(RegistrationPage.zone_id_R, Region);

		RegistrationPage.Password_R.sendKeys(Password);
		RegistrationPage.confirmPassword_R.sendKeys(confirmpassword);

		// Validation Parts- 1st checkpoint
		String fname1 = RegistrationPage.fnameR.getAttribute("value");
		System.out.println("attribute data is" + fname1);
		Assert.assertEquals(fname, fname1, "first name validation unSuccessful");
		String lname1 = driver.findElement(By.name("lastname")).getAttribute(
				"value");
		System.out.println("attribute data is" + lname1);
		Assert.assertEquals(lname, lname1, "Last name validation unSuccessful");
		String eml = driver.findElement(By.name("email")).getAttribute("value");
		System.out.println("attribute data is" + eml);
		// Assert.assertEquals(email, eml, "Email validation unSuccessful");
		String tphone = driver.findElement(By.name("telephone")).getAttribute(
				"value");
		System.out.println("attribute data is" + eml);
		Assert.assertEquals(Telephone, tphone,
				"Telephone No validation unSuccessful");
		String faxno = driver.findElement(By.name("fax")).getAttribute("value");
		System.out.println("attribute data is" + faxno);
		Assert.assertEquals(fax, faxno, "Fax No validation unSuccessful");

		driver.findElement(By
				.xpath(".//*[@id='content']/form/div[4]/table/tbody/tr/td[2]/input[2]"));
		// Test Step: 004
		driver.findElement(By.name("agree")).click();

		// driver.findElement(By.xpath(".//*[@id='content']/form/div[5]/div/input[2]")).click();
		driver.findElement(By.className("button")).click();
		// Validation of 2nd Checkpoint
		WebDriverWait wdw1 = new WebDriverWait(driver, 30);
		wdw1.until(ExpectedConditions.visibilityOfElementLocated(By
				.xpath("//*[contains(text(),'Congratulations')]")));
		driver.findElement(By.xpath("//*[@id='header']/div[5]/a[1]")).click();
		// driver.findElement(By.xpath("//*[contains(text(),'Home')]")).click();
		// Clicking on samsung Galaxy tab of home page

		// Test Step: 005
		driver.findElement(By.xpath(".//*[@id='slideshow0']/a")).click();

		/*----------------------------------------------------*/
		logger.log(LogStatus.PASS, "Method \"registration\" is passed");
	}

	// Test Step: 006 : Test Step: 007 : // Test Step: 008
	@Test(dataProvider = "Reviewproduct", dependsOnMethods = "registration")
	public void reviewProd(String Yourname, String Rating, String Review1)
			throws InterruptedException {
		logger = extent.startTest("reviewProd");
		driver.findElement(By.xpath("//*[contains(text(),'Reviews')]")).click();
		// passing the data from excel
		driver.findElement(By.name("name")).clear();
		driver.findElement(By.name("name")).sendKeys(Yourname);
		driver.findElement(By.xpath(".//*[@id='tab-review']/textarea")).clear();
		driver.findElement(By.xpath(".//*[@id='tab-review']/textarea"))
				.sendKeys(Review1);
		int RatingInt = Integer.parseInt(Rating);
		driver.findElement(
				By.xpath(".//*[@id='tab-review']/input[" + (RatingInt + 1)
						+ "]")).click();
		System.out.println("Please Enter Captha:");
		 
		/*Scanner sc = new Scanner(System.in);
		String captcha = sc.nextLine();*/
		//driver.findElement(By.xpath(".//*[@name='captcha']")).clear();
		/*driver.findElement(By.xpath(".//*[@name='captcha']")).sendKeys(captcha);*/
		
		Thread.sleep(10000);
		driver.findElement(By.id("button-review")).click();
		Thread.sleep(3000);
		System.out.println("length of the review1 is " + Review1.length());
		if (Review1.length() < 20) {
			String Warningmsg = driver.findElement(
					By.xpath(".//div[@id='tab-review']/div[2]")).getText();
			System.out.println("The message is " + Warningmsg);
			Assert.assertEquals(
					"Warning: Review Text must be between 25 and 1000 characters!",
					Warningmsg);
			System.out.println("validation is not successsfull");
		} else {
			String Warningmsg = driver.findElement(
					By.xpath(".//div[@id='tab-review']/div[2]")).getText();
			System.out.println("The message is " + Warningmsg);
			System.out.println("Hello");
			Assert.assertEquals(
					"Thank you for your review. It has been submitted to the webmaster for approval.",
					Warningmsg);
			System.out.println("validation is successsfull");
		}
		logger.log(LogStatus.PASS, "Method \"reviewProd\" is passed");
	}

	// Test Step: 009, 10
	@Test(description = "add to Wish list", dependsOnMethods = "reviewProd")
	public void addToWishList() throws InterruptedException, IOException {
		logger = extent.startTest("addToWishList");
		driver.findElement(By.linkText("Add to Wish List")).click();
		Thread.sleep(5000);
		/*
		 * WebDriverWait wdw2=new WebDriverWait(driver,100);
		 * wdw2.until(ExpectedConditions
		 * .elementToBeClickable(By.xpath(".//*[@id='notification']/div/img"
		 * ))).click();
		 */
		// driver.findElement(By.xpath("//div[@id='notification']/div/img")).click();
		Thread.sleep(3000);
		// This is a Wish list link // Test Step: 011
		driver.findElement(By.id("wishlist-total")).click();
		/*
		 * Checkpoint to validate the product number in the link and check point
		 * to validate number of table records in the table - and both had to be
		 * compared for the success.
		 */
		String wishlisttext = driver.findElement(By.id("wishlist-total"))
				.getText();
		System.out.println("The text of the Wishlist is " + wishlisttext);
		String wishListItems = wishlisttext.substring(11,
				wishlisttext.length() - 1);

		int wishListItemInt = Integer.parseInt(wishListItems);
		System.out.println("wishList Items are:" + wishListItems);

		List<WebElement> WishlistCount = driver.findElements(By
				.xpath(".//*[@id='content']/div[2]/table/tbody"));
		System.out.println("List Size is:  " + WishlistCount.size());

		Assert.assertEquals(WishlistCount.size(), wishListItemInt,
				"WishList Items are matching");

		// Writing to a flat file

		// Test Step: 012 ,13
		driver.findElement(By.xpath(".//*[@id='currency']/a[2]")).click();
		File myfile = new File(System.getProperty("user.dir")
				+ "/target/OutputFile/UnitPrice.txt");
		String sterling = driver.findElement(
				By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
		System.out.println("Unit Price is" + sterling);
		BufferedWriter pw = new BufferedWriter(new FileWriter(myfile));
		pw.write("The Unit price is " + sterling);

		// Test Step: 014 ,15
		driver.findElement(By.xpath(".//*[@id='currency']/a[1]")).click();
		String euro = driver.findElement(
				By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
		System.out.println("Unit Price is" + euro);
		pw.newLine();
		pw.append("The Unit price is " + euro);

		// Test Step: 016 ,17
		driver.findElement(By.xpath(".//*[@id='currency']/a[3]")).click();
		String dollar = driver.findElement(
				By.xpath(".//*[@id='wishlist-row49']/tr/td[5]/div")).getText();
		System.out.println("Unit Price is" + dollar);
		pw.newLine();
		pw.append("The Unit price is " + dollar);
		pw.close();

		// Test Step: 018
		driver.findElement(By.xpath(".//*[@id='wishlist-row49']/tr/td[6]/img"))
				.click();
		WebDriverWait wdw3 = new WebDriverWait(driver, 30);
		wdw3.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath(".//*[@id='notification']/div/img"))).click();
		driver.findElement(
				By.xpath(".//*[@id='wishlist-row49']/tr/td[6]/a/img")).click();
		WebDriverWait wdw4 = new WebDriverWait(driver, 30);
		wdw4.until(
				ExpectedConditions.elementToBeClickable(By
						.xpath(".//*[@id='container']/div[4]/img"))).click();
		driver.findElement(By.xpath(".//*[@id='content']/div[3]/div/a"))
				.click();
		driver.findElement(By.xpath(".//*[@id='welcome']/a[2]")).click();
		// Checkpoint for logout message
		String logout = driver.findElement(By.xpath("//*[@id='content']/h1"))
				.getText();
		System.out.println(logout);
		Assert.assertEquals("Account Logout", logout);
		// Checkpoint for login message
		String loginmessage = driver.findElement(
				By.xpath("//div[@id='welcome']/a[1]")).getText();

		System.out.println(loginmessage);
		Assert.assertEquals("login", loginmessage, "Validation is not passed");
		driver.close();
		logger.log(LogStatus.PASS, "Method \"addToWishList\" is passed");
	}

}
