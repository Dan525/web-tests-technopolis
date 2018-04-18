package tests;

import core.FriendVideoPage;
import core.VideoPage;
import core.VideoPlayerPage;
import org.junit.Assert;
import org.junit.Test;

public class WatchLaterTest extends TestBase {

    @Test
    public void watchLaterTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        videoPlayerPage.clickWatchLater();
        String videoName = videoPlayerPage.getVideoName();
        videoPlayerPage.closeVideo();
        VideoPage videoPage = new FriendVideoPage(driver).clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = videoPage.videoList().get(0).getVideoName();
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, watchLaterVideoName);
    }
}
