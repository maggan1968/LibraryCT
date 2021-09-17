package com.LibraryCT.US5_LibrarianSelectUsersGroups;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class LibrarianSelectUserGroup {
    private static String eachLibrarian;

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Given  librarian is on the loginPage
        driver.get("http://library2.cybertekschool.com/login.html");

        ArrayList<String> librarianUsers=new ArrayList<>();
        librarianUsers.add("librarian54@library");
        librarianUsers.add("librarian15@library");

        // System.out.println( librarianUsers.size());
        // System.out.println( librarianUsers);


        for (String eachLibrarianUser : librarianUsers) {
            Thread.sleep(2000);

            //When librarian enters valid email address and password
            WebElement eMailAddressBox= driver.findElement(By.id("inputEmail"));
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
            WebElement signInBtn =driver.findElement(By.cssSelector("button"));

            //      (By.cssSelector("button[type='submit']"));
            //      (By.xpath("//button[@type='submit']"));

            if (signInBtn.isEnabled()) {
                signInBtn.click();
            } else {
                System.out.println("This button is not clickable");
            }
            Thread.sleep(2000);
            //verify that user is on the homepage
            String result = "";
            String expectedTitle = "Library";
            String actualTitle = driver.getTitle();
            if (expectedTitle.equals(actualTitle)) {
                result = eachLibrarian + " is on the homepage";
                System.out.println(result);
            } else {
                result = "User could not log in";
                System.out.println(result);
            }

            WebElement usersModuleBtn = driver.findElement(By.xpath("//li[2]//a "));
            //  (By.cssSelector("#menu_item .nav-item:nth-of-type(2) .nav-link"));
            //   (By.xpath("//i[@class='fa fa-user']"));
            usersModuleBtn.click();
            Thread.sleep(1500);

            WebElement userGroupBox = driver.findElement(By.xpath("//div[@class='form-group']//select"));
            userGroupBox.click();

            Select userGroupObj = new Select(userGroupBox);
            userGroupObj.selectByValue("null");
            Thread.sleep(1500);
            userGroupBox.click();
            userGroupObj.selectByValue("2");
            Thread.sleep(1500);
            userGroupBox.click();
            userGroupObj.selectByValue("3");
            Thread.sleep(1500);

            //check there are three options for user group
            List<WebElement> userGroupObjects = driver.findElements(By.xpath("//select[@id='user_groups']//option"));
            String result2 = "";
            if(userGroupObjects.size()==3){
                result2 = "Test PASSED : "+eachLibrarian+" has 3 options for User Group";
                System.out.println(result2);
            }else{
                result2 = "Test FAILED :"+eachLibrarian+ " has "+userGroupObjects.size()+" options for User Group";
                System.out.println(result2);
            }

            //log out
            WebElement beforeLogOutBtn = driver.findElement(By.cssSelector("a[class='nav-link dropdown-toggle']"));
            //    (By.cssSelector("a[class='nav-link dropdown-toggle']"));
            //    (By.cssSelector(".nav-link.dropdown-toggle"));
            beforeLogOutBtn.click();
            Thread.sleep(1500);
            WebElement logOutBtn = driver.findElement(By.cssSelector("a[class='dropdown-item']"));
            //   (By.xpath("//div/a[@class='dropdown-item']"));
            logOutBtn.click();
            System.out.println("-------------------------------------------");
        }
        driver.quit();

    }

    }
