package selenide;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.wrappers.PlaylistWrapper;
import utils.Transformer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static selenide.wrappers.PlaylistWrapper.PLAYLIST;

public class FriendVideoPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendVideoPage.class);

    private static final By CONTENT_BLOCKS = By.xpath(".//div[@class='ugrid_cnt']");

    @Override
    protected void check() {
        $(CONTENT_BLOCKS).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendPlaylistPage selectPlaylist(String playlistName) {
        final List<PlaylistWrapper> playlists = Transformer.wrap($$(PLAYLIST), PlaylistWrapper::new);
        Assert.assertFalse("Video list is empty", playlists.isEmpty());
        for (PlaylistWrapper playlistWrapper : playlists) {
            if (playlistWrapper.getPlaylistName().equals(playlistName)) {
                playlistWrapper.getMainElement().shouldBe(Condition.visible).click();
                LOG.info("Clicked on playlist: {}", playlistName);
                return new FriendPlaylistPage();
            }
        }
        LOG.error("Can't find playlist with name: {}", playlistName);
        Assert.fail();
        return null;
    }
}
