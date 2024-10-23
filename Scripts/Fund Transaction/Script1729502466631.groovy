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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.keyword.internal.WebUIAbstractKeyword as WebUIAbstractKeyword
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.console.ui.BytecodeCollector as BytecodeCollector
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import java.io.FileOutputStream as FileOutputStream
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook as XSSFWorkbook
import java.io.BufferedWriter as BufferedWriter
import java.io.FileWriter as FileWriter

WebUI.openBrowser('http:cams-frontend-uat.apps.ocp4dev.muf.co.id')

WebUI.maximizeWindow()

TestData DataLogin = TestDataFactory.findTestData('Data Files/Login')

TestData DataFundTransaction = TestDataFactory.findTestData('Data Files/Fund Transaction')

int RowDataLogin = DataLogin.getRowNumbers()

int RowDataFundTransaction = DataFundTransaction.getRowNumbers()

for (int i = 1; i <= RowDataFundTransaction; i++) {
    WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), DataLogin.getValue(1, 4))

    WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), DataLogin.getValue(2, 4))

    WebUI.sendKeys(findTestObject('Login/Field_Password'), Keys.chord(Keys.ENTER))

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Menu_Fund Transaction'))

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Menu_Tab Export'))

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Checkbox_Transaction Type'))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Fund Transaction/Field_Sub Transaction Type'), SubTransactionType(
            DataFundTransaction.getValue('Sub Transaction Type', i)), false)

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode Start Date'))

    // untuk melakukan block character yang ada difield
    String ScriptStartDate = ('var textSelector = document.querySelector(\'input#start-date-history-post-ft\') ;' + 'textSelector.focus();') + 
    'textSelector.select();'

    WebUI.executeJavaScript(ScriptStartDate, null)

    WebUI.sendKeys(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode Start Date'), Keys.chord(Keys.BACK_SPACE))

    WebUI.setText(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode Start Date'), DataFundTransaction.getValue(
            'Periode Start Date', i))

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode End Date'))

    // untuk melakukan block character yang ada difield
    String ScriptEndDate = ('var textSelector = document.querySelector(\'input#end-date-history-post-ft\') ;' + 'textSelector.focus();') + 
    'textSelector.select();'

    WebUI.executeJavaScript(ScriptEndDate, null)

    WebUI.sendKeys(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode End Date'), Keys.chord(Keys.BACK_SPACE))

    WebUI.setText(findTestObject('Object Repository/Menu/Fund Transaction/Field_Periode End Date'), DataFundTransaction.getValue(
            'Periode End Date', i))

    WebUI.click(findTestObject('Object Repository/Menu/Fund Transaction/Button_search'))
	
    memindahkanDataKeExcel() 
 	
	   
   
   
} 


def SubTransactionType(String getSubTransactionType) {
    if (getSubTransactionType == 'ALL') {
        return 'ALL'
    } else if (getSubTransactionType == 'CLAIM PETTY CASH') {
        return '2'
    } else {
        return '7'
    }
}

def memindahkanDataKeExcel() {
    int RefferenceNo = 0

    String GetDataTanggal

    String CredittedAccount = '1810002145601/MANDIRI UTAMA FINANC(IDR)'

    String DebittedAccount = '1240069888999/MANDIRI UTAMA FINANC(IDR)'

    String Remark = 'Oke'

    String GetDataDocNumber

    String GetDataDuit

    String SuccessfullorFail = 'Success'

    String filePath = 'Data Upload/Transaction Status.csv'

    WebDriver driver = DriverFactory.getWebDriver()

    String tableXpath = '//*[@id="tbl-fund-trans-export"]'

    WebElement table = driver.findElement(By.xpath(tableXpath))

    List<WebElement> TableRows = table.findElements(By.tagName('tr'))

    int jumlahRow = TableRows.size()

    BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))

    writer.write('Executed Date,Refference No.,Creditted Account,Debitted Account,Remark,Customer Reference No,Amount,Successfull/Fail,Reason')

    writer.newLine()

    for (int i = 1; i < jumlahRow; i++) {
        List<WebElement> tableCells = TableRows.get(i).findElements(By.tagName('td'))

        int jumlahCell = tableCells.size()

        for (int j = 1; j < jumlahCell; j++) {
             GetDataDocNumber = tableCells.get(3).getText()

             GetDataTanggal = tableCells.get(7).getText()

             GetDataDuit = tableCells.get(13).getText().replace(',', '')
        }
        
        String csvLine = ((((((((((((((GetDataTanggal + ',') + RefferenceNo) + ',') + CredittedAccount) + ',') + DebittedAccount) + 
        ',') + Remark) + ',') + GetDataDocNumber) + ',') + GetDataDuit) + ',') + SuccessfullorFail) + ','

        writer.write(csvLine)

        writer.newLine()
    }
    
    writer.close()
}