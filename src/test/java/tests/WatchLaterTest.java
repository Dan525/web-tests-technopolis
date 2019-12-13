package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class WatchLaterTest {
    private static final Logger LOG = LoggerFactory.getLogger(WatchLaterTest.class);

    @Before
    public void preCondition() {
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final VideoPage videoPage = userPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        final String watchLaterVideoName = "1";
        final Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName);
        if (isVideoPresent) {
            final VideoPlayerPage videoPlayerPage = videoPage.clickOnVideoByName(watchLaterVideoName);
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        }
        videoPage.clickUserMenu();
        videoPage.exit();
    }

    @Test
    public void watchLaterTest() {
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        final FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        final FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        final FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        final VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        videoPlayerPage.clickWatchLater();
        final String videoName = videoPlayerPage.getVideoName();
        LOG.info("Watch later video name: {}", videoName);
        videoPlayerPage.closeVideo();
        final VideoPage videoPage = new FriendVideoPage().clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        final String watchLaterVideoName = videoPage.videoList().get(0).getText();
        Assert.assertEquals("Watch later video name is differ from expected", videoName, watchLaterVideoName);
    }
}
