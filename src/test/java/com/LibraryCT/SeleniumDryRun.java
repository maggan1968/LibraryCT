package com.LibraryCT;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumDryRun {

    public static void main(String[] args) {

        //1.Setup Chrome Driver  using webdriver manager
        // so selenium can use it to automate chrome
        WebDriverManager.chromedriver().setup();


        //2. Create  webdriver instance using Chrome Driver Object to open browser
        WebDriver driver = new ChromeDriver();


        //3. Navigate to https://google.com
        driver.navigate().to("https://google.com"); //or
        driver.get("https://google.com");

    }


}






