package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VideoPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(VideoPage.class);

    private static final By SECTIONS_BLOCK = By.xpath(".//div[@class='nav-side ']");
    private static final By SEARCH_VIDEO = By.xpath(".//div[@class='toolbar_search']");
    private static final By MY_VIDEO = By.xpath(".//a[@href='/video/myVideo']");
    private static final By VIDEO_PREVIEW = By.xpath(".//div[@class='video-card_img-w']");
    private static final By WATCHLATER_VIDEO = By.xpath(".//a[@href='/video/watchLater']");
    private static final By VIDEO = By.xpath(".//div[contains(@class, 'ugrid_i')]");
    private static final By EMPTY_VIDEO_LIST = By.xpath(".//div[@class='stub-empty __video-card ']");

    @Override
    protected void check() {
        $(SECTIONS_BLOCK).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
        $(SEARCH_VIDEO).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public void clickMyVideo() {
        $(MY_VIDEO).shouldBe(Condition.visible).click();
    }

    public void clickWatchLaterSection() {
        $(WATCHLATER_VIDEO).shouldBe(Condition.visible).click();
    }

    public Boolean checkVideoByName(String targetName) {
        for (VideoWrapper videoWrapper : videoList()) {
            if (videoWrapper.getVideoName().equals(targetName)) {
                LOG.info("Founded video: {}", targetName);
                return true;
            }
        }
        LOG.error("Can't find video with name: {}", targetName);
        return false;
    }

    public VideoPlayerPage clickOnVideoByName(String targetName) {
        final List<VideoWrapper> videoList = videoList();
        Assert.assertFalse("Video list is empty", videoList.isEmpty());
        for (VideoWrapper videoWrapper : videoList) {
            if (videoWrapper.getVideoName().equals(targetName)) {
                LOG.info("Founded video: {}", targetName);
                videoWrapper.getMainElement().shouldBe(Condition.visible).click();
                return new VideoPlayerPage();
            }
        }
        LOG.error("Can't find video with name: {}", targetName);
        Assert.fail();
        return null;
    }

    public List<VideoWrapper> videoList() {
        ElementsCollection videoElements = $$(VIDEO);
        SelenideElement emptyList = $(EMPTY_VIDEO_LIST);
        int attempt = 0;
        while (attempt < 4 && videoElements.isEmpty() && !emptyList.isDisplayed()) {
            LOG.info("Retry attempt #{} to receive video list", attempt + 1);
            attempt++;
        }
        return VideoTransformer.wrap($$(VIDEO));
    }
}
