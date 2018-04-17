package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

public class WatchLaterTest extends TestBase {

    @Test
    public void watchLaterTest() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        VideoPage videoPage = new UserMainPage(driver).clickVideoOnToolbar();
        WatchLaterInterface videoPlayerPage = videoPage.clickOnFirstVideo(VideoPage.POPULAR_VIDEO).get();
        videoPlayerPage.clickWatchLater();
        videoPlayerPage.checkWatchLater();
    }
}
