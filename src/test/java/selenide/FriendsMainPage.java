package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class FriendsMainPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendsMainPage.class);

    private static final By FRIEND_CARDS_BLOCK = By.xpath(".//ul[@class='ugrid_cnt']");
    private static final String FRIEND = ".//a[@class='n-t bold' and text()='%s']";

    @Override
    protected void check() {
        $(FRIEND_CARDS_BLOCK).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendPage chooseFriend(String name) {
        final String friendXpath = String.format(FRIEND, name);
        $(By.xpath(friendXpath)).shouldBe(Condition.visible).click();
        LOG.info("Clicked on friend: {}", name);
        return new FriendPage();
    }
}
