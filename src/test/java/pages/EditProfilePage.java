package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

public class EditProfilePage extends BasePage {

	/**
	 * Page elements
	 */
	
	static By a_facebook = By.id("//android.widget.TextView[@text='Facebook']/following-sibling::android.widget.EditText");
	static By i_facebook = By.id("TODO");
	static By facebook = getByElement(a_facebook, i_facebook);

	
	public EditProfilePage() throws MalformedURLException {
		super();
	}

	/**
	 * Actions methods
	 */

	
	/**
	 * Getters
	 */
	
	
	/**
	 * Setters
	 */
	public static void setFacebookUserName(String username){
		/*
		swipeVerticallyBottomToUp(0.90, 0.10);
		boolean fb = isFacebookFieldDisplayed();
		System.out.println(fb);
		if(!fb){
			System.out.println("IF LOOPS, SWIPE AGAIN ...");
			swipeVerticallyBottomToUp(0.80, 0.30);
		}
		sendKeys(facebook, username);
		*/
	}
	
	
	/**
	 * Verification methods
	 */
	public static boolean isFacebookFieldDisplayed(){
		System.out.println("********************><><><");
		return isElementPresent(facebook);
	}
	
	
}
