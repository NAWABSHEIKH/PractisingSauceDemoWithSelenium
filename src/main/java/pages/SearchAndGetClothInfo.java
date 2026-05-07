package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BasePage;


public class SearchAndGetClothInfo extends BasePage{

	
	public SearchAndGetClothInfo(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	/*
	 * 1. Add endpoint to  locate the element.
	 * 2. write method to perform action.
	 * 
	 * */
	
	@FindBy(xpath = "//div[@class=\'inventory_item_name \']")
	List<WebElement> getClothTilte;
	
	@FindBy(xpath="//div[@class=\'inventory_details_desc large_size\']")
	WebElement getClothInfo;
	
	@FindBy(xpath="//div[@class=\'inventory_details_price\']")
	WebElement getPriceInfo;
	
	@FindBy(xpath="//button[@id='add-to-cart']")
	WebElement addToCart;
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement shoppingCartLink;
	
	@FindBy(xpath="//button[@id='remove']")
	WebElement removeBtn;
	
	//button[contains(@id, 'add-to-cart')]
	//button[text()='Add to cart']
	
	
	//div[@class='inventory_item_description'] // --> all description for product 
	@FindBy(xpath="//div[@class='inventory_item_description']")
	List<WebElement> allDescriptionForProduct;
	
	
public void searchMutipleCloth(ArrayList<String> clothes) {
		int count=0;
		for(String cloth:clothes) {
			
			for(WebElement description: allDescriptionForProduct) {
				String appearCloth=description.findElement(By.xpath(".//div[@class=\'inventory_item_name \']")).getText();
				
				if(cloth.equalsIgnoreCase(appearCloth)) {
					//System.out.println("Appeared Cloth: "+appearCloth +" "+"User Cloth searched: "+ appearCloth);
					description.findElement(By.xpath(".//button[text()='Add to cart']")).click();
					System.out.println("Added item: " + cloth);
					System.out.println(count++);
					break;
				}
			}	
		}
	}
	
	
	
	
	public void locateThatElementJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void searchCloth(String name) {
			boolean isFound=false;
			for(WebElement c:getClothTilte) {
//				System.out.println(c.getText()+"--->"+name);
				if(c.getText().equalsIgnoreCase(name)) {
					isFound=true;
					this.locateThatElementJs(driver, c);
					Assert.assertEquals(c.getText(), name);
					c.click();
					System.out.println("Cloth Found.");
					break;
				}
			}
			
			if(isFound==false) {
				System.out.println("Cloth Not Matched.");
				Assert.fail("Test failed because the element was not found: " + name);
			}			
	}
	
	
	public String getClothInfo() {
		return getClothInfo.getText();
	}
	
	public String getClothPrice() {
		return getPriceInfo.getText();
	}
	
	public void clickAddToCart() {
		if(addToCart.isDisplayed()) {
			this.locateThatElementJs(driver, addToCart);
			addToCart.click();
		}
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(removeBtn));
		Assert.assertTrue(removeBtn.isDisplayed());
	}
	
	
	
	
	
	
	
	
	
	

}
