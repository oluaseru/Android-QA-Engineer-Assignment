# Android-QA-Engineer-Assignment

I've created a few scenarios using an appium cucumber Java setup.
And I've used the Cucumber framework to write user scenarios in BDD format, which sits on top of the logic.

## Project structure
the main sections of the project are 
- Feature file - BBD scenarios 
- Steps definitions files - the feature file steps are bound to logic
- Page Objects (POM) - lower-level actions which are performed on the screen

![image](https://user-images.githubusercontent.com/20359959/161903049-c6005c76-d8ab-4c45-add1-9a00dea0a6d7.png)



## How to execute the tests
1. You'll need to download Appium Server GUI client - https://github.com/appium/appium-desktop/releases/tag/v1.22.2
- If you're on a Mac machine, choose the asset - Appium-Server-GUI-mac-1.22.2.dmg
- for Windows Machine, choose the asset - Appium-Server-GUI-windows-1.22.2.exe

note: Once you have this project on your local machine, you need to launch the appium desktop client in the background as this server is enabling communication with the device under test.

2. Specify the Android device on your local machine to run tests on by adding the **DeviceName** and **platformVersion** to config.properties file

![image](https://user-images.githubusercontent.com/20359959/161900571-a0b86291-d269-4880-8dbc-1be23213e9f8.png)

3. Using Android Studio IDE to execute the test from the **RunCucumberTest.class** by right click 

![image](https://user-images.githubusercontent.com/20359959/161893714-52b17b81-2405-4441-95ea-91deed0122c5.png)

## Test report
The test report is under the **target folder > cucumber html reports**

![image](https://user-images.githubusercontent.com/20359959/161901813-9c1259cd-8962-4b7c-876e-4dd5ba0bc533.png)

