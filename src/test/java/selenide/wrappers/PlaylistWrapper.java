package selenide.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class PlaylistWrapper extends Wrapper {
    public static final By PLAYLIST = By.xpath(".//a[@class='video-card_n ellip']");

    public PlaylistWrapper(SelenideElement mainElement) {
        super(mainElement);
    }

    public String getPlaylistName() {
        final SelenideElement playlistNameElem = $(mainElement).shouldBe(Condition.visible);
        return playlistNameElem.getText();
    }
}
