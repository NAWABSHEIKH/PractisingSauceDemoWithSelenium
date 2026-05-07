package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.BasePage;

public class CartPage extends BasePage{

	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//a[@class='shopping_cart_link']")
	WebElement addToCartLogo;
	
	@FindBy(xpath="//div[@class='cart_item']")
	List<WebElement> cartItems; //list all cart item
	
	@FindBy(xpath="//div[@class='cart_quantity']")
	WebElement productQuantity;
	
	@FindBy(xpath="//div[@class='cart_item_label']")
	List<WebElement> productLabel; //list all title,description,prices,remove of the product.
	
	@FindBy(xpath="//div[@class='inventory_item_name']")
	WebElement productName;
	
	@FindBy(xpath="//div[@class='inventory_item_desc']")
	WebElement productDescription;
	
	@FindBy(xpath="//div[@class='item_pricebar']")
	WebElement productPriceBar;
	
	@FindBy(xpath="//div[@class='inventory_item_price']")
	WebElement productItemPrice;
	
	@FindBy(xpath="//button[@id='remove-sauce-labs-onesie']")
	WebElement productItemRemove;
	
	@FindBy(xpath="//button[@id='checkout']")
	WebElement checkoutBtn;
	
	@FindBy(xpath="//button[@id='continue-shopping']")
	WebElement continueShoppingBtn;
	
	
	public void locateThatElementJs(WebDriver driver,WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public void clickAddToCart() {
		this.locateThatElementJs(driver, addToCartLogo);
		addToCartLogo.click();
	}
	
	public int getListOfItemsPresentInCart() {
		return cartItems.size();
	}
	
	public String getProductQuantity() {
		String quantity="";
		for(int i=0;i<getListOfItemsPresentInCart();i++) {
			quantity=cartItems.get(i).findElement(By.xpath("//div[@class='cart_quantity']")).getText();
		}
		return  quantity;
	}
	
	public void clickCheckoutButton() {
		this.locateThatElementJs(driver,checkoutBtn);
		checkoutBtn.click();
	}
	
	public void clickContinueShoppingButton() {
		this.locateThatElementJs(driver,continueShoppingBtn);
		continueShoppingBtn.click();
	}
	
	
	public void checkAndCompareProductListedInCartPage(ArrayList<String> userAddedProduct){
		int count=0;
		for(String product:userAddedProduct) {
			
			for(WebElement ele:cartItems) {
				String listedProduct=ele.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
				if(listedProduct.equalsIgnoreCase(product)) {
				//	System.out.println("listedProduct"+listedProduct+" "+"user product"+ product);
					count++;
					Assert.assertEquals(listedProduct, product);
					break;
				}
			}
		}
		if(count==userAddedProduct.size()) {
			System.out.println("Element match perfectly");
		}
		
	}
	
	public List<String> addCostOfEachProduct(ArrayList<String> userAddedProduct){
		ArrayList<String> cost=new ArrayList<String>();
		String costOfEachProduct="";
        for(String product:userAddedProduct) {
			
        	for(WebElement ele:cartItems) {
				String listedProduct=ele.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
				if(listedProduct.equalsIgnoreCase(product)) {
					costOfEachProduct =	ele.findElement(By.xpath(".//div[@class='inventory_item_price']")).getText();
					cost.add(costOfEachProduct);
					break;
				}
			}
		}
		
		return cost;
	}
	
	
	
	
	
	
	
	
}
