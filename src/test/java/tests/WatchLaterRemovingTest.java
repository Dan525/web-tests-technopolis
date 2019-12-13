package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class WatchLaterRemovingTest {
    private static final Logger LOG = LoggerFactory.getLogger(WatchLaterRemovingTest.class);

    @Before
    public void preCondition() {
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final selenide.VideoPage videoPage = userPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        final String watchLaterVideoName = "1";
        final Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName);
        if (!isVideoPresent) {
            final FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
            final FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
            final FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
            final FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
            final VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        }
        videoPage.clickUserMenu();
        videoPage.exit();
    }

    @Test
    public void watchLaterRemovingTest() {
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
        final selenide.VideoPage videoPage = new FriendVideoPage().clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        final Boolean isVideoPresent = videoPage.checkVideoByName(videoName);
        Assert.assertFalse("Video " + videoName + "shouldn't be in watch later section", isVideoPresent);
    }
}
