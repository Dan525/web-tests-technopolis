package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LikeFactory {

    public static final By LIKE_LABEL = By.xpath(".//div[@class='html5-vpl_ac_i_t' and text()='Вы']");

    public LikeInterface get(WebDriver driver) {
        if (isButtonPressed(driver)) {
            return new VideoPlayerPageFuncEnabled(driver);
        } else {
            return new VideoPlayerPageFuncDisabled(driver);
        }
    }

    private boolean isButtonPressed(WebDriver driver) {
        if (driver.findElement(LIKE_LABEL).isDisplayed()) return true;
        return false;
    }
}
