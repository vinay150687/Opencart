package Com.OpenCart.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {
	
	public  void DropDwnText(WebElement element,String Text){
		
		
		Select obj = new Select(element);
		obj.selectByVisibleText(Text);
	}
	
	
	public  void DropDwnByIndex(WebElement element,int index){
		
		
		Select obj = new Select(element);
		obj.selectByIndex(index);
	}
	
	public void myClick(WebElement element){
		
		element.click();
		
	}
	
public void SendText(WebElement element,String data){
		
		element.sendKeys(data);
		
	}

public void ExplicitlyWait(WebDriver driver,By element1){
	WebDriverWait wdw = new WebDriverWait(driver, 30);
	wdw.until(ExpectedConditions.visibilityOfElementLocated(element1));
	
	
}

public void SleepW(int Time) throws InterruptedException{
	
	Thread.sleep(Time);
	
}

public void pickTheText(WebElement Myelement){
	
	Myelement.getText();
	
	
	
	
}
	
}
