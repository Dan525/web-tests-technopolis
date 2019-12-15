package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public abstract class Toolbar extends PageBase {
    private static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    private static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    private static final By USER_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    private static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    private static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");
    private static final By FEEDBACK = By.xpath(".//span[contains(@class, 'feedback')]");
    private static final By FEEDBACK_LIKE_VIDEO = By.xpath(".//div[contains(@class,'notif_tx')]");

    public VideoPage clickVideoOnToolbar() {
        $(VIDEO).shouldBe(Condition.visible).click();
        return new VideoPage();
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        $(FRIENDS).shouldBe(Condition.visible).click();
        return new FriendsMainPage();
    }

    public void clickUserMenu() {
        $(USER_MENU).shouldBe(Condition.visible).click();
    }

    public LoginPage exit() {
        $(EXIT_BUTTON).shouldBe(Condition.visible).click();
        $(CONFIRM_EXIT).shouldBe(Condition.visible).click();
        return new LoginPage();
    }

    public String getLastLikeFeedbackText() {
        $(FEEDBACK).shouldBe(Condition.visible).click();
        return $$(FEEDBACK_LIKE_VIDEO).get(0).getText();
    }
}
