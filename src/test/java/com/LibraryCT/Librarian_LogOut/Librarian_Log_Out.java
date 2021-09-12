package com.LibraryCT.Librarian_LogOut;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Librarian_Log_Out {
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


            WebElement passwordBox = driver.findElement(By.id("inputPassword"));
            passwordBox.sendKeys("Sdet2022*");
            Thread.sleep(2000);

            //And librarian click sign in button
            WebElement singInButton = driver.findElement(By.xpath("//button[@class=\"btn btn-lg btn-primary btn-block\"]"));
            singInButton.click();
            Thread.sleep(3000);
            //verify land to homepage
            String expectedResult="Library";
            String actualResult=(driver.getTitle());

            if(actualResult.equalsIgnoreCase(expectedResult)){
                System.out.println(eachLibrarianUser+":PASSED-Browser landing homepage successfully");
            }else{
                System.out.println(eachLibrarianUser+":FAILED-Browser not on the homepage");
            }
            Thread.sleep(3000);

            //click user name on the right corner and log out

            driver.findElement(By.id("navbarDropdown")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText("Log Out")).click();

            Thread.sleep(1000);

            String expectedResult1="Login - Library";
            String actualResult1=driver.getTitle();

            if(actualResult1.equalsIgnoreCase(expectedResult1)){
                System.out.println(eachLibrarianUser+":PASSED-Browser log out the library application");
            }else{
                System.out.println(eachLibrarianUser+":FAILED-Library didn't log out library application");
            }

        }

        //close browser
        driver.quit();
        }
    }

