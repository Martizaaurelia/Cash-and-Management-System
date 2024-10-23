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
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

TestData DataParameterPlafonPettyCash = TestDataFactory.findTestData('Data Files/Parameter Plafon Petty Cash')

int RowDataParameterPlafonPettyCash = DataParameterPlafonPettyCash.getRowNumbers()

WebUI.openBrowser('http://cams-frontend-uat.apps.ocp4dev.muf.co.id')

WebUI.maximizeWindow()

WebUI.setText(findTestObject('Object Repository/Login/Field_Username'), '17004250')

WebUI.setText(findTestObject('Object Repository/Login/Field_Password'), '123')

WebUI.sendKeys(findTestObject('Object Repository/Login/Button_Login'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Menu_Master'))

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Menu_Parameter Plafon Petty Cash'))

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Tab_Form'))

if (RowDataParameterPlafonPettyCash != 1) {
    for (int j = 1; j <= RowDataParameterPlafonPettyCash; j++) {
        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Location'))

        WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Location'), DataLocation(
                DataParameterPlafonPettyCash.getValue(1, j)), false)

        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Detail Location'))

        WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Search Detail Location'), 
            DataParameterPlafonPettyCash.getValue(2, j))

        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/CheckBox_Detail Location'))

        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Pilih Detail Location'))

        //        if (WebUI.getText(findTestObject('findTestObject(\'Object Repository/Menu/Parameter Plafon Petty Cash/Field_Plafon Saat ini\')')) == 
        //        '') {
        //            WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'))
        //
        //            WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash'), Peruntukan(DataParameterPlafonPettyCash.getValue(
        //                        3, j)), false)
        //
        //            WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_plafon'), DataParameterPlafonPettyCash.getValue(
        //                    4, j))
        //
        //            WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Add'))
        //        } else {
        //			
        //			WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))
        //			
        //			WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))
        //		}
        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'))

        WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'), Peruntukan(DataParameterPlafonPettyCash.getValue(
                    3, j)), false)

        WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_plafon'), DataParameterPlafonPettyCash.getValue(
                4, j))

        WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Add'))
    }
    //	if (WebUI.getText(findTestObject('findTestObject(\'Object Repository/Menu/Parameter Plafon Petty Cash/Field_Plafon Saat ini\')')) ==
    //		'') {
    //			WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'))
    //
    //			WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash'), Peruntukan(DataParameterPlafonPettyCash.getValue(
    //						3, j)), false)
    //
    //			WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_plafon'), DataParameterPlafonPettyCash.getValue(
    //					4, j))
    //
    //			WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Add'))
    //		} else {
    //			
    //			WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))
    //			
    //			WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))
    //		}
} else {
    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Location'))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Location'), DataLocation(
            DataParameterPlafonPettyCash.getValue(1, 1)), false)

    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Detail Location'))

    WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Search Detail Location'), DataParameterPlafonPettyCash.getValue(
            2, 1))

    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/CheckBox_Detail Location'))

    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Pilih Detail Location'))

    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'))

    WebUI.selectOptionByValue(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_Peruntukan'), Peruntukan(
            DataParameterPlafonPettyCash.getValue(3, 1)), false)

    WebUI.setText(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Field_plafon'), DataParameterPlafonPettyCash.getValue(
            4, 1))

    WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Add'))
}

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Save'))

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_Yakin Konfirmasi'))

WebUI.click(findTestObject('Object Repository/Menu/Parameter Plafon Petty Cash/Button_OK Setelah Konfirmasi'))

WebUI.click(findTestObject('Object Repository/LogOut/Header_logout'))

WebUI.click(findTestObject('Object Repository/LogOut/Button_logout'))

WebUI.callTestCase(findTestCase('Petty Cash Request'), [:], FailureHandling.STOP_ON_FAILURE)

def DataLocation(String Location) {
    String HasilLocation

    if (Location == 'HO') {
        return HasilLocation = 'HO'
    } else if (Location == 'AREA') {
        return HasilLocation = 'AR'
    } else if (Location == 'CABANG') {
        return HasilLocation = 'BR'
    } else {
        return HasilLocation = 'MF'
    }
}

def Peruntukan(String TujuanPeruntukan) {
    String HasilTujuanPeruntukan

    if (TujuanPeruntukan == 'PLAFON AWAL') {
        return HasilTujuanPeruntukan = '1'
    } else {
        return HasilTujuanPeruntukan = '2'
    }
}