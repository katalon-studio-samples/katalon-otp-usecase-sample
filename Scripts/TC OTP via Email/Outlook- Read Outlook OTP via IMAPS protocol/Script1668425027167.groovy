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

String mailFolderName = 'INBOX'

String emailSubjectContent = 'Test OTP Outlook'

String emailContent = 'This is your OTP for Outlook: '

int lengthOfOTP = 5

String hostName = 'outlook.office365.com' //change it according to your mail

String username = 'mohit@outlook.com' //username

String password = 'Outlook Password'

String searchText = null

Properties sysProps = new Properties()

sysProps.put('mail.store.protocol', 'imaps')

sysProps.put('mail.imaps.socketFactory.class', 'javax.net.ssl.SSLSocketFactory')

sysProps.put('mail.imaps.socketFactory.fallback', 'false')

sysProps.put('mail.imaps.port', '993')

sysProps.put('mail.imaps.socketFactory.port', '993')

Session session = Session.getDefaultInstance(sysProps, null)

//session.setDebug(true)
Store store = session.getStore('imaps')

store.connect(hostName, username, password)

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

    if (message.getSubject().startsWith('Test OTP outlook')) {
        if (content instanceof String) {
            System.out.println('Email Number ' + (i + 1))

            System.out.println('Subject: ' + message.getSubject())

            System.out.println('From: ' + (message.getFrom()[0]))

            System.out.println('Text: ' + content)

            break
        } else if (content instanceof MimeMultipart) {
            System.out.println('Email Number ' + (i + 1))

            System.out.println('Subject: ' + message.getSubject())

            System.out.println('From: ' + (message.getFrom()[0]))

            System.out.println('Text: ' + getMutipartMessage(content))

            intOTP = extractOTP(getMutipartMessage(content))

            System.out.println('OTP from Outlook is: ' + intOTP)

            break
        }
    }
}

//close the store and folder objects
emailFolder.close(false)

store.close()

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
    Pattern p1 = Pattern.compile('This is your OTP for Outlook: ([0-9]+)')

    Matcher m1 = p1.matcher(msgcontent)

    if (m1.find()) {
        return m1.group(1)
    }
}

