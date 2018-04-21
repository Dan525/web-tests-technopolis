package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FriendVideoPage extends Toolbar {

    public static final By TEST_FRIEND_PLAYLIST = By.xpath(".//a[@class='video-card_n ellip' and text()='Тест']");
    public static final By TEST_FRIEND_VIDEO = By.xpath(".//a[@class='video-card_n ellip' and text()='1']");

    public FriendVideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendPlaylistPage selectPlaylist() {
        click(TEST_FRIEND_PLAYLIST);
        return new FriendPlaylistPage(driver);
    }
}
