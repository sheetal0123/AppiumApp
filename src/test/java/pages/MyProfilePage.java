package pages;

import java.net.MalformedURLException;

import org.openqa.selenium.By;

public class MyProfilePage extends BasePage{
	
	/**
	 * Page elements
	 */
	static By a_backButton = By.id("Navigate up");
	static By i_backButton = By.id("TODO");
	static By backButton = getByElement(a_backButton, i_backButton);
	
	static By a_settingButton = By.id("menu_settings");
	static By i_settingButton = By.id("TODO");
	static By settingButton = getByElement(a_settingButton, i_settingButton);
	
	static By a_editProfileButton = By.id("profile_chat_edit");
	static By i_editProfileButton = By.id("TODO");
	static By editProfileButton = getByElement(a_editProfileButton, i_editProfileButton);
	
	static By a_displayName = By.id("profile_display_name");	
	static By i_displayName = By.id("TODO");	
	static By displayName = getByElement(a_displayName, i_displayName);
	
	public MyProfilePage() throws MalformedURLException{
		super();
	}
	
	
	/**
	 * Actions methods
	 */
	public static void clickSettingButton(){
		click(settingButton);
	}
	
	public static void clickEditProfileButton(){
		click(editProfileButton);
	}
	
	public static void clickBackButton(){
		click(backButton);
	}
	
	
	/**
	 * Getters
	 */
	public static String getDisplayName(){
		return getText(displayName);
	}
	
	
	/**
	 * Setters
	 */
	
	/**
	 * Verification methods
	 */
	public static boolean isMyProfilePageLoaded() throws InterruptedException{
		return isPageLoaded(settingButton);
	}
	
	public static boolean isMyNameDisplayed(){
		return isElementPresent(displayName);
	}
	
	
	
}
