package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BasePage;

public class CheckoutOverviewPage extends BasePage {
	
	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@data-test='payment-info-value']")
	WebElement paymentInformation;
	
	@FindBy(xpath="//div[@data-test='shipping-info-value']")
	WebElement shippingInformation;
	
	@FindBy(xpath="//div[@data-test='subtotal-label']")
	WebElement listedProductCost;
	
	@FindBy(xpath="//div[@data-test='tax-label']")
	WebElement taxOnProduct;
	
	@FindBy(xpath="//div[@data-test='total-label']")
	WebElement totalCostWithTax;
	
	@FindBy(xpath="//button[@id='finish']")
	WebElement finishBtn;
	
	@FindBy(xpath="//button[@id='cancel']")
	WebElement cancelBtn;
	
	@FindBy(xpath="//span[text()='Checkout: Overview']")
	WebElement checkoutOverviewTitle;
	
	public void locateThatElementJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public String getPaymentInformation() {
		this.locateThatElementJs(driver, paymentInformation);
		return paymentInformation.getText();
	}
	
	public String getShippmentInformation() {
		return shippingInformation.getText();
	}
	
	public double convertStringToDouble(String amount){
		String amt=amount.replaceAll("[^0-9.]", "");
		return Double.parseDouble(amt);	
	}
	
	public double getListedProductCost(){
		String totalProductCost=listedProductCost.getText();
        Double totalProductCostInNumber=this.convertStringToDouble(totalProductCost);
        return totalProductCostInNumber;  
	}
	
	public double getTaxOnProduct(){
		String totalTax=taxOnProduct.getText();
        Double totalTaxInNumber=this.convertStringToDouble(totalTax);
        return totalTaxInNumber;  
	}
	
	public double getTotalCostWithTax(){
		String totalCostWithTax1=totalCostWithTax.getText();
        Double totalCostWithTaxInNumber=this.convertStringToDouble(totalCostWithTax1);
        return totalCostWithTaxInNumber;  
	} 
	
	public void clickButton(){
		this.locateThatElementJs(driver, finishBtn);
		finishBtn.click();
	}
	
	public void clickRemove(){
		this.locateThatElementJs(driver, cancelBtn);
		cancelBtn.click();
	}
	
	public boolean isCheckoutOverviewTitleVisible() {
		this.locateThatElementJs(driver,checkoutOverviewTitle);
		return checkoutOverviewTitle.isDisplayed();
	}
}
