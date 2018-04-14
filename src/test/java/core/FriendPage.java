package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FriendPage extends PageBase {

    public static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec' and text()='Видео']");
    public static final By TEST_FRIEND_PLAYLIST = By.xpath(".//a[@class='video-card_n ellip' and text()='Тест']");
    public static final By TEST_FRIEND_VIDEO = By.xpath(".//a[@class='video-card_n ellip' and text()='1']");

    public FriendPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public LikeFactory selectVideo() {
        click(TEST_FRIEND_VIDEOS);
        click(TEST_FRIEND_PLAYLIST);
        click(TEST_FRIEND_VIDEO);
        return new LikeFactory();

    }
}
