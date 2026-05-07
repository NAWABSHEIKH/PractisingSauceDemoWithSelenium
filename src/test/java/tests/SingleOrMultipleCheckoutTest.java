package tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CartPage;
import pages.CheckoutComplete;
import pages.CheckoutInformationPage;
import pages.CheckoutOverviewPage;
import pages.LoginPage;
import pages.SearchAndGetClothInfo;

public class SingleOrMultipleCheckoutTest extends BaseTest{

	@Test
	public void TC02_SingleCheckoutAndOrder(){
	
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
		
		cp.clickCheckoutButton();
		
		CheckoutInformationPage cip=new CheckoutInformationPage(driver);
		Assert.assertTrue(cip.checkCheckoutInformationDisplayeSuccessfully());
		
		cip.fillBuyerDetails("John", "Doe", "43261");
		cip.clickContinueBtn();
	
		CheckoutOverviewPage cop=new CheckoutOverviewPage(driver);
		Assert.assertTrue(cop.isCheckoutOverviewTitleVisible());
		
		String paymentInfo=cop.getPaymentInformation();
		String shippentInfo=cop.getShippmentInformation();
		double productCost=cop.getListedProductCost();
		double tax=cop.getTaxOnProduct();
		double totalCostWithTax=cop.getTotalCostWithTax();
		
		System.out.println("****************************_*************************_*************************");
		System.out.println("\t"+paymentInfo+"\t"+shippentInfo+"\t"+productCost+"\t"+tax+"\t"+totalCostWithTax);
		System.out.println("****************************_*************************_*************************");
		
		Assert.assertEquals(productCost+tax, totalCostWithTax);
		
		cop.clickButton();
		
		CheckoutComplete coc=new CheckoutComplete(driver);
		Assert.assertTrue(coc.isCompleteTitleVisible());
		
		System.out.println(coc.getCompleteOrderSuccessfulMessage());
		Assert.assertTrue(coc.getCompleteOrderSuccessfulMessage().contains("Thank you"));
		
		
	}
	
	@Test
	public void TC03_MultipleCheckoutAndOrder(){
		
		ArrayList<String> userEnteredClothes=new ArrayList<String>();
		userEnteredClothes.add("Sauce Labs Backpack");
		userEnteredClothes.add("Test.allTheThings() T-Shirt (Red)");
		userEnteredClothes.add("Sauce Labs Onesie");
		
		
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
		
		SearchAndGetClothInfo srcInfo=new SearchAndGetClothInfo(driver);
		srcInfo.searchMutipleCloth(userEnteredClothes);
		
		CartPage cp=new CartPage(driver);
		cp.clickAddToCart();
		cp.checkAndCompareProductListedInCartPage(userEnteredClothes);
		List<String> costOfEachListedProd=cp.addCostOfEachProduct(userEnteredClothes);
		
		double totalAmount=0;
		for(String cost:costOfEachListedProd)
		{
			System.out.println(cost);
			cost=cost.replaceAll("[^0-9.]", "");
			totalAmount+=Double.parseDouble(cost);
		}
		
		System.out.println(totalAmount);
		
		
		
		System.out.println("========================================================");
        cp.clickCheckoutButton();
		
		CheckoutInformationPage cip=new CheckoutInformationPage(driver);
		Assert.assertTrue(cip.checkCheckoutInformationDisplayeSuccessfully());
		
		cip.fillBuyerDetails("John", "Doe", "43261");
		cip.clickContinueBtn();
	
		CheckoutOverviewPage cop=new CheckoutOverviewPage(driver);
		Assert.assertTrue(cop.isCheckoutOverviewTitleVisible());
		
		String paymentInfo=cop.getPaymentInformation();
		String shippentInfo=cop.getShippmentInformation();
		double productCost=cop.getListedProductCost();
		double tax=cop.getTaxOnProduct();
		double totalCostWithTax=cop.getTotalCostWithTax();
		
		System.out.println("****************************_*************************_*************************");
		System.out.println("\t"+paymentInfo+"\t"+shippentInfo+"\t"+productCost+"\t"+tax+"\t"+totalCostWithTax);
		System.out.println("****************************_*************************_*************************");
		
		Assert.assertEquals(productCost+tax, totalCostWithTax);
		
		System.out.println(productCost-tax);
		
		System.out.println("========================================================");
		
		Assert.assertEquals(totalCostWithTax-tax, totalAmount);
		
        cop.clickButton();
		
		CheckoutComplete coc=new CheckoutComplete(driver);
		Assert.assertTrue(coc.isCompleteTitleVisible());
		
		System.out.println(coc.getCompleteOrderSuccessfulMessage());
		Assert.assertTrue(coc.getCompleteOrderSuccessfulMessage().contains("Thank you"));
		
		
		System.out.println("Test Ended!"); 
		
		
		
	}
	
}
