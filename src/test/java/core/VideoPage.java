package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Collections;
import java.util.List;

public class VideoPage extends Toolbar {

    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By MY_VIDEO = By.xpath(".//a[@id='vv_btn_myVideo']");
    public static final By VIDEO_PREVIEW = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]");
    public static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SECTIONS_BLOCK) && isElementPresent(SEARCH_VIDEO);
            }
        });
    }

    public void clickMyVideo() {
        click(MY_VIDEO);
    }

    public void clickWatchLaterSection() {
        click(WATCHLATER_VIDEO);
    }

    public Boolean checkVideoByName(String videoName, List<VideoWrapper> videoList) {
        if (videoList.isEmpty()) return false;
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) return true;
        }
        return false;
    }

    public void clickOnVideoByName(String videoName, List<VideoWrapper> videoList) {
        Assert.assertFalse("Список видео не должен быть пустым", videoList.isEmpty());
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) {
                click(video.getNameLocator());
            }
        }
    }

    public List<VideoWrapper> videoList() {
        if (isElementVisible(VIDEO_PREVIEW)) {
            List<WebElement> videos = driver.findElements(VIDEO_PREVIEW);
            return VideoTransformer.wrap(videos, driver);
        }
        return Collections.emptyList();
    }
}
