import javax.mail.BodyPart as BodyPart
import javax.mail.Folder as Folder
import javax.mail.Message as Message
import javax.mail.Session as Session
import javax.mail.Store as Store
import javax.mail.internet.MimeMultipart as MimeMultipart
import org.jsoup.Jsoup as Jsoup

String host = 'outlook.office365.com'

String username = 'mohit@outlook.com'

String password = 'Outlook Password'

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

for (int i : (0..messages.length - 1)) {
    Message message = messages[i]

    System.out.println('---------------------------------')

    System.out.println('Email Number ' + (i + 1))

    System.out.println('Subject: ' + message.getSubject())

    System.out.println('From: ' + (message.getFrom()[0]))

    Object content = message.getContent()

    if (content instanceof String) {
        System.out.println('Text: ' + content)
    } else if (content instanceof MimeMultipart) {
        System.out.println('Text: ' + getMutipartMessage(content))
    }
}

//close the store and folder objects
emailFolder.close(false)

store.close( // alternatives appear in an order of increasing
    ) // faithfulness to the original content. Customize as req'd.
//without break same text appears twice in my tests

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

