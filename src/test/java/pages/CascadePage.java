package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

public class CascadePage extends BasePage {
	
	/**
	 * Page elements
	 */
	static By a_profile_card = By.id("profile_card");
	static By i_profile_card = By.id("TODO");
	static By profile_card = getByElement(a_profile_card, i_profile_card);
	
	public CascadePage() throws MalformedURLException {
		super();
	}

	/**
	 * Actions methods
	 */
	public static void clickMyUser(){
		click(profile_card);
	}
	
	
	/**
	 * Getters
	 */
	public static int getUserCount(){
		return getElementList(profile_card).size();
	}
	
	
	/**
	 * Setters
	 */
	
	
	/**
	 * Verification methods
	 */
	public static boolean isCascadePageLoaded() throws InterruptedException{
		Thread.sleep(5000);
		return isPageLoaded(profile_card);
	}
	
	
}
