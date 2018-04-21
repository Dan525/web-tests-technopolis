package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FriendPage extends Toolbar {

    public static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec' and text()='Видео']");

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
