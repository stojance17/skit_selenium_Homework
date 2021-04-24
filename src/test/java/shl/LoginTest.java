package shl;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver webDriver;

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Stojance/seleniumHomework/src/main/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    @BeforeTest
    public void setup(){
        this.webDriver = getDriver();
    }

    @Test //GoodLogin
    public void loginSuccessfulTest() throws InterruptedException {
        webDriver.get("https://twitter.com/login");
        Thread.sleep(5000);
        WebElement username = webDriver.findElement(By.name("session[username_or_email]"));
        WebElement password = webDriver.findElement(By.name("session[password]"));
        WebElement login = webDriver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']"));
        username.sendKeys("JohnDaw09747296");
        password.sendKeys("qwerty123+");
        login.click();
        String expectedUrl= "https://twitter.com/home";
        String actualUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

    }

    @Test //badCredentials (cant do empty credentials login button is disabled )
    public void badCredentialsTest() throws InterruptedException {
        webDriver.get("https://twitter.com/login");
        Thread.sleep(5000);
        WebElement username = webDriver.findElement(By.name("session[username_or_email]"));
        WebElement password = webDriver.findElement(By.name("session[password]"));
        WebElement login = webDriver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']"));
        username.sendKeys("JohnDaw0974726");
        password.sendKeys("asdasd");
        login.click();
        Thread.sleep(5000);
        WebElement error = webDriver.findElement(By.xpath("//div[@class='css-901oao r-18jsvk2 r-1qd0xha r-a023e6 r-16dba41 r-rjixqe r-bcqeeo r-qvutc0']"));
        String errorActual = error.getText();
        String errorExpected = "The username and password that you entered did not match our records. Please double-check and try again.";
        Assert.assertEquals(errorExpected,errorActual);

    }

    @Test //forgot password
    public void forgotPasswordTest() throws InterruptedException {
        webDriver.get("https://twitter.com/login");
        Thread.sleep(5000);
        WebElement linkTag = webDriver.findElement(By.xpath("//a[@class='css-4rbku5 css-18t94o4 css-901oao css-16my406 r-1n1174f r-1loqt21 r-poiln3 r-bcqeeo r-qvutc0']"));
        linkTag.click();
        String expectedUrl= "https://twitter.com/account/begin_password_reset";
        String actualUrl = webDriver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

    }
    @Test
    public void emptyCredentialsTest() throws InterruptedException {
        webDriver.get("https://twitter.com/login");
        Thread.sleep(5000);
        WebElement username = webDriver.findElement(By.name("session[username_or_email]"));
        WebElement password = webDriver.findElement(By.name("session[password]"));
        WebElement login = webDriver.findElement(By.xpath("//div[@data-testid='LoginForm_Login_Button']"));
        String expectedAttrValue = login.getAttribute("aria-disabled");
        Assert.assertEquals(expectedAttrValue,"true");

    }
   @AfterClass
    public void closeDriver() {
        webDriver.close();
    }


}
