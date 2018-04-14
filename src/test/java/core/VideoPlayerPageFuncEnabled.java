package core;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class VideoPlayerPageFuncEnabled extends VideoPlayerPageBase implements WatchLaterInterface{

    public VideoPlayerPageFuncEnabled(WebDriver driver) {
        super(driver);
    }

    public void checkWatchLater() {
        String videoName = driver.findElement(VIDEO_NAME).getText();
        click(CLOSE_VIDEO);
        click(VideoPage.MY_VIDEO);
        click(WATCHLATER_VIDEO);
        String videoNameWatchLater = videoList(VideoPage.VIDEO_PREVIEW).get(0).getVideoName();
        Assert.assertNotEquals("Видео присутствует в отложенных", videoName, videoNameWatchLater);
    }
}
