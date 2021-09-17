package com.LibraryCT.US6_LibrarianSelectUserStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class LibrarianSelectUserStatus {
    public static void main(String[] args) throws InterruptedException {


        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Given  librarian is on the loginPage
        driver.get("http://library2.cybertekschool.com/login.html");

        ArrayList<String> librarianUsers = new ArrayList<>();
        librarianUsers.add("librarian54@library");
        librarianUsers.add("librarian15@library");

        // System.out.println( librarianUsers.size());
        // System.out.println( librarianUsers);


        for (String eachLibrarianUser : librarianUsers) {
            Thread.sleep(2000);

            //When librarian enters valid email address and password
            WebElement eMailAddressBox = driver.findElement(By.id("inputEmail"));
            eMailAddressBox.sendKeys(eachLibrarianUser);

            //find password box and send valid keys
            WebElement passwordBox = driver.findElement(By.id("inputPassword"));
            //      (By.cssSelector("#inputPassword"));
            //      (By.cssSelector(".form-control.valid"));
            //      (By.xpath("//div/input[@id='inputPassword']"));
            //      //div[4]/label[@for='inputPassword']

            passwordBox.sendKeys("Sdet2022*");
            Thread.sleep(2000);

            //find sign in button and click it
            WebElement signInBtn = driver.findElement(By.cssSelector("button"));

            //      (By.cssSelector("button[type='submit']"));
            //      (By.xpath("//button[@type='submit']"));
            signInBtn.click();
            Thread.sleep(1000);

            //verify that user is on the homepage
            String expectedResult="Library";
            String actualResult=(driver.getTitle());

            if(actualResult.equalsIgnoreCase(expectedResult)){
                System.out.println(eachLibrarianUser+":PASSED-Browser landing homepage successfully");
            }else{
                System.out.println(eachLibrarianUser+":FAILED-Browser not on the homepage");
            }
            Thread.sleep(3000);

            //click user module
            driver.findElement(By.xpath("//*[@id=\"menu_item\"]/li[2]/a/span[1]")).click();
            Thread.sleep(3000);

            //click user group

            driver.findElement(By.id("user_groups")).click();
            Thread.sleep(3000);

            //verify 3 options

            // verify first options
            WebElement firstOption=driver.findElement(By.xpath("//select[@id='user_groups']/option[@value='null']"));
            String expectedResult1="ALL";
            String actualResult1= firstOption.getText();

            if(actualResult1.equalsIgnoreCase(expectedResult1)){
                System.out.println(eachLibrarianUser + ":PASSED-first option verified");
            }else{
                System.out.println(eachLibrarianUser + ":FAILED-first option didn't verify");
            }

            Thread.sleep(3000);

            //verify second option

            WebElement secondOption=driver.findElement(By.xpath("//select[@id='user_groups']/option[@value='2']"));
            String expectedResult2="Librarian";
            String actualResult2= secondOption.getText();
            if(actualResult2.equalsIgnoreCase(expectedResult2)){
                System.out.println(eachLibrarianUser + ":PASSED-second option verified");
            }else{
                System.out.println(eachLibrarianUser + ":FAILED-second option didn't verify");
            }

            Thread.sleep(3000);

            //verify third option

            WebElement thirdOption=driver.findElement(By.xpath("//select[@id='user_groups']/option[@value='3']"));
            String expectedResult3="Students";
            String actualResult3=thirdOption.getText();

            if(actualResult3.equalsIgnoreCase(expectedResult3)){
                System.out.println(eachLibrarianUser + ":PASSED-third  option verified");
            }else{
                System.out.println(eachLibrarianUser + ":FAILED-third option didn't verify");
            }

            Thread.sleep(3000);

            //verify number of options
            //And librarian click user group dropdown
            List<WebElement> userDropDown = driver.findElements(By.xpath("//select[@id='user_groups']/option"));
            //System.out.println("userDropDown.size() = " + userDropDown.size());

            int expectedResult4=3;
            int actualResult4=userDropDown.size();

            if(expectedResult4==actualResult4){
                System.out.println("PASSED--"+eachLibrarianUser + ": have 3 options");
            }else{
                System.out.println("FAILED--" + eachLibrarianUser + " have " + actualResult4 + " options");
            }
            //click user name on the right corner and log out

            driver.findElement(By.id("navbarDropdown")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText("Log Out")).click();

            Thread.sleep(1000);



        }

        //close browser
        driver.quit();
        }

    }
    