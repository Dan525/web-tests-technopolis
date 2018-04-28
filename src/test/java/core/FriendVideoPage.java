package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendVideoPage extends Toolbar {

    private static final By TEST_FRIEND_PLAYLIST = By.xpath(".//a[@class='video-card_n ellip' and text()='Тест']");
    private static final By CONTENT_BLOCKS = By.xpath(".//div[@class='ugrid_cnt']");

    public FriendVideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementVisible(CONTENT_BLOCKS);
            }
        });
    }

    public FriendPlaylistPage selectPlaylist() {
        Assert.assertTrue("Отсутствует необходимый плейлист", isElementVisible(TEST_FRIEND_PLAYLIST));
        click(TEST_FRIEND_PLAYLIST);
        log("Переход в плейлист друга");
        return new FriendPlaylistPage(driver);
    }
}
