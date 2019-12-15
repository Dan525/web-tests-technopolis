package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class VideoWrapper {

    private static final By VIDEO_NAME = By.xpath(".//a[@class='video-card_n ellip']");
    private final SelenideElement mainElement;

    public VideoWrapper(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

    public String getVideoName() {
        final SelenideElement videoNameElem = $(mainElement).shouldBe(Condition.visible)
                .$(VIDEO_NAME).shouldBe(Condition.visible);
        return videoNameElem.getText();
    }

    public SelenideElement getMainElement() {
        return mainElement;
    }
}
