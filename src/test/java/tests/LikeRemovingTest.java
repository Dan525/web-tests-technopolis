package tests;

import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class LikeRemovingTest extends TestBase {
    private static final Logger LOG = LoggerFactory.getLogger(LikeRemovingTest.class);

    @Before
    public void preCondition() {
        LOG.info("Check like on video");
        UserPage userPage = new LoginPage().login(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist("Тест");
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo("1");
        boolean isLikeAdded = videoPlayerPage.isLikeAdded();
        if (!isLikeAdded) {
            videoPlayerPage.clickLike();
        }
        videoPlayerPage.closeVideo();
        friendPlaylistPage.clickUserMenu();
        friendPlaylistPage.exit();
    }

    @Test
    public void likeRemovingTest() {
        LOG.info("Like removing test");
        UserPage userPage = new LoginPage().login(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist("Тест");
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo("1");
        int likeBefore = videoPlayerPage.getLikeCount();
        LOG.info("Likes count before: {}", likeBefore);
        videoPlayerPage.clickLike();
        int likeAfter = videoPlayerPage.getLikeCount();
        LOG.info("Likes count after: {}", likeAfter);
        Assert.assertEquals("Like wasn't removed", likeBefore - 1, likeAfter);
    }
}
