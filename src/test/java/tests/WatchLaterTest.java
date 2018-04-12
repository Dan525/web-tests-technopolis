package tests;

import core.SessionPage;
import core.UserMainPage;
import core.VideoPage;
import model.TestBot;
import org.junit.Test;

public class WatchLaterTest extends TestBase {

    @Test
    public void watchLaterTest() throws Exception {
        new SessionPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        new UserMainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.clickOnSection();
        videoPage.clickOnFirstVideo();
        videoPage.clickWatchLater();
    }
}
