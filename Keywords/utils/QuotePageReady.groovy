package utils

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.webui.driver.DriverFactory

import internal.GlobalVariable

public class QuotePageReady {

	static TestObject loaderObj = findTestObject('Object Repository/OneUi-QuotePage/General-Quote-Information/Icon-CircularLoading')
	static TestObject headerObj = createXpath("//h1[contains(.,'Quote')]")

	// Main method
	static void waitForQuotePageReady(int timeout = 20) {

		long start = System.currentTimeMillis()

		// Step 1 → Wait until header exists (core indicator)
		WebUI.waitForElementVisible(headerObj, timeout)

		// Step 3 → Wait until DOM is stable
		waitForDomStable(timeout)

		long duration = (System.currentTimeMillis() - start) / 1000
		println("[QuotePageReady] Page ready in ${duration} seconds")
	}

	// Wait until loader gone
	private static void waitForLoaderToDisappear(int timeout) {
		WebUI.waitForElementNotVisible(loaderObj, timeout, FailureHandling.OPTIONAL)
	}

	// DOM stability check — prevents flaky waits
	private static void waitForDomStable(int timeout) {
		long end = System.currentTimeMillis() + timeout * 1000
		String previousDom = ""

		while (System.currentTimeMillis() < end) {
			String currentDom = DriverFactory.getWebDriver().getPageSource()
			if (currentDom == previousDom) return
				previousDom = currentDom
			WebUI.delay(0.3)
		}

		WebUI.comment("[WARN] DOM did not stabilize in ${timeout} seconds")
	}

	private static TestObject createXpath(String xp) {
		TestObject t = new TestObject()
		t.addProperty("xpath", com.kms.katalon.core.testobject.ConditionType.EQUALS, xp)
		return t
	}
}
