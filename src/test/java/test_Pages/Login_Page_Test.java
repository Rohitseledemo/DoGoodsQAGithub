package test_Pages;

import org.testng.annotations.Test;

import Base_Library.Base_Class;
import pages.Login_Page;

public class Login_Page_Test extends Base_Class
{
	Login_Page ob;
	
	@Test
	public void getlaunch() 
	{
		ob = new Login_Page();
		ob.loginToApplication();
	}
	

}
