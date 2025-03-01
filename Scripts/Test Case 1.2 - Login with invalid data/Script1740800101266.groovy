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
import io.appium.java_client.android.AndroidDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
// Memanggil test case lain
Mobile.callTestCase(findTestCase('Test Cases/FlowUntukMasukKeHalamanLoginPageEraspace'), [:], FailureHandling.STOP_ON_FAILURE)




// Format timestamp
String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
String EmailorNumberinput = 'neverwalkalonev18@gmail.com'
String Passwordinput = 'Tokel313@'
// Simpan screenshot dengan timestamp
String KetBelakang = timestamp + ".png"
String screenshotPassedPathV1 = "C:/KatalonScreenshots/TestCase1.2/Passed/Screenshot_TestCase1.2V1" + KetBelakang
String screenshotPassedPathV2 = "C:/KatalonScreenshots/TestCase1.2/Passed/Screenshot_TestCase1.2V1" + KetBelakang
String screenshotFailedPathTombolMasukDisabled = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_TombolMasukDisabled" + KetBelakang
String screenshotFailedPathTombolMasukMissing = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_TombolMasukMissing" + KetBelakang
String screenshotFailedPathGagalInputPasswordKeTextField = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_GagalInputPassword" + KetBelakang
String screenshotFailedPathGagalInputEmailorNomorKeTextField = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_GagalInputEmailorNomor" + KetBelakang
String screenshotFailedErrorMessageMunculPadahalTidakBoleh = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_ErrorMessageMunculPadahalTidakBoleh" + KetBelakang
String ScreenshotInvalidRedirectionAfterLogin = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_InvalidRedirection" + KetBelakang
String ScreenshotToasterNotShown = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_ToasternotShown" + KetBelakang
String SectionListPoinNotShown = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_SectionListPointNotShown" + KetBelakang
String ValueListPointNotShown = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_ValueListPointNotShown" + KetBelakang
String ListMenuBottomNavNotShown = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_ListMenuBottomNavNotShown" + KetBelakang 
String ScreenshotWrongUsername = "C:/KatalonScreenshots/TestCase1.2/Failed/Screenshot_ScreenshotWrongUsername" + KetBelakang 

// Test Case 1.1 - Login dengan data invalid
KeywordUtil.logInfo('Test Case 1.1 - Login dengan invalid data')

Mobile.setText(findTestObject('Object Repository/TextFieldEmailorPhoneNumber'), EmailorNumberinput, 2)

String enteredUsername = Mobile.getAttribute(findTestObject('Object Repository/1.2 Object Repository/VerifyEmailorNumberV2'), 'text', 5)

if (enteredUsername == EmailorNumberinput) {
    KeywordUtil.markPassed('Email/Password Berhasil Diinput pada Email/Password')
} else {
	Mobile.takeScreenshot(screenshotFailedPathGagalInputEmailorNomorKeTextField)
    KeywordUtil.markFailed('Email/Password Tidak Berhasil Diinput pada Email/Password')
}



Mobile.setText(findTestObject('Object Repository/1.2 Object Repository/VerifyPasswordV2'), Passwordinput, 2)

Mobile.tap(findTestObject('Object Repository/ComponentMata'), 0)

String enteredPassword = Mobile.getAttribute(findTestObject('Object Repository/1.2 Object Repository/VerifyPasswordV2AfterInput'), 'text', 5)

if (enteredPassword == Passwordinput) {
    KeywordUtil.markPassed('Password berhasil diinput pada Text Field Password')
} else {
	Mobile.takeScreenshot(screenshotFailedPathGagalInputPasswordKeTextField)
    KeywordUtil.markFailed('Password gagal diinput pada Text Field Password')
}
//Markicek Seharusnya Error Message Tidak Muncul
TestObject errorMessage = findTestObject('Object Repository/ErrorMessageEmailTakTerdaftarYa')
if (!Mobile.waitForElementPresent(errorMessage, 1, FailureHandling.OPTIONAL)) {
	KeywordUtil.markPassed("✅ Error message tidak muncul, test PASSED!")
} else {
	KeywordUtil.markFailed("❌ ERROR: 'Email belum terdaftar' muncul, seharusnya tidak ada!")
	Mobile.takeScreenshot("screenshotFailedErrorMessageMunculPadahalTidakBoleh")
}

