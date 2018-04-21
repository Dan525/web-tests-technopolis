package tests;


import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class NextVideoTest extends TestBase {

    @Before
    public void preCondition() throws Exception {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        friendPlaylistPage.selectVideo();
    }

    @Test
    public void nextVideoTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        String nextVideoName = videoPlayerPage.getNextVideoName();
        final WebElement timer = driver.findElement(videoPlayerPage.TIMER);
        videoPlayerPage.clickNextVideo();
        videoPlayerPage.waitStalenessOfElement(timer);
        VideoPlayerPage nextVideoPlayerPage = new VideoPlayerPage(driver);
        String actualVideoName = nextVideoPlayerPage.getVideoName();
        Assert.assertEquals("Названия видео не совпадают", nextVideoName, actualVideoName);
    }
}
