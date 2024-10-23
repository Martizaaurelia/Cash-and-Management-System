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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testdata.reader.ExcelFactory as ExcelFactory
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import java.util.Random as Random

WebUI.openBrowser('http://cams-frontend-uat.apps.ocp4dev.muf.co.id')

WebUI.maximizeWindow()

TestData DataPettyCashTransaction = TestDataFactory.findTestData('Data Files/Petty Cash Transaction (1)')

TestData DataLogin = TestDataFactory.findTestData('Data Files/Login')

int RowDataLogin = DataLogin.getRowNumbers()

int RowDataPettyCashTransaction = DataPettyCashTransaction.getRowNumbers()

for (int i = 1; i <= RowDataPettyCashTransaction; i++) {
	
	WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), DataLogin.getValue('NIK', 7))

	WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), DataLogin.getValue('Password', 7))

	WebUI.sendKeys(findTestObject('Login/Field_Password'), Keys.chord(Keys.ENTER))
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_menu'))
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_submenu'))
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/tab_transaction'))
	
	WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Petty Cash Transaction/field_cabangMufnet'), DataPettyCashTransaction.getValue(
		'Cabang', 2), false)
	
	WebUI.setText(findTestObject('Object Repository/Menu/Petty Cash Transaction/Field_Description'), DataPettyCashTransaction.getValue(
		'Desc', 2))
	
	TestObject transaction = new TestObject()
	
	for (int j = 1; j < 9; j++) {
		
		WebUI.scrollToElement(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_addDetail'), 5)
		
		WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_addDetail'))
	
		WebUI.scrollToElement(findTestObject('Object Repository/Menu/Petty Cash Transaction/Petty Cash Transaction/data'), 5)
		
		//  Button Search Transaction ID
	
		transaction.addProperty('xpath', ConditionType.EQUALS, ('//*[@id="tbl-pct-detail"]/tbody/tr[' + j) + ']/td[1]/div/span')
	
		WebUI.click(transaction)
		
		//  Select Transaction ID
		
		transaction.addProperty('xpath', ConditionType.EQUALS, ('//*[@id="modal-tbl-trans-id"]/tbody/tr[' + j) + ']/td[1]/input')
		 
		WebUI.click(transaction)
	
		WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_pilihTransaction'))
		
		//  Button Search Cost Center
		
		transaction.addProperty('xpath', ConditionType.EQUALS, ('//*[@id="tbl-pct-detail"]/tbody/tr[' + j) + ']/td[2]/div/span')
		 
		WebUI.click(transaction)
		
		//	Select Cost Center
	
		transaction.addProperty('xpath', ConditionType.EQUALS, ('//*[@id="modal-tbl-cost-center"]/tbody/tr[' + j) + ']/td[1]/input')
		 
		WebUI.click(transaction)
	
		WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_pilihCostCenter'))
		
		//  Input Originating Amount
	
		String originatingAmount = ''
		if (i == 1) {
			originatingAmount = '100.000'
		} else if (i == 2) {
			originatingAmount = '200.000'
		} else {
			originatingAmount = '300.000'
		}
		
		transaction.addProperty('xpath', ConditionType.EQUALS, '//*[@id="tbl-pct-detail"]/tbody/tr[' + j + ']/td[4]/input')
		
		WebUI.setText(transaction, originatingAmount)
		
	}
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_submit'))
	
	// Jika data sudah tidak ada, klik button ok
	
	if (WebUI.verifyElementPresent(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_keMonitoringRequest'), 5)) {
	
	} else if (WebUI.verifyElementPresent(findTestObject('Object Repository/Menu/Petty Cash Transaction/Petty Cash Transaction/button_OK'), 5)) {
	
	WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Transaction/button_OK'))
	
	}
	
	WebUI.closeBrowser()
	
//	WebUI.callTestCase(findTestCase('To Do List Approval'), [:], FailureHandling.STOP_ON_FAILURE)

}