package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendPage extends Toolbar {

    public static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec' and text()='Видео']");

    public FriendPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendVideoPage selectVideoSection() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(TEST_FRIEND_VIDEOS);
            }
        });
        click(TEST_FRIEND_VIDEOS);
        return new FriendVideoPage(driver);
    }
}
