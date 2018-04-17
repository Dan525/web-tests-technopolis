package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.VideoPlayerPageBase.PLAYER;

public class WatchLaterFactory extends PageBase {

    public static final By WATCHLATER_LABEL = By.xpath(".//div[contains(@class,'vpl_watchlater')]//*");

    public WatchLaterFactory(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                new Actions(driver).moveToElement(driver.findElement(PLAYER)).pause(10).build().perform();
                return driver.findElement(WATCHLATER_LABEL).isDisplayed();
            }
        });
    }

    public WatchLaterInterface get() {
        if (isButtonPressed()) {
            return new VideoPlayerPageFuncEnabled(driver);
        } else {
            return new VideoPlayerPageFuncDisabled(driver);
        }
    }

    private boolean isButtonPressed() {
        int watchLaterButton = driver.findElements(WATCHLATER_LABEL).size();
        if (watchLaterButton == 2) return true;
        return false;
    }
}
