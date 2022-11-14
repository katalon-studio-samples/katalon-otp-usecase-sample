import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.util.regex.Matcher
import java.util.regex.Pattern

import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

import internal.GlobalVariable as GlobalVariable


String dirName = RunConfiguration.getProjectDir()
println("Project Directory: "+dirName)

Mobile.startApplication(dirName+'/AppFile/Android Messages.apk', false)

Mobile.tap(findTestObject('OR message/android.widget.TextView - Mr Sharma'), 5)

Mobile.delay(5)

String msg = Mobile.getText(findTestObject('OR message/android.widget.TextView - Your Apple ID code is'), 5)

println(msg)

Pattern p1 = Pattern.compile('Your Apple ID code is: ([0-9]+). Do not share it with anyone')

//Pattern p1 = Pattern.compile('The OTP is ([0-9]+). NEVER SHARE IT WITH ANYONE')
Matcher m1 = p1.matcher(msg)

if (m1.find()) {
    println('Apple ID code:- ' + m1.group(1))

    GlobalVariable.otp = m1.group(1)
}

Mobile.closeApplication()

