package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.LoginPage;
import pages.SearchAndGetClothInfo;

public class SearchAndGetInfoClothTest extends BaseTest {
	
	@Test
	public void TC02_SearchAndGetCLothInfo() {
		LoginPage lp=new LoginPage(driver);
		lp.addUserNameAndPassword("standard_user", "secret_sauce");
		lp.clickLoginBtn();
		
		
		String errorMsg=lp.getErrorLoginMsg();
		if(errorMsg.equalsIgnoreCase("No Error")) {
			String homeTitle=lp.getHomePageText();
			Assert.assertEquals(homeTitle, "Swag Labs");
			System.out.println("Logged in successfully");
		}else {
			System.out.println(errorMsg);
			Assert.assertTrue(errorMsg.contains("do not match any user"),"Login Failed");
			
		}
		
		System.out.println("*******************************************************");
		System.out.println("Your can select cloth out of these below mention list:");
		System.out.println("\tSauce Labs Backpack\n\tSauce Labs Bike Light\n\tSauce Labs Bolt T-Shirt\n\tSauce Labs Fleece Jacket\n\tSauce Labs Onesie\n\tTest.allTheThings() T-Shirt (Red)");
		System.out.println("*******************************************************");
		SearchAndGetClothInfo srcInfo=new SearchAndGetClothInfo(driver);
		srcInfo.searchCloth("Test.allTheThings() T-Shirt (Red)");
		
		System.out.println("Product Info Page Details: \n"+ srcInfo.getClothInfo());
		System.out.println("Product price: \n"+ srcInfo.getClothPrice());
		
		srcInfo.clickAddToCart();
		
		CartPage cp=new CartPage(driver);
		cp.clickAddToCart();
		int itemPresentInAddToCart=cp.getListOfItemsPresentInCart();
		String productQuantity=cp.getProductQuantity();
		
		
		System.out.println(itemPresentInAddToCart +"  "+productQuantity);
		
		System.out.println("====Test End===");
		
		
		
		
		
	}

}
