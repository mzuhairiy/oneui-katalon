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
import io.github.serpro69.kfaker.Faker

import internal.GlobalVariable

public class RandomData {
	static Faker faker = new Faker()

	@Keyword
	def randomName() {
		faker.name.firstName()
	}

	@Keyword
	def randomEmail() {
		faker.internet.email()
	}

	@Keyword
	def randomCompany() {
		faker.company.name()
	}

	@Keyword
	def randomPhone() {
		// Generate 10–12 digit random number
	    String digits = (1..10).collect { faker.random.nextInt(0,9) }.join("")
	    return "44" + digits
	}
}
