package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class FriendVideoPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendVideoPage.class);

    private static final By CONTENT_BLOCKS = By.xpath(".//div[@class='ugrid_cnt']");
    private static final String FRIEND_PLAYLIST = ".//a[@class='video-card_n ellip' and text()='%s']";

    @Override
    protected void check() {
        $(CONTENT_BLOCKS).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendPlaylistPage selectPlaylist(String playlistName) {
        final String friendPlaylistXpath = String.format(FRIEND_PLAYLIST, playlistName);
        $(By.xpath(friendPlaylistXpath)).shouldBe(Condition.visible).click();
        LOG.info("Clicked on playlist: {}", playlistName);
        return new FriendPlaylistPage();
    }
}
