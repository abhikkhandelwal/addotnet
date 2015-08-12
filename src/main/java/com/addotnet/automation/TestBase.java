package com.addotnet.automation;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestBase {

	public static Properties config = null;
	public static Properties OR = null;
	public static boolean loggedIn = false;
	public static WebDriver wbDv = null;
	public static EventFiringWebDriver driver = null;
	public static MSExcelAutomation datatable = null;
	protected DesiredCapabilities capabilities = new DesiredCapabilities();

	@BeforeSuite
	public static void initialize() {

		// loading all the configuration values
		try {
			config = new Properties();
			FileInputStream fp = new FileInputStream(
					new File(".").getCanonicalPath() + File.separator
							+ "config" + File.separator + "config.properties");
			config.load(fp);

			// loading Objects Repositories
			OR = new Properties();
			fp = new FileInputStream(new File(".").getCanonicalPath()
					+ File.separator + "config" + File.separator
					+ "ObjectRepo.properties");
			OR.load(fp);

			datatable = new MSExcelAutomation(new File(".").getCanonicalPath()
					+ File.separator + "xls" + File.separator
					+ "Controller.xlsx");
			// checking the type of browser
			if (config.getProperty("browserType").equalsIgnoreCase("Firefox")) {

				wbDv = new FirefoxDriver();

			}

			if (config.getProperty("browserType").equalsIgnoreCase("Chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir")
								+ "\\SeleniumLibrary\\chromedriver.exe");
				wbDv = new ChromeDriver();
			}

			driver = new EventFiringWebDriver(wbDv);

			// putting an implicit wait after every Action or Event
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			// opening the browser
			driver.navigate().to(config.getProperty("testSiteURL"));

			// maximizing the windows
			driver.manage().window().maximize();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * This function is used to identify the object on the Application
	 * 
	 * @author Sanjay
	 * @param xpathKey
	 *            - unique sudo name which we have kept for every object on the
	 *            web page
	 * @return WebElement
	 */
	public static WebElement getObject(String pathKey) {

		return TestBase.getObject(pathKey, OR);
	}

	public static WebElement getObject(String pathKey, Properties prop) {

		WebElement obj = null;

		try {
			obj = driver.findElement(Helper.getLocator(pathKey, prop));
		} catch (Exception e) {
			obj = null;
		}
		return obj;
	}

	/**
	 * This function is used to identify the objects on the Application
	 * 
	 * @author Sanjay
	 * @param xpathKeyOfElements
	 * @return List<WebElement>
	 */
	public static List<WebElement> getObjects(String xpathKeyOfElements) {

		List<WebElement> obj;

		try {
			By locator = Helper.getLocator(xpathKeyOfElements, OR);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			obj = wait.until(ExpectedConditions
					.presenceOfAllElementsLocatedBy(locator));
		} catch (Exception e) {

			obj = null;
		}

		return obj;

	}

	/**
	 * This method will remove all items from the cart
	 * 
	 * @throws InterruptedException
	 *             @ Author : Sanjay
	 */

	public static void logIn(String userName, String password) {

		Reporter.log("Enter user, password and click Log in button");

		// Enter username
		WebUIAutomation.setText("INPUT_Username_LOGINSCREEN", userName);

		// Enter password
		WebUIAutomation.setText("INPUT_Password_LOGINSCREEN", password);

		// click on 'Log in' button
		if (!WebUIAutomation.clickObj("BTN_SignIn_LOGINSCREEN")) {
			Assert.fail("unsucessful login");
		}

	}

	public static String getUniqueValue() {
		Reporter.log("Getting unique value using system date");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String uniqueValue = (String) dateFormat.format(date);
		return uniqueValue;
	}

	public static boolean selectFromList(String xpathOfListElements,
			String elementToBeClick) {
		Actions action = new Actions(driver);

		By locator = null;
		try {
			locator = Helper.getLocator(xpathOfListElements, OR);

			if (WebUIAutomation.isObjPresent(xpathOfListElements, 30)) {
				// Storing all list values in a List
				List<WebElement> list = driver.findElements(locator);

				for (WebElement element : list) {

					if ((element.getText().trim())
							.equalsIgnoreCase(elementToBeClick)) {

						// selecting a value form drop-down list
						action.click(element).perform();
						action.release();
						break;
					}
				}
				return true;
			} else {

				Reporter.log("List values are not displayed - List items not loaded successfully");
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * This method is used for selecting multiple values from a multi drop-down
	 * list
	 * 
	 * @param xpathOfListValue
	 * @param xpathOfList
	 * @param elementToBeClick
	 * @return boolean true if selected successfully else false
	 */
	public static boolean selectMultipleFromList(String xpathOfListValue,
			String xpathOfList, String elementToBeClick) {
		Actions action = new Actions(driver);
		boolean flag = false;
		By locator = null;
		try {
			locator = Helper.getLocator(xpathOfListValue, OR);
		if (!driver.findElement(locator)
				.isDisplayed()) {
			driver.findElement(locator).click();
			// Thread.sleep(2000);
		}
		List<WebElement> listvalues = driver.findElements(locator);
		String[] separated;

		// Splitting the element string based on new line or comma delimiter
		if (elementToBeClick.contains("\n")) {
			separated = elementToBeClick.split("\n");
		} else if (elementToBeClick.contains(",")) {
			separated = elementToBeClick.split(",");
		} else {
			separated = new String[] { elementToBeClick };
		}

		if (listvalues.size() != 0) {
			for (int i = 0; i < separated.length; i++) {
				// Clicking on dropdown list if list is not open
				if (!driver.findElements(locator).isEmpty()) {
					WebUIAutomation.clickObj(xpathOfList);
					listvalues = driver.findElements(locator);
				}
				// Selecting list values form the list
				for (WebElement element : listvalues) {
					if ((element.getText().trim()).equals(separated[i])) {
						action.click(element).perform();
						// action.release();
						break;
					}
				}
			}
			flag = true;
		} else {
			flag = false;
		}

		if (flag == true) {
			return true;
		} else {
			return false;
		}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static boolean verifyCreate(String actualTextPath,
			String expectedValue, String uniqueValue)
			throws NullPointerException {
		boolean status = false;
		if (WebUIAutomation.isObjPresent(OR.getProperty(actualTextPath), 20)) {
			status = (WebUIAutomation.getObject(OR.getProperty(actualTextPath))
					.getText().substring(2)).contains(expectedValue
					.toUpperCase() + uniqueValue);
		} else {
			Reporter.log(" Page is not navigating : Not Created Successfully");
		}
		if (status) {
			return true;
		} else {
			Reporter.log("Value Mismatch : Not created as Expected");
			return false;
		}
	}

	@AfterSuite
	public void closeBrowser() {

		driver.quit();

	}

}
