package core;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LikeFactory extends PageBase {

    public static final By LIKE_LABEL = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]//div[text()='Вы и 1']");//(".//div[@class='html5-vpl_ac_i_t' and text()='Класс!']");
    public static final By NO_LIKE_LABEL = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]//div[text()='Класс!']");

    public LikeFactory(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        try {
            (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return isElementPresent(LIKE_LABEL);
                }
            });
        } catch (TimeoutException e) {
            (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return isElementPresent(NO_LIKE_LABEL);
                }
            });
            //Assert.assertTrue("Элемента нет", isElementPresent(LIKE_LABEL));
            //Assert.assertEquals("Вы и 1", driver.findElement(LIKE_LABEL).getText());
        }
        /*try {
            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return driver.findElement(LIKE_LABEL).getText() == "Вы и 1";
                }
            });
        //} catch (TimeoutException e);*/


    }

    public LikeInterface get() {
        if (isButtonPressed()) {
            return new VideoPlayerPageFuncDisabled(driver);
        }
        else {
            return new VideoPlayerPageFuncEnabled(driver);
        }
    }

    private boolean isButtonPressed() {
        if (isElementPresent(LIKE_LABEL)) return false;
        return true;
    }
}
