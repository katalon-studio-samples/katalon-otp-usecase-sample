import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
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
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://outlook.live.com/owa/')

WebUI.click(findTestObject('Object Repository/OR Outlook Web/a_Sign in'))

WebUI.click(findTestObject('Object Repository/OR Outlook Web/div_Sign in'))

WebUI.waitForElementPresent(findTestObject('Object Repository/OR Outlook Web/div_Sign in'), 60)

WebUI.setText(findTestObject('Object Repository/OR Outlook Web/input_Sign in_loginfmt'), 'mohit@outlook.com')

WebUI.click(findTestObject('Object Repository/OR Outlook Web/input_Create one_idSIButton9'))

WebUI.delay(2)

WebUI.setEncryptedText(findTestObject('Object Repository/OR Outlook Web/input_Enter password_passwd'), 'iFGeFYmXIrVNNiV6Jq7reA==')

WebUI.click(findTestObject('Object Repository/OR Outlook Web/input_Create one_idSIButton9'))

WebUI.click(findTestObject('Object Repository/OR Outlook Web/input_concat(Stay signed in so you don, , t_4104e6'))

WebUI.click(findTestObject('Object Repository/OR Outlook Web/input_concat(Don, , t show this again)_idSIButton9'))

WebUI.click(findTestObject('Object Repository/OR Outlook Web/span_Inbox'))

WebUI.click(findTestObject('Object Repository/OR Outlook Web/span_Test OTP outlook'))

WebUI.delay(5)

msgcontent = WebUI.getText(findTestObject('Object Repository/OR Outlook Web/div_This is your OTP for Outlook 12345'), FailureHandling.STOP_ON_FAILURE)

Pattern p1 = Pattern.compile('This is your OTP for Outlook: ([0-9]+)')

Matcher m1 = p1.matcher(msgcontent)

if (m1.find()) {
    println('OTP for Outlook: ' + m1.group(1))

    return m1.group(1)
}

WebUI.closeBrowser()

