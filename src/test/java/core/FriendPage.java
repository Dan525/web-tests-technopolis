package core;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendPage extends PageBase {

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

    public void selectSection() {

    }

    public void selectVideo() {
        click(TEST_FRIEND_VIDEOS);
        click(TEST_FRIEND_PLAYLIST);
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.stalenessOf(driver.findElement(TEST_FRIEND_VIDEO)));
        } catch (TimeoutException e) {

        }
        click(TEST_FRIEND_VIDEO);
    }
}
