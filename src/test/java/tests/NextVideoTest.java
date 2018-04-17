package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

public class NextVideoTest extends TestBase {
    @Test
    public void nextVideoTest() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot59", "QA18testbot"));
        FriendPage friendPage = new UserMainPage(driver).clickFriendsOnToolbar().chooseFriend();
        friendPage.selectVideo();
        VideoPlayerPageBase videoPlayer = new VideoPlayerPageFuncDisabled(driver);
        videoPlayer.clickNextVideo();
        videoPlayer.checkNextVideo();
    }
}
