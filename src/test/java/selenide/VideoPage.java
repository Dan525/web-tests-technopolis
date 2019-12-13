package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VideoPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(VideoPage.class);

    private static final By MY_VIDEO = By.xpath(".//a[@href='/video/myVideo']");
    private static final By VIDEO_PREVIEW = By.xpath(".//div[@class='video-card_img-w']");
    private static final By WATCHLATER_VIDEO = By.xpath(".//a[@href='/video/watchLater']");
    private static final By VIDEO_NAME = By.xpath(".//a[@class='video-card_n ellip']");
    private static final By EMPTY_VIDEO_LIST = By.xpath(".//div[@class='stub-empty __video-card ']");
    private static final By VIDEO_LIST_GRID = By.xpath(".//div[@class='ugrid __l']");

    public void clickMyVideo() {
        $(MY_VIDEO).shouldBe(Condition.visible).click();
    }

    public void clickWatchLaterSection() {
        $(WATCHLATER_VIDEO).shouldBe(Condition.visible).click();
    }

    public Boolean checkVideoByName(String targetName) {
        final ElementsCollection videoList = videoList();
        for (SelenideElement videoName : videoList) {
            if (videoName.getText().equals(targetName)) {
                LOG.info("Founded video: {}", targetName);
                return true;
            }
        }
        LOG.error("Can't find video with name: {}", targetName);
        return false;
    }

    public VideoPlayerPage clickOnVideoByName(String targetName) {
        final ElementsCollection videoList = videoList();
        Assert.assertFalse("Video list is empty", videoList.isEmpty());
        for (SelenideElement video : videoList) {
            if (video.getText().equals(targetName)) {
                LOG.info("Founded video: {}", targetName);
                video.click();
                return new VideoPlayerPage();
            }
        }
        LOG.error("Can't find video with name: {}", targetName);
        Assert.fail();
        return null;
    }

    public ElementsCollection videoList() {
        ElementsCollection videoPreview = $$(VIDEO_PREVIEW);
        ElementsCollection emptyList = $$(EMPTY_VIDEO_LIST);
        while (videoPreview.isEmpty() && emptyList.isEmpty()) {
            videoPreview = $$(VIDEO_LIST_GRID);
            emptyList = $$(EMPTY_VIDEO_LIST);
        }
        return $$(VIDEO_NAME);
    }
}
