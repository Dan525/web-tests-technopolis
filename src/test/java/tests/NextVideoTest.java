package tests;


import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class NextVideoTest extends TestBase {

    @Test
    public void nextVideoTest() throws Exception {
        System.out.println();
        log("Запущен тест");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        String nextVideoName = videoPlayerPage.getNextVideoName();
        log("Ожидаемое название следующего видео: " + nextVideoName);
        final WebElement timer = driver.findElement(videoPlayerPage.TIMER);
        videoPlayerPage.clickNextVideo();
        videoPlayerPage.waitStalenessOfElement(timer);
        VideoPlayerPage nextVideoPlayerPage = new VideoPlayerPage(driver);
        String actualVideoName = nextVideoPlayerPage.getVideoName();
        log("Название следующего видео: " + actualVideoName);
        Assert.assertEquals("Названия видео не совпадают", nextVideoName, actualVideoName);
    }
}
