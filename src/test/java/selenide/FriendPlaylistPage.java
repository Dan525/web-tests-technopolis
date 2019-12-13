package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendPlaylistPage extends Toolbar {
    private static final By TEST_FRIEND_VIDEO = By.xpath(".//a[@class='video-card_n ellip' and text()='1']");

    public VideoPlayerPage selectVideo() {
        $(TEST_FRIEND_VIDEO).shouldBe(Condition.visible).click();
        return new VideoPlayerPage();
    }
}
