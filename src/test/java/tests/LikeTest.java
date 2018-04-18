package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Test;

public class LikeTest extends TestBase {

    @Test
    public void likeTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        int likeBefore = videoPlayerPage.getLikeCount();
        videoPlayerPage.clickLike();
        int likeAfter = videoPlayerPage.getLikeCount();
        Assert.assertEquals("Лайк не поставился", likeBefore + 1, likeAfter);
        videoPlayerPage.closeVideo();
        FriendVideoPage friendVideoPage = new FriendVideoPage(driver);
        friendVideoPage.clickUserMenu();
        friendVideoPage.clickExitButton();
        LoginMainPage loginMainPage = friendVideoPage.confirmExit();
        UserMainPage userMainPage = loginMainPage.doLogin(new TestBot("89315960060", "q123451234"));
        userMainPage.clickFeedback();
        String actualLikeFeedbackText = userMainPage.getLikeFeedbackText();
        String likeFeedbackText = userMainPage.getLikeFeedbackText();
        Assert.assertEquals("Текст не совпадает", likeFeedbackText, actualLikeFeedbackText);
    }
}
