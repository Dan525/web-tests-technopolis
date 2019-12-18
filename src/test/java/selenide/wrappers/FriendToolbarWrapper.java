package selenide.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendToolbarWrapper extends Wrapper {
    public static final By FRIEND_NAV_ITEM = By.xpath(".//a[@class='mctc_navMenuSec ']");

    public FriendToolbarWrapper(SelenideElement mainElement) {
        super(mainElement);
    }

    public String getNavItemName() {
        final SelenideElement navElem = $(mainElement).shouldBe(Condition.visible);
        return navElem.getText();
    }
}
