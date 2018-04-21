package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WatchLaterRemovingTest extends TestBase {

    @Before
    public void preCondition() throws Exception {
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = userMainPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
    }

    @Test
    public void watchLaterRemovingTest() throws Exception {
        VideoPage videoPage = new VideoPage(driver);
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.videoList());
        Assert.assertTrue("Видео с таким именем отсутствует", isVideoPresent);
        videoPage.clickOnVideoByName(watchLaterVideoName, videoPage.videoList());
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        videoPlayerPage.clickWatchLater();
        videoPlayerPage.closeVideo();
        driver.navigate().refresh();
        VideoPage videoPageAfterClose = new VideoPage(driver);
        videoPageAfterClose.clickMyVideo();
        videoPageAfterClose.clickWatchLaterSection();
        Boolean isVideoPresentAfterRemoving = videoPageAfterClose.checkVideoByName(watchLaterVideoName, videoPageAfterClose.videoList());
        Assert.assertFalse("Видео с таким именем присутствует", isVideoPresentAfterRemoving);
    }
}
