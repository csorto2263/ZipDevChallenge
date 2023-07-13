package com.example.zipdevchallenge;

import org.testng.annotations.*;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private MainPage mainPage;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.primefaces.org/primereact-v5/#/datatable/selection");

        mainPage = new MainPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void selectBlueBandCheckBox() {

        // Wait for the checkbox to be clickable
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[5]/div[1]/div[2]/div[6]/div/div/table/tbody/tr[3]/td[1]/div/div[2]")));

        //Click the button to select it
        checkbox.click();

        // Get the value of "aria-checked" attribute
        String ariaChecked = checkbox.getAttribute("aria-checked");

        // Assert that "aria-checked" is set to "true"
        assertEquals(ariaChecked, "true", "Checkbox is not selected after clicking it");
    }
}
