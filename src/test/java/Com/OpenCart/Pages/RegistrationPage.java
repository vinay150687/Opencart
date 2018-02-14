package Com.OpenCart.Pages;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {
	
	

	WebDriver driver;
	public  RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//a[@href='http://10.207.182.108:81/opencart/index.php?route=account/register']")
	public  WebElement Register;
	@FindBy(name="firstname") public  WebElement fnameR;
	@FindBy(name="lastname") public  WebElement LnameR;
	@FindBy(name="email") public  WebElement emailR;
	@FindBy(name="telephone") public  WebElement telephoneR;
	@FindBy(name="fax") public  WebElement faxR;
	@FindBy(name="company") public  WebElement companyR;
	@FindBy(name="company_id") public  WebElement company_idR;
	@FindBy(name="address_1") public  WebElement address_1R;
	@FindBy(name="address_2") public  WebElement address_2R;
	@FindBy(name="city") public  WebElement city_R;
	@FindBy(name="postcode") public  WebElement postcode_R;
	@FindBy(name="password") public  WebElement Password_R;
	@FindBy(name="confirm") public  WebElement confirmPassword_R;
	@FindBy(name="country_id") public  WebElement country_id_R  ;
	@FindBy(name="zone_id") public  WebElement zone_id_R;
}
	