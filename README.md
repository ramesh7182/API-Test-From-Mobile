# API-Test-From-Mobile
Mobile automation framework to test API services from app UI.
<h2>Prerequisite:</h2>
1. Java 1.8 or above<br>
2. Android Studio for Android emulator or Real Android Device connected with ADB enabled<br>
3. Appium Server installed <br>
4. Intellij or any IDE to run test<br>

<h2> How to run test </h2>
1. You need to start emulator from Android studio(or connect real device to system). Make sure that you can find device using "adb devices" in command promt<br>
2. Code is pulled from repo and pom.xml is opened in intellij. Wait for all maven resources to import<br>
3. Run the test using testng.xml<br>
4. You can see result at intellij console<br>
5. If you want you can setup Jenkins as maven build as it is supported to run maven file with testng.xml in Jenkins<br>
6. By default report is just testng default format<br>

<h2>Framework Structure</h2>
<img src ='https://github.com/ramesh7182/API-Test-From-Mobile/blob/master/AutomationArchitecture.png'></img>

<h2>Demo Video</h2>
https://github.com/ramesh7182/API-Test-From-Mobile/blob/master/DemoVideo.mp4

<h2>Enhancement</h2>
1. All device capabilities will be put in application.properties file and read from there for uses. This will allow isolation of any test properties
2. If more tests are added, then any label or text verification constant will be placed in ConstantUtil.java. This will be utility to read any constant from any external file like csv 
3. Currently all the locators are placed inside page.java, but if it is increasing then it should be also externalized
