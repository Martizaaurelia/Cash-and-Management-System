import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.security.Key as Key
import java.util.concurrent.ConcurrentHashMap.KeySetView as KeySetView
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
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.testdata.ExcelData as ExcelData
import org.eclipse.persistence.internal.jpa.metadata.converters.TemporalMetadata as TemporalMetadata
import org.openqa.selenium.Keys as Keys

//WebDriver webDrive = DriverFactory.getWebDriver()
//String TableTabFormParameterPlafonPettyCash = '//*[@id="tbl-param-saldo-pc"]'
//WebElement WBE = webDrive.findElement(By.xpath(TableTabFormParameterPlafonPettyCash))
//List<WebElement> RowsTable = WBE.findElements(By.tagName('tr'))
WebUI.openBrowser('http://cams-frontend-uat.apps.ocp4dev.muf.co.id')

WebUI.maximizeWindow()

// Ambil data diexcel
TestData DataPettyCashRequest = TestDataFactory.findTestData('Data Files/Petty Cash Request')

TestData DataLogin = TestDataFactory.findTestData('Data Files/Login')

int RowDataLogin = DataLogin.getRowNumbers()

int RowDataPettyCashRequest = DataPettyCashRequest.getRowNumbers()

for (int i = 1; i <= RowDataPettyCashRequest; i++) {
    WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), DataLogin.getValue(1, 1))

    WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), DataLogin.getValue(2, 1))

    WebUI.sendKeys(findTestObject('Login/Field_Password'), Keys.chord(Keys.ENTER))

    WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Menu_Petty Cash'))

    WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Menu_Petty Cash Transaction'))

    WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Cabang Atau Mufnet'))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Cabang Atau Mufnet'), DataPettyCashRequest.getValue(
            1, i), false)

    WebUI.setText(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Description'), DataPettyCashRequest.getValue(
            2, i))

    WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Peruntukan'))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Peruntukan'), Peruntukan(DataPettyCashRequest.getValue(
                3, i)), false)

    WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Peruntukan'))

    //Jika sudah ada plafonnya maka user akan langsung logout
    if (WebUI.getAttribute(findTestObject('Object Repository/Menu/Petty Cash Request/Field_Plafon Saat ini'), 'value') == 
    '') {
        WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))

        WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))

        WebUI.closeBrowser() 
		
		WebUI.callTestCase(findTestCase('Parameter Plafon Petty Cash'), [:], FailureHandling.STOP_ON_FAILURE)
        
    } else {
        WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Button_Submit'))

        WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Button_Yakin Konfirmasi'))

        WebUI.click(findTestObject('Object Repository/Menu/Petty Cash Request/Button_OK Setelah berhasil konfirmasi'))

        WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))

        WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))

        WebUI.closeBrowser()
		
		WebUI.callTestCase(findTestCase('To do List Approval'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

def Peruntukan(String TujuanPeruntukan) {
    if (TujuanPeruntukan == 'PLAFON AWAL') {
        return '1'
    } else {
        return '2'
    }
}

//