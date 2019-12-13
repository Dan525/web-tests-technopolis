package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.*;

public class LikeRemovingTest {
    private static final Logger LOG = LoggerFactory.getLogger(LikeRemovingTest.class);

    @Before
    public void preCondition() {
        LOG.info("Check like on video");
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        final FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        final FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        final FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        final VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        final boolean isLikeAdded = videoPlayerPage.isLikeAdded();
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
        final UserPage userPage = new LoginPage().login("QA18testbot58", "QA18testbot");
        final FriendsMainPage friendsMainPage = userPage.clickFriendsOnToolbar();
        final FriendPage friendPage = friendsMainPage.chooseFriend("Денис Борисов");
        final FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        final FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        final VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        final int likeBefore = videoPlayerPage.getLikeCount();
        LOG.info("Likes count before: {}", likeBefore);
        videoPlayerPage.clickLike();
        final int likeAfter = videoPlayerPage.getLikeCount();
        LOG.info("Likes count after: {}", likeAfter);
        Assert.assertEquals("Like wasn't removed", likeBefore - 1, likeAfter);
    }
}
