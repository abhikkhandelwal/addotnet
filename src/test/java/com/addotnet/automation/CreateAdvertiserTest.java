package com.addotnet.automation;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Author: Sanjay
 * @Created on 26-03-2015
 * @Note: This test script is not working completly as server sends an error
 *        response(Manually also not working)
 */
public class CreateAdvertiserTest extends TestBase {
	// Checking the script has to execute or not
	@BeforeTest
	public void doIHaveToSkip() {

		if (!(WebUIAutomation.checkTestCaseRunmode(this.getClass()
				.getSimpleName()))) {

			throw new SkipException("Runmode set to No");
		}
	}

	@Test(dataProvider = "getData")
	public void createAdvertiserTest(String UserName, String Password,
			String Status, String ClickURLRedirect, String Server2ServerPixel,
			String FullName, String Company, String Email,
			String StreetAddress, String City, String Country, String State,
			String ZipCode, String PhoneNo, String PhoneExt, String Fax,
			String BillingType, String AccountCap, String HeardAboutUs,
			String ReferredBy, String Managers, String AccountExecutive) {

		Reporter.log("Executing CreateAdvertiserTest");
		Reporter.log("*****************************\n");
		// Login to the Application
		logIn("xiaolong.xu", "root");

		WebUIAutomation.isObjPresent("TAB_Advertiser_DASHBOARDLEFTPANEL", 30);

		if (!WebUIAutomation.clickObj("TAB_Advertiser_DASHBOARDLEFTPANEL")) {
			Assert.fail("Clicking on 'Advertiser' tab from the left panel is not successful");
		}
		if (!WebUIAutomation.clickObj("SUBTAB_Advertisers_DASHBOARDLEFTPANEL")) {
			Assert.fail("Clicking on 'Advertiser' sub-tab from the left panel is not successful");
		}
		try {
			// Wait for 'Create New' dropdown appear on Advertiser List page
			WebUIAutomation.isObjPresent("DRPDWNBTN_CreateNew_ADVERTISERSLIST",
					30);

			// Clicking on 'Create New' dropdown

			if (!WebUIAutomation
					.clickObj("DRPDWNBTN_CreateNew_ADVERTISERSLIST")) {
				Assert.fail("Clicking on 'Create New' drop-down from the 'Advertises List' page is not successful");
			}
			
			// Checking Advertiser link is displayed or not under Create New
			// drop down
			Assert.assertTrue(WebUIAutomation.isObjPresent(
					"DRPDWNLNK_Advertiser_ADVERTISERSLIST", 10),
					"Advertiser Link is not displayed under Create New drop down");
			
			// Clicking on 'Advertiser' link under 'Create New' dropdown, It
			// will navigate to 'Create Advertiser' page
			if (!WebUIAutomation
					.clickObj("DRPDWNLNK_Advertiser_ADVERTISERSLIST")) {
				Assert.fail("Clicking on 'Advertiser' link from the 'Create New' drop-down is not successful");
			}

			// Getting Server Date TimeStamp
			String uniqueValue = TestBase.getUniqueValue();

			// Entering the text in the Username field

			String username = UserName + uniqueValue;
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_UserName_CREATEADVERTISERPAGE", username),
					"Unable to enter Username in the text field");

			// Entering the text in the Password field
			Reporter.log("Entering the text in the Password field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_Password_CREATEADVERTISERPAGE", Password),
					"Unable to enter Password in the text field");
			
			// Selecting status from Status drop down
			Reporter.log("Setting the status in the status drop-down field");
			if (WebUIAutomation.getObject("SELECT_Status_CREATEADVERTISERPAGE")
					.isDisplayed()) {
				Assert.assertTrue(WebUIAutomation.selectValueFromDrpDwn(
						"SELECT_Status_CREATEADVERTISERPAGE", Status),
						"Unable to select status in the Status drop-down");
			}

