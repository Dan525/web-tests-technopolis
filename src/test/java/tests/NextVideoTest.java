package tests;


import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NextVideoTest extends TestBase {

    @Before
    public void setUp() throws Exception {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        friendVideoPage.selectPlaylist();
        friendVideoPage.selectVideo();
    }

    @Test
    public void nextVideoTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        String nextVideoName = videoPlayerPage.getNextVideoName();
        videoPlayerPage.clickNextVideo();
        String actualVideoName = videoPlayerPage.getVideoName();
        Assert.assertEquals("Названия видео не совпадают", nextVideoName, actualVideoName);
    }
}
