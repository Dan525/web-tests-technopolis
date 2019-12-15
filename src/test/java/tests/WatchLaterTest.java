package tests;

import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class WatchLaterTest extends TestBase {
    private static final Logger LOG = LoggerFactory.getLogger(WatchLaterTest.class);

    @Before
    public void preCondition() {
        UserPage userPage = new LoginPage().login(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = userPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName);
        if (isVideoPresent) {
            VideoPlayerPage videoPlayerPage = videoPage.clickOnVideoByName(watchLaterVideoName);
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        }
        videoPage.clickUserMenu();
        videoPage.exit();
    }

    @Test
    public void watchLaterTest() {
        UserPage userPage = new LoginPage().login(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist("Тест");
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo("1");
        videoPlayerPage.clickWatchLater();
        String videoName = videoPlayerPage.getVideoName();
        LOG.info("Watch later video name: {}", videoName);
        videoPlayerPage.closeVideo();
        VideoPage videoPage = new FriendVideoPage().clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = videoPage.videoList().get(0).getVideoName();
        Assert.assertEquals("Watch later video name is differ from expected", videoName, watchLaterVideoName);
    }
}
