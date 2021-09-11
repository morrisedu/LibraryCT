import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class UserLogin_Positive1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver chrome = new ChromeDriver();

        chrome.get("https://library2.cybertekschool.com/login.html");

        boolean testStatus;
        String failMessage = "";

        // Verify title is "Login - Library"
        String expectedLoginTitle = "Login - Library";
        if (chrome.getTitle().equals(expectedLoginTitle)) {
            testStatus = true;
        } else {
            testStatus = false;
            failMessage += "\n\tExpected login title not " + expectedLoginTitle;
        }

        // Login as librarian
        WebElement emailInput = chrome.findElement(By.cssSelector("#inputEmail"));
        WebElement passwordInput = chrome.findElement(By.cssSelector("#inputPassword"));
        WebElement signInButton = chrome.findElement(By.cssSelector("#login-form > button"));

        emailInput.sendKeys("librarian21@library");
        passwordInput.sendKeys("Sdet2022*");
        signInButton.submit();

        // Verify that there are 3 modules on the page
        Thread.sleep(10000);
        List<WebElement> modules = chrome.findElements(By.className("card"));

        if (modules.size() == 3) {
            testStatus = true;
        } else {
            testStatus = false;
            failMessage += "\n\tExpected modules should be 3. There are " + modules.size();
        }

        System.out.println("testStatus: " + testStatus);
        System.out.println("failMessage: " + failMessage);

        chrome.quit();
    }
}
