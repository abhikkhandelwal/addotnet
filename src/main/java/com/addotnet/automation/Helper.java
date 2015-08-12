package com.addotnet.automation;

import java.util.Properties;

import org.openqa.selenium.By;

public class Helper {
	public static By getLocator(String strElement, Properties prop)
			throws Exception {

		// retrieve the specified object from the object list
		String locator = prop.getProperty(strElement);

		// extract the locator type and value from the object
		String locatorType = locator.split(":")[0];
		String locatorValue = locator.split(":")[1];

		// for testing and debugging purposes
		System.out.println("Retrieving object of type '" + locatorType
				+ "' and value '" + locatorValue + "' from the object map");

		// return a instance of the By class based on the type of the locator
		// this By can be used by the browser object in the actual test
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			return By.id(locatorValue);
		case "name":
			return By.name(locatorValue);
		case "classname":
		case "class":
			return By.className(locatorValue);
		case "tagname":
		case "tag":
			return By.className(locatorValue);
		case "linktext":
		case "link":
			return By.linkText(locatorValue);
		case "partiallinktext":
			return By.partialLinkText(locatorValue);
		case "cssselector":
		case "css":
			return By.cssSelector(locatorValue);
		case "xpath":
			return By.xpath(locatorValue);
		default:
			throw new Exception("Unknown locator type '" + locatorType + "'");
		}
	}

}
