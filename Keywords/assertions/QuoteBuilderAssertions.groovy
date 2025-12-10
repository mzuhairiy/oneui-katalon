package assertions
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
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import internal.GlobalVariable
import models.QuoteInformation

public class QuoteBuilderAssertions {
	@Keyword
	def assertFieldValues(Map<String, Object> expectedValues) {

        expectedValues.each { label, expected ->
            TestObject obj = new TestObject(label + "_value")
            obj.addProperty("xpath", ConditionType.EQUALS,
                "//p[normalize-space()='" + label + "']/following-sibling::p[1]"
            )

            String actual = WebUI.getText(obj)

            assert actual == expected.toString() : 
                "❌ ${label} mismatch → Expected: '${expected}', Actual: '${actual}'"
        }

        WebUI.comment("✔ All field assertions passed")
    }
}
