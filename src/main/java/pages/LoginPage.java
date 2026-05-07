package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class LoginPage extends BasePage {
	
	public WebDriver driver;
	
	@FindBy(xpath = "//input[@id='user-name']")
	WebElement userName;
	
	@FindBy(xpath="//input[@id='password']")
	WebElement userPassword;
	
	@FindBy(xpath="//input[@id='login-button']")
	WebElement loginBtn;
	
	@FindBy(xpath="//h3[@data-test=\"error\"]")
	WebElement errorMsg;
	
	//error msg --> //h3[@data-test="error"]
	//if sucessfully loggedin then locate --> //div[@class="app_logo"] --> get Text and check "  Swag Labs "

	@FindBy(xpath = "//div[@class='app_logo']")
	WebElement appLogo;

	public String getHomePageText() {
	    return appLogo.getText();
	}
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	public void addUserNameAndPassword(String name,String password){
		userName.sendKeys(name);
		userPassword.sendKeys(password);
	}

	public void clickLoginBtn() {
		loginBtn.click();
	}
	
	
	public String getErrorLoginMsg() {
	    try {
	        return errorMsg.getText();
	    } catch (Exception e) {
	        return "No Error";
	    }
	}
	
}

//	public String getErrorLoginMsg() {
//		
//			if(errorMsg.isDisplayed()) {
//				return errorMsg.getText();							
//			}else {
//				return "No Error";
//			}
//		
//	}