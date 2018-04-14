package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

public class LikeTest extends TestBase {

    @Test
    public void likeTest() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        FriendPage friendPage = new UserMainPage(driver).clickFriendsOnToolbar().chooseFriend();
        LikeInterface videoPlayerPage = friendPage.selectVideo().get(driver);
        videoPlayerPage.clickLike();
        videoPlayerPage.checkLike();
    }
}
