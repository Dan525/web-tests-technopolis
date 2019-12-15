package tests;


import model.TestBot;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class NextVideoTest extends TestBase {
    private static final Logger LOG = LoggerFactory.getLogger(NextVideoTest.class);

    @Test
    public void nextVideoTest() {
        UserPage userPage = new LoginPage().login(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist("Тест");
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo("1");
        String nextVideoName = videoPlayerPage.getNextVideoName();
        LOG.info("Expected next video name: {}", nextVideoName);
        videoPlayerPage.clickNextVideo();
        String videoName = videoPlayerPage.getVideoName();
        LOG.info("Next video name: {}", videoName);
        Assert.assertEquals("Next video name is differ from expected", nextVideoName, videoName);
    }
}
