package tests;


import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class NextVideoTest {
    private static final Logger LOG = LoggerFactory.getLogger(NextVideoTest.class);

    @Test
    public void nextVideoTest() {
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        final FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        final FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        final FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        final VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        final String nextVideoName = videoPlayerPage.getNextVideoName();
        LOG.info("Expected next video name: {}", nextVideoName);
        videoPlayerPage.clickNextVideo();
        final String videoName = videoPlayerPage.getVideoName();
        LOG.info("Next video name: {}", videoName);
        Assert.assertEquals("Next video name is differ from expected", nextVideoName, videoName);
    }
}
