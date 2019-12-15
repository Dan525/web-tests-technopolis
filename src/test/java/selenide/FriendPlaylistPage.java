package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class FriendPlaylistPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendPlaylistPage.class);

    private static final By CHANNEL_PANEL = By.xpath(".//div[@class='channel-panel h-mod']");
    private static final String FRIEND_VIDEO = ".//a[@class='video-card_n ellip' and text()='%s']";

    @Override
    protected void check() {
        $(CHANNEL_PANEL).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public VideoPlayerPage selectVideo(String videoName) {
        final String videoXpath = String.format(FRIEND_VIDEO, videoName);
        $(By.xpath(videoXpath)).shouldBe(Condition.visible).click();
        LOG.info("Clicked on video: {}", videoName);
        return new VideoPlayerPage();
    }
}
