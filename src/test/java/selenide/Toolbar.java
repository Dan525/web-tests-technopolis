package selenide;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.By;
import selenide.wrappers.FeedbackWrapper;
import utils.Transformer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static selenide.wrappers.FeedbackWrapper.FEEDBACK;

public abstract class Toolbar extends PageBase {
    private static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    private static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    private static final By USER_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    private static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    private static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");
    private static final By FEEDBACK_BUTTON = By.xpath(".//span[contains(@class, 'feedback')]");
    private static final By FEEDBACK_LIST = By.xpath(".//div[@class='js-feedback-list']");

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
        $(FEEDBACK_BUTTON).shouldBe(Condition.visible).click();
        $(FEEDBACK_LIST).shouldBe(Condition.visible);
        final List<FeedbackWrapper> feedbackList = Transformer.wrap($$(FEEDBACK), FeedbackWrapper::new);
        final FeedbackWrapper lastFeedback = feedbackList.get(0);
        Assert.assertNotNull("No feedbacks", lastFeedback);
        return lastFeedback.getFeedbackText();
    }
}
