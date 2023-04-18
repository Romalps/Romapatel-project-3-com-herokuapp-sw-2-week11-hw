package testsuite;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/*
2. Create the package ‘testsuite’ and create the
following class inside the ‘testsuite’ package.
1. LoginTest
3. Write down the following test into ‘LoginTest’ class
1. userShouldLoginSuccessfullyWithValidCredentials
* Enter “tomsmith” username
* Enter “SuperSecretPassword!” password
* Click on ‘LOGIN’ button
* Verify the text “Secure Area”
2. verifyTheUsernameErrorMessage
* Enter “tomsmith1” username
* Enter “SuperSecretPassword!” password
* Click on ‘LOGIN’ button
* Verify the error message “Your username
is invalid!”
3. verifyThePasswordErrorMessage
* Enter “tomsmith” username
* Enter “SuperSecretPassword” password
* Click on ‘LOGIN’ button
* Verify the error message “Your password
is invalid!”
 */
public class LoginTest extends BaseTest {

    String baseurl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseurl);
    }

    @Test
    public void verifyUserShouldLoginSuccessfullyWithValidCredentials() {

        // Find username field element and send username element
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        // Find password field element and send password element
        driver.findElement(By.name("password")).sendKeys("SuperSecretPassword!");
        // Find Login button field element and click
        driver.findElement(By.xpath("//button[@class='radius']")).click();

        String expectedMessage = "You logged into a secure area!\n×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Secure Area text displayed", expectedMessage, actualMessage);

    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        // Find username field element and send username element
        driver.findElement(By.name("username")).sendKeys("tomsmith1");
        // Find password field element and send password element
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        // Find Login button element and clock
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String expectedMessage = "Your username is invalid!\n×";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='flash error']"));
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals("Your username is valid error message displayed", expectedMessage, actualMessage);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        //Find username field element and send username element
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        // Find Password field element and send password element
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("SuperSecretPassword");
        // Find Login button field and click
        driver.findElement(By.xpath("//button[@class='radius']")).click();
        String expectedMessage = "Your password is invalid!\n×";

        WebElement actualTextElement = driver.findElement(By.xpath("//div[@id='flash']"));
        String actualMessage = actualTextElement.getText();

        Assert.assertEquals("Your password is invalid message displayed", expectedMessage, actualMessage);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
