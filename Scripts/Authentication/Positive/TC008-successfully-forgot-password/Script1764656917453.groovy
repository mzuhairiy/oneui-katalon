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

'user navigate to the login page'
CustomKeywords.'actions.GeneralActions.openLoginPage'()

WebUI.setText(findTestObject('OneUi-LoginPage/Field-Email'), 'adew@loopup.co')

WebUI.click(findTestObject('OneUi-LoginPage/Btn-Next'))

WebUI.click(findTestObject('OneUi-LoginPage/Btn-ForgotPassword'))

WebUI.click(findTestObject('OneUi-LoginPage/Btn-SendEmail'))

WebUI.verifyElementVisible(findTestObject('OneUi-LoginPage/Txt-EmailSent'), FailureHandling.STOP_ON_FAILURE)

WebUI.verifyElementVisible(findTestObject('OneUi-LoginPage/Btn-EmailSent'), FailureHandling.STOP_ON_FAILURE)

WebUI.closeBrowser()

