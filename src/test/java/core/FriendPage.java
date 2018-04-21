package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendPage extends Toolbar {

    public static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec' and text()='Видео']");
    public static final By TEST_FRIEND_TOP_BLOCK = By.xpath(".//div[contains(@id,'MiddleColumnTopCardFriend') and not(contains(@id,'Footer'))]");

    public FriendPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(TEST_FRIEND_TOP_BLOCK);
            }
        });
    }

    public FriendVideoPage selectVideoSection() {
        Assert.assertTrue("Отсутствует кнопка \"Видео\" на странице друга", isElementPresent(TEST_FRIEND_VIDEOS));
        click(TEST_FRIEND_VIDEOS);
        return new FriendVideoPage(driver);
    }
}
