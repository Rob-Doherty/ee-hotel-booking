import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxDriver

waiting {
    timeout = 5
}

environments {

//    Crossbrowser work in progress to be extended in future:
//
//    // run via “./gradlew chromeTest”
//    chrome {
//        driver = { new ChromeDriver() }
//    }
//
//    // run via “./gradlew chromeHeadlessTest”
//    chromeHeadless {
//        driver = {
//            ChromeOptions o = new ChromeOptions()
//            o.addArguments('headless')
//            new ChromeDriver(o)
//        }
//    }

    // run via “./gradlew firefoxTest”
    firefox {
        atCheckWaiting = 5

        driver = { new FirefoxDriver() }
    }

}

// To run the tests with all browsers just run “./gradlew test”

baseUrl = "http://hotel-test.equalexperts.io"
