import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

    /*
    1. Открыть страницу https://www.saucedemo.com/
    2. Ввести в поле username значение standard_user
    3. Оставить поле password пустым (ввести пробел)
    4. Нажать кнопку Login
    5. Проверить, что мы видим сообщение об ошибке с текстом
       Epic sadface: Password is required
     */
    @Test
    public void checkNegativeLoginWithEmptyPassword() {
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Password is required");

        driver.quit();
    }

    /*
    1. Открыть страницу https://www.saucedemo.com/
    2. Ввести в поле username значение standard_user
    3. Ввести в поле password значение secret_sauce
    4. Нажать кнопку Login
    5. Проверить, что мы перешли на страницу Products
     */
    @Test
    public void checkPositiveLogin() {
//        WebDriver driver = new ChromeDriver();
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        Boolean titleIsVisible = driver.findElement(By.cssSelector("[data-test=title]")).isDisplayed();

        Assert.assertTrue(titleIsVisible);

        driver.quit();
    }

    /*
    1. Открыть страницу https://www.saucedemo.com/
    2. Ввести в поле username некорректное значение nostandart_user
    3. Ввести в поле password значение secret_sauce
    4. Нажать кнопку Login
    5. Проверить, что мы видим сообщение об ошибке с текстом
       Epic sadface: Username and password do not match any user in this service
     */
    @Test
    public void checkNegativeLoginWithIncorrectUsername() {
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("nostandart_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");

        driver.quit();
    }

    /*
    1. Открыть страницу https://www.saucedemo.com/
    2. Оставить поле username пустым
    3. Ввести в поле password значение secret_sauce
    4. Нажать кнопку Login
    5. Проверить, что мы видим сообщение об ошибке с текстом
       Epic sadface: Username is required
     */
    @Test
    public void checkNegativeLoginWithEmptyUsername() {
        WebDriver driver = new FirefoxDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String errorMessage = driver.findElement(By.cssSelector("[data-test=error]")).getText();

        Assert.assertEquals(errorMessage, "Epic sadface: Username is required");

        driver.quit();
    }
}