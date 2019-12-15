package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {

    private static final String URL = "http://ok.ru/";

    @Before
    public void setUp() {
        init();
    }

    @After
    public void tearDown() {
        stop();
    }

    public void init() {
        Selenide.open(URL);
        final WebDriver driver = WebDriverRunner.getWebDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void stop() {
        Selenide.closeWebDriver();
    }
}