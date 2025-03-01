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
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.text.SimpleDateFormat
import java.util.Date

//Format timestamp
String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
String EmailorNumberinput = 'invalidaccountya@gmail.com'
String Passwordinput = 'passngaco'
//Simpan screenshot dengan timestamp
String KetBelakang = timestamp + ".png"
String screenshotPassedPath = "C:/KatalonScreenshots/TestCase1.1/Passed/Screenshot_TestCase1.1" + KetBelakang
String screenshotFailedPathTombolMasukEnabled = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_TombolMasukEnabled" + KetBelakang
String screenshotFailedPathTombolMasukMissing = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_TombolMasukMissing" + KetBelakang
String screenshotFailedPathGagalInputPasswordKeTextField = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_GagalInputPassword" + KetBelakang
String screenshotFailedPathGagalInputEmailorNomorKeTextField = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_GagalInputEmailorNomor" + KetBelakang
String screenshotFailedWordingErrorMessageEmailorPasswordNotRegisteredNotMatch = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_WordingErrorMessageTidakMatch" + KetBelakang
String screenshotFailedWordingErrorMessageEmailorPasswordNotRegisteredTidakMuncul = "C:/KatalonScreenshots/TestCase1.1/Failed/Screenshot_WordingErrorMessageTidakMuncul" + KetBelakang

//CallTCFlow
Mobile.callTestCase(findTestCase('Test Cases/FlowUntukMasukKeHalamanLoginPageEraspace'), [:], FailureHandling.STOP_ON_FAILURE)

// Test Case 1.1 - Login dengan data invalid
KeywordUtil.logInfo('Test Case 1.1 - Login dengan invalid data')

Mobile.setText(findTestObject('Object Repository/TextFieldEmailorPhoneNumber'), EmailorNumberinput, 5)

String enteredUsername = Mobile.getAttribute(findTestObject('Object Repository/VerifyEmailV1'), 'text', 5)

if (enteredUsername == EmailorNumberinput) {
    KeywordUtil.markPassed('Email/Password Berhasil Diinput pada Email/Password')
} else {
	Mobile.takeScreenshot(screenshotFailedPathGagalInputEmailorNomorKeTextField)
    KeywordUtil.markFailed('Email/Password Tidak Berhasil Diinput pada Email/Password')
}

// Objek yang akan dicek
TestObject errorMessage = findTestObject('Object Repository/ErrorMessageEmailTakTerdaftarYa')


	// Cek apakah elemen ada
	if (Mobile.waitForElementPresent(errorMessage, 10, FailureHandling.OPTIONAL)) {

		String actualText = Mobile.getAttribute(errorMessage, 'text', 5, FailureHandling.OPTIONAL)
		

		String expectedText = 'Email belum terdaftar'
		
		// Validasi apakah teks sesuai
		if (actualText == expectedText) {
			KeywordUtil.markPassed("✅ Wording sesuai: " + actualText)
		} else {
			KeywordUtil.markFailed("⚠️ Wording tidak sesuai! Expected: '" + expectedText + "', but found: '" + actualText + "'")
			Mobile.takeScreenshot(screenshotFailedWordingErrorMessageEmailorPasswordNotRegisteredNotMatch)
		}
	} else {
		KeywordUtil.markFailed("❌ Element tidak ditemukan!")
		Mobile.takeScreenshot(screenshotFailedWordingErrorMessageEmailorPasswordNotRegisteredTidakMuncul)
	}


Mobile.setText(findTestObject('Object Repository/TextFieldPassword'), Passwordinput, 5)

Mobile.tap(findTestObject('Object Repository/ComponentMata'), 0)

String enteredPassword = Mobile.getAttribute(findTestObject('Object Repository/VerifyPasswordV1'), 'text', 5)

if (enteredPassword == Passwordinput) {
    KeywordUtil.markPassed('Password berhasil diinput pada Text Field Password')
} else {
	Mobile.takeScreenshot(screenshotFailedPathGagalInputPasswordKeTextField)
    KeywordUtil.markFailed('Password gagal diinput pada Text Field Password')
}

// Validasi tombol "Masuk" unclickable
TestObject loginButton = findTestObject('Object Repository/android.widget.Button - Masuk')
Mobile.waitForElementPresent(loginButton, 5)
String isEnabled = Mobile.getAttribute(loginButton, 'enabled', 5)

// Cek apakah tombol dalam keadaan aktif atau tidak
if (isEnabled == 'true') {
    KeywordUtil.markFailed('✅ Tombol Masuk dalam keadaan ENABLED')
	Mobile.takeScreenshot(screenshotFailedPathTombolMasukEnabled)
} else if (isEnabled == 'false') {
    KeywordUtil.markPassed('❌ Tombol Masuk dalam keadaan DISABLED')
} else {
	Mobile.takeScreenshot(screenshotFailedPathTombolMasukMissing)
    KeywordUtil.markFailed('⚠️ Gagal mendapatkan status tombol Masuk')
}






Mobile.takeScreenshot(screenshotPassedPath)

Mobile.closeApplication()

