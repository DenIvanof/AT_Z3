package ru.netology.callback.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//java -jar artifacts/app-order.jar
public class CallbackTest {

    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);

    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldNameInvaulibleTest() throws InterruptedException {
        driver.get("http://localhost:9999/");
        //Thread.sleep(500);
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Денис");
        //Thread.sleep(500);
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+71112223344");
        //Thread.sleep(500);
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        //Thread.sleep(500);
        driver.findElement(By.className("button")).click();
        //Thread.sleep(1000);
        String expected = "  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        Assertions.assertEquals(expected, actual);
    }


}



