package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VideoPlayerPage extends PageBase {
    private static final Logger LOG = LoggerFactory.getLogger(VideoPlayerPage.class);

    private static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    private static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    private static final By PLAYER_LIKE = By.xpath(".//div[@class='html5-vpl_ac_i'][1]");
    private static final By PLAYER_LIKE_TEXT = By.xpath(".//div[@class='html5-vpl_ac_i'][1]//div[@class='html5-vpl_ac_i_t']");
    private static final By VIDEO_NAME = By.xpath(".//div[@class='vp-layer-info_h textWrap']");
    private static final By CLOSE_VIDEO = By.xpath(".//div[@id='vpl_close']/div");
    private static final By PLAYER = By.xpath(".//video[contains(@class,'display')]");
    private static final By LIKE_COUNT = By.xpath(".//span[contains(@class,'js-klass')]//span[contains(@class, 'widget_count js-count')]");
    private static final By NEXT_VIDEO_NAME = By.xpath(".//div[@class='html5-vpl_next-video_n']");
    private static final By NEXT_VIDEO_NAME_CSS = By.cssSelector(".html5-vpl_next-video_n");
    private static final By NEXT_VIDEO_BUTTON = By.xpath(".//div[contains(@class,'vpl_panel_btn') and contains(@al-click,'NextButton')]");
    private static final By TIMER = By.xpath(".//div[@class='html5-vpl_time_t']");

    @Override
    protected void check() {
        $(PLAYER).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public void clickWatchLater() {
        $(PLAYER).shouldBe(Condition.visible).click();
        $(PLAYER_PANEL).shouldBe(Condition.visible).hover()
                .$(PLAYER_WATCHLATER).shouldBe(Condition.visible).click();
    }

    public void clickLike() {
        $(PLAYER).shouldBe(Condition.visible).click();
        ElementsCollection likeButton = $$(PLAYER_LIKE);
        while (likeButton.isEmpty()) {
            likeButton = $$(PLAYER_LIKE);
        }
        likeButton.get(0).click();
        LOG.info("Clicked like");
    }

    public void clickNextVideo() {
        $(PLAYER).shouldBe(Condition.visible).click();
                $(PLAYER_PANEL).shouldBe(Condition.appear).hover()
                .$(NEXT_VIDEO_BUTTON).shouldBe(Condition.appear).click();
        LOG.info("Clicked next video");
    }

    public String getNextVideoName() {
        $(PLAYER).shouldBe(Condition.visible).click();
        return $(PLAYER_PANEL).shouldBe(Condition.visible).hover()
                .$(NEXT_VIDEO_BUTTON).shouldBe(Condition.exist).hover()
                .$(NEXT_VIDEO_NAME).getText();
    }

    public String getVideoName() {
        final String videoName = $(VIDEO_NAME).shouldBe(Condition.visible).getText();
        LOG.info("Video name: {}", videoName);
        return videoName;
    }

    public int getLikeCount() {
        final int likeCount = Integer.parseInt($(LIKE_COUNT).shouldBe(Condition.visible).getText());
        LOG.info("Like count: {}", likeCount);
        return likeCount;
    }

    public void closeVideo() {
        $(CLOSE_VIDEO).shouldBe(Condition.visible).click();
        LOG.info("Video closed");
    }

    public boolean isLikeAdded() {
        $(PLAYER).shouldBe(Condition.visible).click();
        SelenideElement likeButton = $(PLAYER_LIKE);
        int attempt = 0;
        while (attempt < 4 && likeButton.isDisplayed()) {
            LOG.info("Retry attempt #{} to find like button", attempt + 1);
            attempt++;
        }
        final boolean likeNotAdded = $(PLAYER_LIKE_TEXT).shouldBe(Condition.exist).getText().contains("Класс!");
        return !likeNotAdded;
    }
}
