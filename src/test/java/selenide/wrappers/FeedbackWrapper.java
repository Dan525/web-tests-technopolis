package selenide.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FeedbackWrapper extends Wrapper {
    public static final By FEEDBACK = By.xpath(".//div[contains(@class,'notif_tx')]");

    public FeedbackWrapper(SelenideElement mainElement) {
        super(mainElement);
    }

    public String getFeedbackText() {
        final SelenideElement friendElem = $(mainElement).shouldBe(Condition.visible);
        return friendElem.getText();
    }
}
