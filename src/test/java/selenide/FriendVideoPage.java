package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendVideoPage extends Toolbar {
    private static final By TEST_FRIEND_PLAYLIST = By.xpath(".//a[@class='video-card_n ellip' and text()='Тест']");

    public FriendPlaylistPage selectPlaylist() {
        $(TEST_FRIEND_PLAYLIST).shouldBe(Condition.visible).click();
        return new FriendPlaylistPage();
    }
}
