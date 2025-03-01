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
import io.appium.java_client.android.AndroidDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory

// Fungsi untuk menutup aplikasi sebelum memulai skrip
Mobile.startExistingApplication('com.eraspace.app')

Mobile.closeApplication()

Mobile.startExistingApplication('com.eraspace.app')






if (Mobile.waitForElementPresent(findTestObject('Object Repository/CheckLabelIntroBannerWhenFirstInstallingEraspace'), 1,
	FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Object Repository/ButtonLanjutkanIntroBannerFirstInstall'), 2)
}
//validation untuk check android version karna floating request permission antara android 9 dengan android 12 itu berbeda
String androidVersion = Mobile.getDeviceOSVersion()
if (androidVersion.startsWith("9")) {
	if (Mobile.waitForElementPresent(findTestObject('Object Repository/ButtonPermissionIzinkanLokasi'), 2, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Object Repository/ButtonPermissionIzinkanLokasi'), 2)
}

} else {
	
	boolean isPopupVisible = Mobile.verifyElementExist(findTestObject('Object Repository/Android12PermissionPopUpLokasi'), 2, FailureHandling.OPTIONAL)
	
	if (isPopupVisible) {
Mobile.tap(findTestObject('Object Repository/OptionRequestLokasiAndroid12SaatAplikasiDigunakan'), 1)
	} else {
		println("Popup izin lokasi tidak muncul, lanjutkan proses normal")
	}
	

}

//FunctionLogoutIfAlreadyLoggedin
if (!Mobile.verifyElementExist(findTestObject('Object Repository/CheckLoggedinOrNot'), 1, FailureHandling.OPTIONAL)) {
Mobile.startExistingApplication('com.eraspace.app/com.eraspace.home.presentation.HomeActivity')
Mobile.tap(findTestObject('Object Repository/ButtonAkunUntukDipakaiLogout'), 0)
Mobile.tap(findTestObject('Object Repository/ButtonToolsUntukDipakaiLogout'), 0)
Mobile.tap(findTestObject('Object Repository/ButtonKeluarUntukDipakaiLogout'), 0)
Mobile.tap(findTestObject('Object Repository/ButtonConfirmKeluarUntukDipakaiLogout'), 0)
Mobile.startExistingApplication('com.eraspace.app')
Mobile.closeApplication()
Mobile.startExistingApplication('com.eraspace.app')
}

Mobile.waitForElementPresent(findTestObject('Object Repository/ButtonMasukInHomePage'), 0)
Mobile.verifyElementExist(findTestObject('Object Repository/ButtonMasukInHomePage'), 0)
// Coba klik tombol "Masuk" dengan FailureHandling.OPTIONAL agar tidak langsung gagal jika tidak bisa diklik
boolean isClicked = Mobile.tap(findTestObject('Object Repository/ButtonMasukInHomePage'), 0, FailureHandling.OPTIONAL)

// Cek apakah tombol berhasil diklik atau tidak
if (!isClicked) {
//Untuk dapatkan Width dan Height dari current device (karna button lewati pada coachmark tidak dapat diassert saya jadinya menggunakan function tapatposition
int deviceWidth = Mobile.getDeviceWidth()
int deviceHeight = Mobile.getDeviceHeight()
//Hitung persentase layar dari current device saya ROG Phone 2
float xPercent = 645 / 1080.0  // 59.72% dari lebar layar
float yPercent = 1943 / 2340.0 // 83.07% dari tinggi layar

//Perhitungan agar support ke device lain dari base ROG Phone 2
int xTap = (int) (deviceWidth * xPercent)
int yTap = (int) (deviceHeight * yPercent)

// Tap di posisi yang sudah disesuaikan
Mobile.tapAtPosition(xTap, yTap)



//CheckCurrentActivitySesuaiAtauTidak
AndroidDriver driver = (AndroidDriver) MobileDriverFactory.getDriver()

	// Ambil current activity
	String currentActivity = driver.currentActivity()
	
	// Log output current activity
	KeywordUtil.logInfo("📌 Current Activity: " + currentActivity)

	// Validasi apakah currentActivity sesuai
	if (currentActivity.contains("com.eraspace.home.presentation.HomeActivity")) {
		Mobile.tap(findTestObject('Object Repository/ButtonMasukInHomePage'), 0)
	} else {
	}


}