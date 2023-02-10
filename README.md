# Introduction

This project demonstrates how katalon studio helps users to extract OTP from different -2 platforms for the test automation script. It contain some test cases for Mobile OTP extraction, some test cases for Outlook and Gmail OTP extraction.

# Requirements

* [Katalon Studio][KS]
* Configure outlook/gmail for OTP extraction
* Connect with your mobile device for OTP extraction

# Use case 

Use Case No 1: Extract OTP from outlook.

    * Extract OTP/number
    * Use the extracted otp for verification(web/mobile/api)    

Use Case No 2: Extract OTP from gmail.

    * Extract OTP/number
    * Use the extracted otp for verification(web/mobile/api)
      
Use Case No 2: Extract OTP from mobile.

    * Connect mobile device with system.
    * Launch the .apk file
    * Extract OTP/number
    * Use the extracted otp for verification(web/mobile/api)
  

# How to automate

For Gmail/Outlook

    * Refer to the attached test cases in our git repository.

    * Change username and password for Gmail and outlook in the test cases.

    * Change the Email subject and body content from where user want to extract the OTP


For Mobile

    * Connect with your mobile device

    * Run the test on android device


* Run the test execution. [Here][5]

* Verify the test execution result. [Here][6]

[1]: <https://docs.katalon.com/docs/maintain/self-healing-tests-in-katalon-studio#configure-test-design> "Here"
[2]: <https://docs.katalon.com/docs/author/manage-projects/project-settings/desired-capabilities/manage-desired-capabilities-in-katalon-studio#ariaid-title1> "Here"
[3]: <https://docs.katalon.com/docs/author/record-and-spy/webui-record-and-spy-utilities/record-web-utility-in-katalon-studio#record-a-new-test-case> "Here"
[4]: <https://docs.katalon.com/docs/author/data-driven-testing/global-variables-and-execution-profile#execution-profile> "Here"
[5]: <https://docs.katalon.com/docs/execute/execute-tests-with-katalon-studio/execute-tests-with-katalon-studio-overview#ariaid-title1> "Here"
[6]: <https://docs.katalon.com/docs/analyze/reports/view-test-reports/view-test-reports-in-katalon-testops/view-test-results-and-execution-logs-in-katalon-testops#ariaid-title1> "Here"
[KS]: <https://docs.katalon.com/docs/get-started/katalon-studio-installation/install-katalon-studio-on-macoswindows#download-katalon-studio> "Katalon Studio"
