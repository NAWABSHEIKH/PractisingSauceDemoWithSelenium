package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

/*
 * This is my  checkout information page class where you will get following:
 * -> you will get field likes First Name,Last Name,Postal zip/Code
 * -> Continue and Remove  Button.
 * 
 */
public class CheckoutInformationPage extends BasePage{
	
	public CheckoutInformationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	//input[@id='first-name]
	//input[@id='last-name']
	//input[@id='postal-code']
	//input[@id='continue']
	//button[@id='cancel']
	//h3[@data-test='error']
	
	@FindBy(xpath="//input[@id='first-name']")
	WebElement fName;
	
	@FindBy(xpath="//input[@id='last-name']")
	WebElement lName;
	
	@FindBy(xpath="//input[@id='postal-code']")
	WebElement pCode;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement continueBtn;
	
	@FindBy(xpath="//button[@id='cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement errorMsg;
	
	@FindBy(xpath="//span[text()='Checkout: Your Information']")
	WebElement checkoutInformationTitle;
	
	
	public void locateThatElementJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void fillBuyerDetails(String fistname,String lastname,String postalCode) {
		this.locateThatElementJs(driver, fName);
		fName.sendKeys(fistname);
		lName.sendKeys(lastname);
		pCode.sendKeys(postalCode);
	}
	
	public void clickContinueBtn() {
		this.locateThatElementJs(driver, continueBtn);
		continueBtn.click();
	}
	
	public void clickCancelBtn() {
		this.locateThatElementJs(driver, cancelBtn);
		cancelBtn.click();
	}
	
	public String getErrorMessage() {
		if(errorMsg.isDisplayed()) {
			return errorMsg.getText();
		}else {
			return "No Such Error Found! All User details are entered correctly. ";
		}
	}
	
	public boolean checkCheckoutInformationDisplayeSuccessfully() {
		this.locateThatElementJs(driver, checkoutInformationTitle);
		return checkoutInformationTitle.isDisplayed();
	}
	
	
	
	

}
