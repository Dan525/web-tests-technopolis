package tests;

import core.*;
import model.TestBot;
import org.junit.Test;

public class OpenVideoTest extends TestBase{

    @Test
    public void openVideo() throws Exception {
        new LoginMainPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        new UserMainPage(driver).clickVideoOnToolbar();
        VideoPage videoPage = new VideoPage(driver);
        videoPage.clickOnFirstVideo(VideoPage.POPULAR_VIDEO);
    }
}