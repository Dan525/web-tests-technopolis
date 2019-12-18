package selenide.wrappers;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class VideoWrapper extends Wrapper {
    private static final By VIDEO_NAME = By.xpath(".//a[@class='video-card_n ellip']");

    public VideoWrapper(SelenideElement mainElement) {
        super(mainElement);
    }

    public String getVideoName() {
        final SelenideElement videoNameElem = $(mainElement).shouldBe(Condition.visible)
                .$(VIDEO_NAME).shouldBe(Condition.visible);
        return videoNameElem.getText();
    }
}
