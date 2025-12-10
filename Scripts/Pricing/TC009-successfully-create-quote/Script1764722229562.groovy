import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData

CustomKeywords.'actions.GeneralActions.openLoginPage'()

CustomKeywords.'actions.LoginActions.loginWithValidCreds'()

CustomKeywords.'utils.Utils.waitForPageReady'(findTestObject('OneUi-InternalPage/Pricing-PricingMenu'))

def generalQuoteInformationData = CustomKeywords.'actions.QuoteActions.createNewQuote'()

utils.QuotePageReady.waitForQuotePageReady(30)

CustomKeywords.'assertions.QuoteBuilderAssertions.assertFieldValues'([
	'Prepared For' : generalQuoteInformationData.preparedForCompany,
	'Valid For (Days)' : generalQuoteInformationData.validFor,
	'Currency' : generalQuoteInformationData.currency,
	'Volume Band' : generalQuoteInformationData.volumeBand,
	'Prepared By' : generalQuoteInformationData.preparedByName,
	])

WebUI.closeBrowser()

