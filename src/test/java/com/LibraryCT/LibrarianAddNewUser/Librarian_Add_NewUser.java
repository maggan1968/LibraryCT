package com.LibraryCT.LibrarianAddNewUser;




import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

public class Librarian_Add_NewUser {
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

        driver.manage().window().maximize();
        Thread.sleep(1000);


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

            // verify user count
            WebElement currentUser=driver.findElement(By.xpath("//*[@id=\"user_count\"]"));
            int currentUser1=Integer.valueOf(currentUser.getText());
            System.out.println("currentUser1 = " + currentUser1);

            //click user module
            driver.findElement(By.xpath("//*[@id=\"menu_item\"]/li[2]/a/span[1]")).click();
            Thread.sleep(3000);

            //click add user
            driver.findElement(By.xpath("//*[@id=\"users\"]/div[1]/div[1]/span/a")).click();
            Thread.sleep(3000);

            //full name box
            if(eachLibrarianUser=="librarian54@library") {
                driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[1]/div/div[1]/div/div/input")).sendKeys("Hello World");
            }else{
                driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[1]/div/div[1]/div/div/input")).sendKeys("Hello Java");
            }
            Thread.sleep(1000);

            //password box

            driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[1]/div/div[2]/div[1]/div/input")).sendKeys("123456");
            Thread.sleep(1000);
            //email
            if(eachLibrarianUser=="librarian54@library") {
                driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[1]/div/div[2]/div[2]/div/input")).sendKeys("hello123@cybertekschool.com");
            }else{
                driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[1]/div/div[2]/div[2]/div/input")).sendKeys("hellojava1@cybertekschool.com");
            }
            Thread.sleep(1000);

            //user group
            WebElement userGroup=driver.findElement(By.id("user_group_id"));
            Select group=new Select(userGroup);
            group.selectByValue("3");
            Thread.sleep(2000);

            //status

            WebElement status=driver.findElement(By.id("status"));
            Select statusUpdate=new Select(status);
            statusUpdate.selectByValue("ACTIVE");
            Thread.sleep(2000);

            //click end date box
            driver.findElement(By.xpath("//*[@id=\"end_date\"]/input")).click();
            Thread.sleep(1000);

            //click month
            driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]")).click();
            Thread.sleep(1000);
            //click year arrow button
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/thead/tr/th[3]/i")).click();
            Thread.sleep(1000);
            //click august
            driver.findElement(By.xpath("/html/body/div[2]/div[2]/table/tbody/tr/td/span[8]")).click();
            Thread.sleep(1000);
            //click day
            driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody/tr[2]/td[5]")).click();
            Thread.sleep(1000);

            //address box
            if(eachLibrarianUser=="librarian54@library") {
                driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("1234 cybertek str no:1 tyson,virginia");
            }else{
                driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("4563 Java Pl no:12, tyson,virginia");
            }
            Thread.sleep(1000);
            //save changes button

            driver.findElement(By.xpath("//*[@id=\"add_user_form\"]/div[2]/button[2]")).click();
            Thread.sleep(3000);

            //navigate back to dashboard

            driver.navigate().back();
            Thread.sleep(3000);
            // user count after adding
            // verify user count
            WebElement afterAddingUser=driver.findElement(By.xpath("//*[@id=\"user_count\"]"));
            int afterUser1=Integer.valueOf(afterAddingUser.getText());
            System.out.println("afterUser1 = " + afterUser1);
            Thread.sleep(3000);

            //verify adding user

            if(currentUser1+1==afterUser1){
                System.out.println(eachLibrarianUser + ":user added successfully");
            }else{
                System.out.println(eachLibrarianUser + ": user did not add");
            }

            Thread.sleep(2000);

            driver.findElement(By.id("navbarDropdown")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Log Out")).click();

        }

        //close browser
        driver.quit();
    }
}