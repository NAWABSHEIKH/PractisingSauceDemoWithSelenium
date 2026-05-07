package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class CheckoutComplete extends BasePage {
	
	public CheckoutComplete(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='Checkout: Complete!']")
	WebElement checkoutComplteTitle;
	
	@FindBy(xpath="//h2[@data-test='complete-header']")
	WebElement completeTitle;
	
	@FindBy(xpath="//button[@id=\"back-to-products\"]")
	WebElement backBtn;
	
	public boolean isCompleteTitleVisible() {
		return checkoutComplteTitle.isDisplayed();
	}
	
	public String getCompleteOrderSuccessfulMessage() {
		return completeTitle.getText();
	}
	
	public void clickBackButton() {
		backBtn.click();
	}
	
	
	

}
