package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeRemovingTest extends TestBase {

    @Before
    public void preCondition() {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        if (!videoPlayerPage.isLikeAdded()) videoPlayerPage.clickLike();
        videoPlayerPage.closeVideo();
        friendPlaylistPage.clickUserMenu();
        friendPlaylistPage.clickExitButton();
        friendPlaylistPage.confirmExit();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
    }

    @Test
    public void likeRemovingTest() throws Exception {
        UserMainPage userMainPage = new UserMainPage(driver);
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        int likeBefore = videoPlayerPage.getLikeCount();
        final WebElement likeElement = driver.findElement(videoPlayerPage.LIKE_COUNT);
        videoPlayerPage.clickLike();
        videoPlayerPage.waitStalenessOfElement(likeElement);
        int likeAfter = videoPlayerPage.getLikeCount();
        Assert.assertEquals("Количество лайков не совпадает", likeBefore - 1, likeAfter);
    }
}