// Validasi tombol "Masuk" Clickable
TestObject loginButton = findTestObject('Object Repository/android.widget.Button - Masuk')
Mobile.waitForElementPresent(loginButton, 2)
String isEnabled = Mobile.getAttribute(loginButton, 'enabled', 1)

// Cek apakah tombol dalam keadaan aktif atau tidak
if (isEnabled == 'true') {
    KeywordUtil.markPassed('✅ Tombol Masuk dalam keadaan ENABLED')
} else if (isEnabled == 'false') {
    KeywordUtil.markFailed('❌ Tombol Masuk dalam keadaan DISABLED')
	Mobile.takeScreenshot(screenshotFailedPathTombolMasukDisabled)
} else {
	Mobile.takeScreenshot(screenshotFailedPathTombolMasukMissing)
    KeywordUtil.markFailed('⚠️ Gagal mendapatkan status tombol Masuk')
}






Mobile.takeScreenshot(screenshotPassedPathV1)
Mobile.tap(findTestObject('Object Repository/android.widget.Button - Masuk'), 1)

Mobile.delay(1)
//CheckCurrentActivitySesuaiAtauTidak 
AndroidDriver driver = (AndroidDriver) MobileDriverFactory.getDriver()

	// Ambil current activity
	String currentActivity = driver.currentActivity()
	
	// Log output current activity
	KeywordUtil.logInfo("📌 Current Activity: " + currentActivity)

	// Validasi apakah currentActivity sesuai
	if (currentActivity.contains("com.eraspace.home.presentation.HomeActivity")) {
		KeywordUtil.markPassed("✅ Success Redirection Sesuai ke Home Page")
	} else {
		KeywordUtil.markFailed("❌ Redirection Tidak Sesuai Sekarang malah Redirect ke Page/Activity : " + currentActivity)
	    Mobile.takeScreenshot(ScreenshotInvalidRedirectionAfterLogin)
	}

	
	
	
	//ClosePop Up Verif phone
	// Cek apakah PopUpVerifPhoneNumber muncul
	if (Mobile.verifyElementExist(findTestObject('Object Repository/1.2 Object Repository/PopUpVerifPhoneNumber'), 1, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Object Repository/1.2 Object Repository/ButtonClosePopUpPhoneNumber'), 0)
	}
	

	// Menunggu SectionListPoint muncul dalam waktu tertentu || Menampilkan List point
	boolean isSectionListPointVisible = Mobile.verifyElementVisible(findTestObject('Object Repository/1.2 Object Repository/SectionListPoint'), 1, FailureHandling.OPTIONAL)
	
	if (!isSectionListPointVisible) {
		KeywordUtil.markFailed('SectionListPoint tidak muncul')
		Mobile.takeScreenshot(SectionListPoinNotShown)
	} else {
		// 2. Memastikan **ValueListPoint** muncul di dalamnya
		boolean isValueListPointVisible = Mobile.verifyElementVisible(findTestObject('Object Repository/1.2 Object Repository/ValueListPoint'), 1, FailureHandling.OPTIONAL)
		
		if (!isValueListPointVisible) {
			KeywordUtil.markFailed("Value List Point Tak Muncul")
			Mobile.takeScreenshot(ValueListPointNotShown)
		}
	}
	
	//CheckBottomnav Muncul Atau tidak, kalau tidak maka akan take screenshot || Menampilkan List menu
	if (!Mobile.verifyElementExist(findTestObject('Object Repository/1.2 Object Repository/ListMenuBottomNavigation'), 1, FailureHandling.OPTIONAL)) {
		Mobile.takeScreenshot(ListMenuBottomNavNotShown)
		KeywordUtil.markFailed("Bottom Navigation not Shown")
	}
	
	Mobile.tap(findTestObject('Object Repository/ButtonAkunUntukDipakaiLogout'), 0)
//Menampilkan nama Akun
	Mobile.verifyElementVisible(findTestObject("Object Repository/1.2 Object Repository/android.widget.TextView - Never Walk Alone V18"), 2)
	String cekusernameakun = Mobile.getAttribute(findTestObject('Object Repository/1.2 Object Repository/android.widget.TextView - Never Walk Alone V18'), 'text', 0)
	
	if (cekusernameakun == 'Never Walk Alone V18') {
		KeywordUtil.markPassed('Username Sesuai')
	} else {
		Mobile.takeScreenshot(ScreenshotWrongUsername)
		KeywordUtil.markFailed('Username Tidak Sesuai')
	}

Mobile.closeApplication()

