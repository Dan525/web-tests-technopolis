package selenide.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FriendWrapper extends Wrapper {
    public static final By FRIEND = By.xpath(".//li[@class='ugrid_i']");
    private static final By FRIEND_NAME = By.xpath(".//a[@class='n-t bold']");

    public FriendWrapper(SelenideElement mainElement) {
        super(mainElement);
    }

    public String getFriendName() {
        final SelenideElement friendElem = $(mainElement).shouldBe(Condition.visible)
                .$(FRIEND_NAME).shouldBe(Condition.visible);
        return friendElem.getText();
    }
}
