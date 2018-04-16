package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.VideoPlayerPageBase.PLAYER;

public class LikeFactory extends PageBase {

    public static final By LIKE_TEXT_FIELD = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]//div");

    public LikeFactory(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(LIKE_TEXT_FIELD);
            }
        });
    }

    public LikeInterface get() {
        if (isButtonPressed()) {
            return new VideoPlayerPageFuncEnabled(driver);
        }
        else {
            return new VideoPlayerPageFuncDisabled(driver);
        }
    }

    private boolean isButtonPressed() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                new Actions(driver).moveToElement(driver.findElement(PLAYER)).pause(10).build().perform();
                if (driver.findElement(LIKE_TEXT_FIELD).isDisplayed()) {
                    return !(driver.findElement(LIKE_TEXT_FIELD).getText().isEmpty());
                }
                return false;
            }
        });
        System.out.println("Состояние кнопки: " + driver.findElement(LIKE_TEXT_FIELD).getText());
        if (driver.findElement(LIKE_TEXT_FIELD).getText().equals("Класс!")) return false;
        return true;
    }
}
