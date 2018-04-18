package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FriendPage extends Toolbar {

    public static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec' and text()='Видео']");
    public static final By TEST_FRIEND_PLAYLIST = By.xpath(".//a[@class='video-card_n ellip' and text()='Тест']");
    public static final By TEST_FRIEND_VIDEO = By.xpath(".//a[@class='video-card_n ellip' and text()='1']");
    public static final By TEST_FRIEND_VIDEO_LIST = By.xpath(".//a[@class='video-card_n ellip']");

    public FriendPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendVideoPage selectVideoSection() {
        click(TEST_FRIEND_VIDEOS);
        return new FriendVideoPage(driver);
    }
}
