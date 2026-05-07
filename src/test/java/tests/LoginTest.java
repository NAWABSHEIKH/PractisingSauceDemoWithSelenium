package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	
	@Test
	public void TC01_Login() {
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
	}
	

}
