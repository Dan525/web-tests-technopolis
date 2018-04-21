package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeTest extends TestBase {

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
    public void likeTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        int likeBefore = videoPlayerPage.getLikeCount();
        final WebElement likeElement = driver.findElement(videoPlayerPage.LIKE_COUNT);
        videoPlayerPage.clickLike();
        videoPlayerPage.waitStalenessOfElement(likeElement);
        int likeAfter = videoPlayerPage.getLikeCount();
        Assert.assertEquals("Количество лайков не совпадает", likeBefore + 1, likeAfter);
        videoPlayerPage.closeVideo();
        FriendVideoPage friendVideoPage = new FriendVideoPage(driver);
        friendVideoPage.clickUserMenu();
        friendVideoPage.clickExitButton();
        LoginMainPage loginMainPage = friendVideoPage.confirmExit();
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("89315960060", "q123451234"));
        userMainPage.clickFeedback();
        String actualLikeFeedbackText = userMainPage.getActualLikeFeedbackText();
        String likeFeedbackText = userMainPage.getLikeFeedbackText();
        Assert.assertEquals("Текст уведомления о лайке не совпадает", likeFeedbackText, actualLikeFeedbackText);
    }
}
