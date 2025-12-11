package actions
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.util.KeywordUtil
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

import internal.GlobalVariable

public class LoginActions {

	@Keyword
	def loginWithValidCreds() {
		KeywordUtil.logInfo("Login with valid credentials..")
		WebUI.setText(findTestObject('OneUi-LoginPage/Field-Email'), GlobalVariable.G_AdminUserName)
		WebUI.click(findTestObject('OneUi-LoginPage/Btn-Next'))
		WebUI.setText(findTestObject('OneUi-LoginPage/Field-Password'), GlobalVariable.G_Password)
		WebUI.click(findTestObject('OneUi-LoginPage/Btn-SignIn'))
	}

	@Keyword
	def loginWithInvalidCreds() {
		WebUI.setText(findTestObject('OneUi-LoginPage/Field-Email'), "adew@loopup.co")
		WebUI.click(findTestObject('OneUi-LoginPage/Btn-Next'))
		WebUI.setText(findTestObject('OneUi-LoginPage/Field-Password'), "pass2134")
		WebUI.click(findTestObject('OneUi-LoginPage/Btn-SignIn'))
	}
}