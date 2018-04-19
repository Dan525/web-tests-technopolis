package tests;


import core.VideoPlayerPage;
import org.junit.Assert;
import org.junit.Test;

public class NextVideoTest extends TestBase {

    @Test
    public void nextVideoTest() throws Exception {
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        String nextVideoName = videoPlayerPage.getNextVideoName();
        videoPlayerPage.clickNextVideo();
        String actualVideoName = videoPlayerPage.getVideoName();
        Assert.assertEquals("Названия видео не совпадают", nextVideoName, actualVideoName);
    }
}
