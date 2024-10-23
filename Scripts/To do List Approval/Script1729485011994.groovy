import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import javax.media.rtp.rtcp.SenderReport as SenderReport
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

WebUI.openBrowser('http:cams-frontend-uat.apps.ocp4dev.muf.co.id')

WebUI.maximizeWindow()

TestData DataLogin = TestDataFactory.findTestData('Data Files/Login')

TestData DataToDoList = TestDataFactory.findTestData('Data Files/To do List')

int RowDataLogin = DataLogin.getRowNumbers()

int RowDataToDoList = DataToDoList.getRowNumbers()


for (int i = 1; i <= RowDataToDoList; i++) {
    WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), DataLogin.getValue(1, 2))

    WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), DataLogin.getValue(2, 2))

    WebUI.sendKeys(findTestObject('Login/Field_Password'), Keys.chord(Keys.ENTER))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/To do list'))

    WebUI.setText(findTestObject('Object Repository/Menu/To do list/Field_Periode start date'), DataToDoList.getValue('Periode Start Date', 
            i))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'))

    // untuk melakukan block character yang ada difield
    String Script = ('var textSelector = document.querySelector(\'input#end-date-tdl\') ;' + 'textSelector.focus();') + 
    'textSelector.select();'

    WebUI.executeJavaScript(Script, null)

    WebUI.sendKeys(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'), Keys.chord(Keys.BACK_SPACE))

    WebUI.setText(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'), DataToDoList.getValue('Periode end Date', 
            i))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_Transaction Type'), TransactionType(
            DataToDoList.getValue('Transaction Type', i)), false)

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_Sub transaction type'), SubTransactionType(
            DataToDoList.getValue('Sub Transaction Type', i)), false)

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Search'))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/CheckBox_Transaction to do list'))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_action edit'))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Field_text area notes'))

    WebUI.sendKeys(findTestObject('Object Repository/Menu/To do list/Field_text area notes'), DataToDoList.getValue('Notes', 
            i))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_approval Status'), ApprovalStatus(
            DataToDoList.getValue('Approval Status', i)), false)

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Submit'))

    WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Yakin Konfirmasi'))
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Button_OK Setelah berhasil konfirmasi'))
	
}

WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))

WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))

for (int i = 1; i <= RowDataToDoList; i++) {
	WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), DataLogin.getValue(1, 5))

	WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), DataLogin.getValue(2, 5))

	WebUI.sendKeys(findTestObject('Login/Field_Password'), Keys.chord(Keys.ENTER))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/To do list'))

	WebUI.setText(findTestObject('Object Repository/Menu/To do list/Field_Periode start date'), DataToDoList.getValue('Periode Start Date',
			i))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'))

	// untuk melakukan block character yang ada difield
	String Script = ('var textSelector = document.querySelector(\'input#end-date-tdl\') ;' + 'textSelector.focus();') +
	'textSelector.select();'

	WebUI.executeJavaScript(Script, null)

	WebUI.sendKeys(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'), Keys.chord(Keys.BACK_SPACE))

	WebUI.setText(findTestObject('Object Repository/Menu/To do list/Field_Periode end date'), DataToDoList.getValue('Periode end Date',
			i))

	WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_Transaction Type'), TransactionType(
			DataToDoList.getValue('Transaction Type', i)), false)

	WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_Sub transaction type'), SubTransactionType(
			DataToDoList.getValue('Sub Transaction Type', i)), false)

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Search'))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/CheckBox_Transaction to do list'))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_action edit'))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Field_text area notes'))

	WebUI.sendKeys(findTestObject('Object Repository/Menu/To do list/Field_text area notes'), DataToDoList.getValue('Notes',
			i))

	WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/To do list/Field_approval Status'), ApprovalStatus(
			DataToDoList.getValue('Approval Status', i)), false)

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Submit'))

	WebUI.click(findTestObject('Object Repository/Menu/To do list/Button_Yakin Konfirmasi'))
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Button_OK Setelah berhasil Konfirmasi Approval ke 2'))
	
}

WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))

WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))

WebUI.callTestCase(findTestCase('Fund Transaction'), [:], FailureHandling.STOP_ON_FAILURE)



def TransactionType(String getTransactionType) {
    if (getTransactionType == 'PETTY CASH') {
        return '1'
    } else {
        return '2'
    }
}

def SubTransactionType(String getSubTransactionType) {
    if (getSubTransactionType == 'ALL') {
        return 'ALL'
    } else if (getSubTransactionType == 'PETTY CASH TRANSACTION') {
        return '1'
    } else if (getSubTransactionType == 'CLAIM PETTY CASH') {
        return '2'
    } else if (getSubTransactionType == 'PETTY CASH OPNAME') {
        return '3'
    } else {
        return '7'
    }
}

def ApprovalStatus(String getApprovalStatus) {
    if (getApprovalStatus == 'APPROVE') {
        return 'Approved'
    } else {
        return ''
    }
}