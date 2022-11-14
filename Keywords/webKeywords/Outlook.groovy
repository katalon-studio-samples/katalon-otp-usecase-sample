package webKeywords

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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import javax.mail.BodyPart as BodyPart
import javax.mail.Folder as Folder
import javax.mail.Message as Message
import javax.mail.Session as Session
import javax.mail.Store as Store
import javax.mail.internet.MimeMultipart as MimeMultipart
import org.jsoup.Jsoup as Jsoup
import java.util.regex.Matcher as Matcher
import java.util.regex.Pattern as Pattern
import internal.GlobalVariable as GlobalVariable


public class Outlook {

	@Keyword(keywordObject='WebCustomKeyword')
	def String getOTPfromOutlook(String email_subject) {

		String host = 'outlook.office365.com'

		String username = 'mohit@outlook.com'

		String password = 'Outlook password Password'

		Properties properties = new Properties()

		properties.put('mail.pop3.host', host)

		properties.put('mail.pop3.port', '995')

		properties.put('mail.pop3.starttls.enable', 'true')

		Session emailSession = Session.getDefaultInstance(properties)

		//create the POP3 store object and connect with the pop server
		Store store = emailSession.getStore('pop3s')

		store.connect(host, username, password)

		//create the folder object and open it
		Folder emailFolder = store.getFolder('INBOX')

		emailFolder.open(Folder.READ_ONLY)

		// retrieve the messages from the folder in an array and print it
		Message[] messages = emailFolder.getMessages()

		System.out.println('messages.length---' + messages.length)

		int msglen = messages.length - 1

		for (int i = msglen; i >= 0; i--) {
			Message message = messages[i]

			System.out.println('---------------------------------')

			//System.out.println('Email Number ' + (i + 1))
			//System.out.println('Subject: ' + message.getSubject())
			//System.out.println('From: ' + (message.getFrom()[0]))
			Object content = message.getContent()

			if (message.getSubject().startsWith(email_subject)) {
				if (content instanceof String) {
					System.out.println('Email Number ' + (i + 1))

					System.out.println('Subject: ' + message.getSubject())

					System.out.println('From: ' + (message.getFrom()[0]))

					System.out.println('Text: ' + content)

					String intOTP = extractOTP(getMutipartMessage(content))

					System.out.println('OTP from Outlook is: ' + intOTP)

					return intOTP


					break
				} else if (content instanceof MimeMultipart) {
					System.out.println('Email Number ' + (i + 1))

					System.out.println('Subject: ' + message.getSubject())

					System.out.println('From: ' + (message.getFrom()[0]))

					System.out.println('Text: ' + getMutipartMessage(content))

					String intOTP = extractOTP(getMutipartMessage(content))

					System.out.println('OTP from Outlook is: ' + intOTP)

					return intOTP

					break
				}
			}
		}

		//close the store and folder objects
		emailFolder.close(false)

		// alternatives appear in an order of increasing
		// faithfulness to the original content. Customize as req'd.
		//without break same text appears twice in my tests
		//println('OTP for email:- ' + m1.group(1))
		store.close()
	}
	def getMutipartMessage(MimeMultipart mimeMultipart) {
		String result = ''

		int count = mimeMultipart.getCount()

		for (def j : (0..count - 1)) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(j)

			if (bodyPart.isMimeType('text/plain')) {
				result = ((result + '\n') + bodyPart.getContent())

				break
			} else if (bodyPart.isMimeType('text/html')) {
				String html = ((bodyPart.getContent()) as String)

				result = ((result + '\n') + Jsoup.parse(html).text())
			}
		}

		return result
	}

	def extractOTP(String msgcontent) {
		Pattern p1 = Pattern.compile('This is your OTP for Outlook: ([0-9]+).')

		Matcher m1 = p1.matcher(msgcontent)

		if (m1.find()) {
			return m1.group(1)
		}
	}

}
