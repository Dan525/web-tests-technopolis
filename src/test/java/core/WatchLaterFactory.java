package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WatchLaterFactory {

    public static final By WATCHLATER_LABEL = By.xpath(".//div[contains(@class,'vpl_watchlater')]//*");

    public WatchLaterInterface get(WebDriver driver) {
        if (isButtonPressed(driver)) {
            return new VideoPlayerPageFuncEnabled(driver);
        } else {
            return new VideoPlayerPageFuncDisabled(driver);
        }
    }

    private boolean isButtonPressed(WebDriver driver) {
        int watchLaterButton = driver.findElements(WATCHLATER_LABEL).size();
        if (watchLaterButton == 2) return true;
        return false;
    }
}
