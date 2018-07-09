# Project 1 - Selenium Challenge
*** Features of the Framework: ***
-----
-  Use of 'Page Object Model' as Design pattern
-  Use of BDD using Cucumber
-  Beautiful Report
-  Screenshots for Verification
-  Logging
-  Reading the Testdata and configuration from config files
-  Ability to choose browsers and URLs by adding to Config files
-  Use of WebDriver Manager so apporpirate browser gets downloaded and no need to set Property for the driver

*** How to Run ***
-----
* Using IDE like Eclipse: 
  * Import the project in IDE
  * Right click `pom.xml` and click `Run As` , Maven >> `Generate sources`. After dependencies are downloaded.
  * Right click again, `pom.xml` and `Run As`, Maven >> `Test`. If it asks to specify the goal, choose as 'test'

* Another option is to go to the root of the folder and run maven from command line using: `mvn compile generate-resources` and then followed by `mvn test`

*** Report to check ***
-----
- This framework generates multiple reports, however the best report to check is: Cucumber HTML Report.
- It gives details like: Passed, Failed, Skipped, Pending, Total, etc.
- It is available in: `target/cucumber-report-html-pretty/cucumber-html-reports` folder and the file to check is: `overview-features.html`


*** Technical Know Hows ***
-----
* Framwork contains pacakges:
Under src/test/java
  * `features`: contains the feature file needed for Cucumber. It has the 3 scenarios in BDD Given-When-Then format.
  * `steps`: steps package gives the step definitions for the steps mentioned in `Scenario.feature` file. There is the most IMP file for the project called `FeatureDefinition`. 
	This class contains all the steps needed for each of the scenario one by one in BDD format. 
  * `runner`: generates file which glues features and steps and generates `cucumber.json` which is consumed to produce reports
-- `utilities`: package contains project specific utilities needed like Test data generation and reading properties files
-- `pages`: package contains page object model file, which has the getter-setter methods needed to create an abstraction in `steps/FeatureDefinition` file.

* Other files:
 * properties files: 
   * `config.properties` to have general properties like Browser and URLs.
   * testdata.properties` contains testdata
 * Screenshots for each scenario can be found at: `test-output/screenshots` folder
 * pom.xml: for Maven dependencies and plugins configuration

*** Dependencies used and usage
-----
* cucumer-core, cucumber-java, cucumber-testng, testng: Needed for cucumber
* selenium-java: for selenium webdriver
* maven-cucumber-reporting: for reporting
* tinylog: for Logging
* webdrivermanager: for setting properties for the browser on the fly and downloaded the appopriate binaries needed to run selenium

-----

Thanks for your time :-)