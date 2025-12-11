package actions
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.builtin.SendKeysKeyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import CustomKeywords

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import internal.GlobalVariable
import models.QuoteInformation

public class QuoteActions {
	@Keyword
	def createNewQuote() {
		WebUI.click(findTestObject('OneUi-InternalPage/Pricing-PricingMenu'))
		WebUI.click(findTestObject('OneUi-QuotePage/Btn-NewQuote'))

		QuoteInformation data = new QuoteInformation()

		data.currency = fillCurrency()
		data.volumeBand = fillVolumeBand()
		data.validFor = fillValidFor()
		data.validFrom = fillValidFrom()
		data.preparedForName = fillPreparedForName()
		data.preparedForCompany = fillPreparedForCompany()
		data.preparedByName = fillPreparedByName()
		data.preparedByEmail = fillPreparedByEmail()
		data.preparedByCompany = fillPreparedByCompany()
		data.preparedByPhone = fillPreparedByPhone()
		WebUI.delay(0.3)
		//test commit
		WebUI.waitForElementClickable(findTestObject('Object Repository/OneUi-QuotePage/General-Quote-Information/Btn-SaveContinue'), 10)
		WebUI.click(findTestObject('OneUi-QuotePage/General-Quote-Information/Btn-SaveContinue'))
		return data
	}

	@Keyword
	def pickRandomOption(TestObject listLocator) {
		int attempts = 0

		while (attempts < 2) {
			try {
				List<WebElement> items = WebUiCommonHelper.findWebElements(listLocator, 5)
				if (items.isEmpty()) {
					KeywordUtil.markFailed("Dropdown is empty!")
				}

				int randomIndex = new Random().nextInt(items.size())
				String value = items.get(randomIndex).getText()
				items.get(randomIndex).click()

				return value
			}
			catch (e) {
				attempts++
				WebUI.delay(0.3)
			}
		}
	}

	@Keyword
	def fillCurrency() {
		WebUI.waitForElementClickable(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-Currency'), 10)
		WebUI.click(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-Currency'))
		def value = pickRandomOption(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-List-Currency'))
		KeywordUtil.logInfo("Selected currency: ${value}")
		return value
	}

	@Keyword
	def fillVolumeBand() {
		WebUI.waitForElementClickable(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-VolumeBand'), 10)
		WebUI.click(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-VolumeBand'))
		def value = pickRandomOption(findTestObject('OneUi-QuotePage/General-Quote-Information/Dd-List-VolumeBand'))
		KeywordUtil.logInfo("Selected volume band: ${value}")
		return value
	}

	@Keyword
	def fillValidFor() {
		def validForDays = CustomKeywords.'utils.Utils.getRandomNumber'()
		TestObject validForDaysField = findTestObject('Object Repository/OneUi-QuotePage/General-Quote-Information/Field-ValidForDays')
		CustomKeywords.'actions.GeneralActions.setCleanText'(validForDaysField, validForDays)
		KeywordUtil.logInfo("Valid for: ${validForDays} days")
		return validForDays
	}

	@Keyword
	def fillValidFrom() {
		WebUI.click(findTestObject('Object Repository/OneUi-QuotePage/General-Quote-Information/Btn-DatePicker'))
		def validFrom = CustomKeywords.'utils.Utils.selectDay'()
		KeywordUtil.logInfo("Valid from: ${validFrom}")
		return validFrom
	}

	@Keyword
	def fillPreparedForName() {
		def name = CustomKeywords.'utils.RandomData.randomName'()
		WebUI.setText(findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PF-Name'), name)
		KeywordUtil.logInfo("Prepared for: ${name}")
		return name
	}

	@Keyword
	def fillPreparedForCompany() {
		def company = CustomKeywords.'utils.RandomData.randomCompany'()
		WebUI.setText(findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PF-Company'), company)
		KeywordUtil.logInfo("Prepared for (Company): ${company}")
		return company
	}

	@Keyword
	def fillPreparedByName() {
		def name = CustomKeywords.'utils.RandomData.randomName'()
		TestObject nameField = findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PB-Name')
		CustomKeywords.'actions.GeneralActions.setCleanText'(nameField, name)
		KeywordUtil.logInfo("Prepared by: ${name}")
		return name
	}

	def fillPreparedByEmail() {
		def email = CustomKeywords.'utils.RandomData.randomEmail'()
		TestObject emailField = findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PB-Email')
		CustomKeywords.'actions.GeneralActions.setCleanText'(emailField, email)
		KeywordUtil.logInfo("Email: ${email}")
		return email
	}

	@Keyword
	def fillPreparedByCompany() {
		def company = CustomKeywords.'utils.RandomData.randomCompany'()
		TestObject companyField = findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PB-Company')
		CustomKeywords.'actions.GeneralActions.setCleanText'(companyField, company)
		KeywordUtil.logInfo("Company: ${company}")
		return company
	}

	@Keyword
	def fillPreparedByPhone() {
		def phoneNumber = CustomKeywords.'utils.RandomData.randomPhone'()
		WebUI.setText(findTestObject('OneUi-QuotePage/General-Quote-Information/Field-PB-PhoneNumber'), phoneNumber)
		KeywordUtil.logInfo("Phone number: ${phoneNumber}")
		return phoneNumber
	}
}
