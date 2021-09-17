package com.LibraryCT.US3_LibrarianAddNewBook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class Librarian_Add_NewBook {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        ArrayList<String> librarianUsers = new ArrayList<>();
        librarianUsers.add("librarian54@library");
        librarianUsers.add("librarian15@library");
        // System.out.println(librarianUsers.size());
        // System.out.println(librarianUsers);

        //Given librarian is on the loginPage
        driver.get("http://library2.cybertekschool.com/login.html");

        for (String eachLibrarianUser : librarianUsers) {
            Thread.sleep(1000);
            WebElement userNameBox = driver.findElement(By.id("inputEmail"));
            userNameBox.sendKeys(eachLibrarianUser);
            WebElement passwordBox = driver.findElement(By.id("inputPassword"));
            passwordBox.sendKeys("Sdet2022*");

            Thread.sleep(1000);

            //click sign in button
            driver.findElement(By.xpath("//*[@id=\"login-form\"]/button")).click();
            Thread.sleep(3000);

            //verify book count

            WebElement currentBookCount=driver.findElement(By.xpath("//*[@id=\"book_count\"]"));
            int currentBook=Integer.valueOf(currentBookCount.getText());
            // System.out.println("currentBook = " + currentBook);

            //   click book module

            driver.findElement(By.cssSelector("#menu_item > li:nth-child(3) > a > span.title")).click();
            Thread.sleep(3000);

            //click add book

            driver.findElement(By.xpath("//*[@id=\"books\"]/div[1]/div[1]/span/a")).click();
            Thread.sleep(3000);

            //book name box
            driver.findElement(By.xpath("//*[@id=\"add_book_form\"]/div[1]/div/div[1]/div[1]/div/input")).sendKeys("NUTUK");
            Thread.sleep(1000);

            //ISBN box
            driver.findElement(By.xpath("//*[@id=\"add_book_form\"]/div[1]/div/div[1]/div[2]/div/input")).sendKeys("1234");
            Thread.sleep(1000);
            //year box

            driver.findElement(By.cssSelector("#add_book_form > div.modal-body > div > div:nth-child(1) > div:nth-child(3) > div > input")).sendKeys("1927");
            Thread.sleep(1000);

            //author
            driver.findElement(By.xpath("//*[@id=\"add_book_form\"]/div[1]/div/div[2]/div[1]/div/input")).sendKeys("Mustafa Kemal ATATURK");
            Thread.sleep(1000);
            //book category
            WebElement bookCategory=driver.findElement(By.xpath("//*[@id=\"book_group_id\"]"));
            Select booksSelect=new Select(bookCategory);
            booksSelect.selectByValue("3");
            Thread.sleep(1000);

            //description

            driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("Nutuk was a speech delivered by Mustafa Kemal Ataturk from 15 to 20 October 1927, at the second congress of Rebuplican People's Party.");
            Thread.sleep(1000);
            //save changes button

            driver.findElement(By.xpath("//*[@id=\"add_book_form\"]/div[2]/button[2]")).submit();
            Thread.sleep(1000);

            //navigate dashboard
            driver.navigate().back();
            Thread.sleep(3000);

            // after count book

            WebElement afterBookCount=driver.findElement(By.xpath("//*[@id=\"book_count\"]"));
            int afterBook=Integer.valueOf(afterBookCount.getText());
            // System.out.println("afterBook = " + afterBook);
            Thread.sleep(2000);

            //verify book added
            if(currentBook+1==afterBook){
                System.out.println(eachLibrarianUser + ": Book added successfully");
            }else{
                System.out.println("Book did not add successful");
            }


            driver.findElement(By.id("navbarDropdown")).click();
            Thread.sleep(1000);

            driver.findElement(By.linkText("Log Out")).click();


        }

        //close browser
        driver.quit();

        }
}