			// Entering the text in the Full Name field
			Reporter.log("Entering the text in the Full Name field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_FullName_CREATEADVERTISERPAGE", FullName),
					"Unable to enter Full Name in the text field");

			// Entering the text in the Company field
			Reporter.log("Entering the text in the Company field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_Company_CREATEADVERTISERPAGE", Company),
					"Unable to enter Company in the text field");
			
			// Entering the text in the Email field
			Reporter.log("Entering the text in the Email field");
			String uniqueEmail = "";
			String[] separatedEmail = Email.split("@");
			for (int i = 0; i < separatedEmail.length; i++) {
				uniqueEmail += separatedEmail[i];
				if (i == 0) {
					uniqueEmail = uniqueEmail + uniqueValue + "@";
				}
			}
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_Email_CREATEADVERTISERPAGE", uniqueEmail),
					"Unable to enter Email in the text field");

			// Entering the number in Server 2 Server Pixel field
			Reporter.log("Entering the number in Server 2 server pixel");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_Server2ServerPixel_CREATEADVERTISERPAGE",
					Server2ServerPixel),
					"Unable to enter Server 2 Server pixel number");

			// Entering the text in the Street Address field of Billing Address
			Reporter.log("Entering the text in the Street Address field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_StreetAddressInBillingAddress_CREATEADVERTISERPAGE",
					StreetAddress),
					"Unable to enter Street Address in the text field");

			// Entering the text in the City field of Billing Address
			Reporter.log("Entering the text in the City field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_CityInBillingAddress_CREATEADVERTISERPAGE", City),
					"Unable to enter City in the text field");

			// Clicking on the Country dropdown of Billing Address
			Reporter.log("Clicking on the Country drop down");
			Assert.assertTrue(
					WebUIAutomation
							.clickObj("DRPDWNLST_CountryInBillingAddress_CREATEADVERTISERPAGE"),
					"Unable to click on country drop-down");

			// Selecting the country from Country dropdown of Billing Address
			Reporter.log("Selecting the Country from the Country drop-down");
			Assert.assertTrue(

					TestBase.selectFromList(
							"DRPDWNLST_CountryListInBillingAddress_CREATEADVERTISERPAGE",
							Country), "Unable to select country");
			
			// Selecting the State of Billing Address
			if (WebUIAutomation.isObjPresent(
					"DRPDWNLST_StateInBillingAddress_CREATEADVERTISERPAGE", 10)) {
				Reporter.log("Clicking on the State drop down");
				Assert.assertTrue(
						WebUIAutomation
								.clickObj("DRPDWNLST_StateInBillingAddress_CREATEADVERTISERPAGE"),
						"Unable to click on State drop-down");

				Reporter.log("Selecting the State from the State drop-down");

				selectFromList(
						"DRPDWNLST_StateListInBillingAddress_CREATEADVERTISERPAGE",
						State);
			} else if (WebUIAutomation
					.isObjPresent(
							"INPUT_StateOtherInBillingAddress_CREATEADVERTISERPAGE",
							10)) {
				Reporter.log("Entering the text in the State field");
				Assert.assertTrue(
						WebUIAutomation
								.setText(
										"INPUT_StateOtherInBillingAddress_CREATEADVERTISERPAGE",
										State),
						"Unable to enter State in the text field");
			} else {
				Assert.assertTrue(false, "Unable to set State");
			}

			// Entering the text in the Zip field of Billing Address
			Reporter.log("Entering the text in the zip code");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_ZipCodeInBillingAddress_CREATEADVERTISERPAGE",
					ZipCode), "Unable to enter zip code in the text field");

			// Entering the text in the Phone Number field of Billing Address
			Reporter.log("Entering the text in the Phone");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_PhoneNoInBillingAddress_CREATEADVERTISERPAGE",
					PhoneNo), "Unable to enter phone number in the text field");

			// Entering the text in the Street Address field of Contact Info
			Reporter.log("Entering the text in the Street Address field of Contact Info");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_StreetAddressInContactInfo_CREATEADVERTISERPAGE",
					StreetAddress),
					"Unable to Enter text in the Street Address field of Contact Info");

			// Entering the text in the City field of Contact Info
			Reporter.log("Entering the text in the City field of Contact Info");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_CityInContactInfo_CREATEADVERTISERPAGE", City),
					"Unable to Enter the text in the City field of Contact Info");

			// Clicking on the Country dropdown
			Reporter.log("Clicking on the Country drop down");
			Assert.assertTrue(
					WebUIAutomation
							.clickObj("DRPDWNLST_CountryInContactInfo_CREATEADVERTISERPAGE"),
					"Unable to click on country drop-down");

			// Selecting the country from Country dropdown
			Reporter.log("Selecting the Country from the Country drop-down");
			Assert.assertTrue(
					selectFromList(
							"DRPDWNLST_CountryListInContactInfo_CREATEADVERTISERPAGE",
							Country), "Unable to select country");

			// Selecting the State
			if (WebUIAutomation.isObjPresent(
					"DRPDWNLST_StateInContactInfo_CREATEADVERTISERPAGE", 10)) {
				Reporter.log("Clicking on the State drop down");
				Assert.assertTrue(
						WebUIAutomation
								.clickObj("DRPDWNLST_StateInContactInfo_CREATEADVERTISERPAGE"),
						"Unable to click on State drop-down");

				Reporter.log("Selecting the State from the State drop-down");

				selectFromList(
						"DRPDWNLST_StateListInContactInfo_CREATEADVERTISERPAGE",
						State);
			} else if (WebUIAutomation.isObjPresent(
					"INPUT_StateOtherInContactInfo_CREATEADVERTISERPAGE", 10)) {
				Reporter.log("Entering the text in the State field");
				Assert.assertTrue(WebUIAutomation.setText(
						"INPUT_StateOtherInContactInfo_CREATEADVERTISERPAGE",
						State), "Unable to enter State in the text field");
			} else {
				Assert.assertTrue(false, "Unable to set State");
			}

			// Entering the text in the Zip field of Contact Info
			Reporter.log("Entering the text in the Zip field of Contact Info");
			Assert.assertTrue(
					WebUIAutomation.setText(
							"INPUT_ZipCodeInContactInfo_CREATEADVERTISERPAGE",
							ZipCode),
					"Unable rto Enter Zip Code in the Contact Info Zip Code field ");

			// Entering the text in the Phone Number field of Contact Info
			Reporter.log("Entering the text in the Phone Number field of Contact Info");
			Assert.assertTrue(
					WebUIAutomation.setText(
							"INPUT_PhoneNoInContactInfo_CREATEADVERTISERPAGE",
							PhoneNo),
					"Unable to Enter the text in the Phone Number field of Contact Info");

			// Entering the text in the Fax field
			Reporter.log("Entering the text in the Fax");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_FaxInContactInfo_CREATEADVERTISERPAGE", Fax),
					"Unable to enter fax in the text field");

			// Selecting the Billing Type from Billing Type dropdown
			Reporter.log("Selecting the Billing Type from the Billing Type drop-down");
			Assert.assertTrue(WebUIAutomation.selectValueFromDrpDwn(
					"SELECT_BillingType_CREATEADVERTISERPAGE", BillingType),
					"Unable to select Billing Type from Billing Type drop-down list");

			// Entering the text in the Account Cap field
			if (!AccountCap.equals("Unlimited")) {
				if (WebUIAutomation.getObject(
						"BTN_AccountCapUnlimited_CREATEADVERTISERPAGE")
						.isDisplayed()) {
					WebUIAutomation
							.clickObj("BTN_AccountCapUnlimited_CREATEADVERTISERPAGE");
					Reporter.log("Entering the text in the Account Cap");
					Assert.assertTrue(WebUIAutomation
							.setText("INPUT_AccountCap_CREATEADVERTISERPAGE",
									AccountCap),
							"Unable to enter Account Cap in the text field");
				}
			}

			// Entering the text in the Heard About Us field
			Reporter.log("Entering the text in Heard About Us field");
			Assert.assertTrue(WebUIAutomation.setText(
					"INPUT_HeardAboutUs_CREATEADVERTISERPAGE", HeardAboutUs),
					"Unable to enter Heard About Us text field");

			// Clicking on the Referred By drop down
			Reporter.log("Clicking on the Referred By drop down");
			Assert.assertTrue(WebUIAutomation
					.clickObj("DRPDWNLST_ReferredBy_CREATEADVERTISERPAGE"),
					"Unable to click on Referred By drop-down");

			// Selecting Referred By from Referred By drop down
			Reporter.log("Selecting the Referred By from the Referred By drop-down");
			boolean x = selectFromList(
					"DRPDWNLST_ReferredByList_CREATEADVERTISERPAGE", ReferredBy);
			Assert.assertTrue(x);

			// Clicking on the Managers drop down
			Reporter.log("Clicking on the Managers drop down");
			Assert.assertTrue(WebUIAutomation
					.clickObj("DRPDWNLST_Managers_CREATEADVERTISERPAGE"),
					"Unable to click on Managers drop-down");
			
            
			// Selecting Managers from the Managers drop down
			Reporter.log("Selecting Managers from Manager drop down");
			boolean y = selectMultipleFromList(
					"DRPDWNLST_ManagersList_CREATEADVERTISERPAGE",
					"DRPDWNLST_Managers_CREATEADVERTISERPAGE", Managers);
			Assert.assertTrue(y);

			// Clicking on the Account Executives drop down
			Reporter.log("Clicking on the Account Executives drop down");
			Assert.assertTrue(
					WebUIAutomation
							.clickObj("DRPDWNLST_AccountExecutives_CREATEADVERTISERPAGE"),
					"Unable to click on Account Executives drop-down");

			// Selecting Account Executives from the Account Executives drop
			// down
			Reporter.log("Selecting Account Executives from Account Executives drop down");
			boolean z = selectMultipleFromList(
					"DRPDWNLST_AccountExecutivesList_CREATEADVERTISERPAGE",
					"DRPDWNLST_AccountExecutives_CREATEADVERTISERPAGE",
					Managers);
			Assert.assertTrue(z);

			// Clicking on the Account Access drop down
			Reporter.log("Clicking on the Account Access drop down");
			Assert.assertTrue(WebUIAutomation
					.clickObj("DRPDWNLST_AccountAccess_CREATEADVERTISERPAGE"),
					"Unable to click on AccountAccess drop-down");

			// Selecting Account Access from the Account Access drop down
			Reporter.log("Selecting Account Access from Account Access drop down");
			boolean r = selectMultipleFromList(
					"DRPDWNLST_AccountAccessList_CREATEADVERTISERPAGE",
					"DRPDWNLST_AccountAccess_CREATEADVERTISERPAGE", AccountExecutive);
			Assert.assertTrue(r);

			// Checking whether Save&Continue button is displayed on the screen
			// or not
			Reporter.log("Checking whether Save&Continue button is displayed on the Create Advertiser Page");
			Assert.assertTrue(WebUIAutomation.isObjPresent(
					"BTN_Save&Continue_CREATEADVERTISERPAGE", 20),
					"Save&Continue button is not displayed - Page not loaded successfully");

			// Clicking on the Save&Continue button on the screen
			Reporter.log("Clicking on the Save&Continue button on the Create Advertiser page");
			Assert.assertTrue(WebUIAutomation
					.clickObj("BTN_Save&Continue_CREATEADVERTISERPAGE"),
					"Unable to click on Save&Continue button");

			// Verify Advertiser is Created Successfully or Not
			Reporter.log("Checking Advertiser is created or not");
			verifyCreate("LNK_AdvertiserText_BREADCRUMB", UserName, uniqueValue);
//			 Assert.assertTrue(WebUIAutomation.isObjPresent("LNK_AdvertiserText_BREADCRUMB",20),"Page is not navigated to Create Campaign page; Advertiser is not Created Successfully");
//			 Assert.assertTrue((WebUIAutomation.getObject("LNK_AdvertiserText_BREADCRUMB").getText().substring(2)).contains(UserName").toUpperCase()+uniqueValue),
//			 "Value Mismatch: Advertiser is not Created Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false,
					"Catch Exception, Advertiser is not created ...");
		}
	}

	@DataProvider
	public Object[][] getData() {

		return WebUIAutomation.getData("CreateAdvertiserTest");
	}

	@AfterMethod
	public void closeBrowser() {

		// quit();
	}

}
