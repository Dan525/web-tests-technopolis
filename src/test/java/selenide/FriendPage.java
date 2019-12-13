package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendPage extends Toolbar {
    private static final By TEST_FRIEND_VIDEOS = By.xpath(".//a[@class='mctc_navMenuSec ' and text()='Видео']");

    public FriendVideoPage selectVideoSection() {
        $(TEST_FRIEND_VIDEOS).shouldBe(Condition.visible).click();
        return new FriendVideoPage();
    }
}
