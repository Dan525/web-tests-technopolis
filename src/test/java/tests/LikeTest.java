package tests;

import core.FriendVideoPage;
import core.VideoPage;
import core.VideoPlayerPage;
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
        VideoPage videoPage = new FriendVideoPage(driver).clickVideoOnToolbar();


        FriendPage friendPage = new UserMainPage(driver).clickFriendsOnToolbar().chooseFriend();
        friendPage.selectVideo();
        LikeInterface videoPlayerPage = new LikeFactory(driver).get();
        videoPlayerPage.clickLike();
        videoPlayerPage.checkLike();
    }
}
