package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendsMainPage extends Toolbar {
    private static final String FRIEND = ".//a[@class='n-t bold' and text()='%s']";

    public FriendPage chooseFriend(String name) {
        final String friendXpath = String.format(FRIEND, name);
        $(By.xpath(friendXpath)).shouldBe(Condition.visible).click();
        return new FriendPage();
    }
}
