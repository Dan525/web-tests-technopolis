package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendPlaylistPage extends Toolbar {

    public static final By TEST_FRIEND_VIDEO = By.xpath(".//a[@class='video-card_n ellip' and text()='1']");
    public static final By CHANNEL_PANEL = By.xpath(".//div[@class='channel-panel h-mod']");

    public FriendPlaylistPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(CHANNEL_PANEL);
            }
        });
    }

    public VideoPlayerPage selectVideo() {
        Assert.assertTrue("Отсутствует необходимое видео в плейлисте друга", isElementPresent(TEST_FRIEND_VIDEO));
        click(TEST_FRIEND_VIDEO);
        return new VideoPlayerPage(driver);
    }
}
