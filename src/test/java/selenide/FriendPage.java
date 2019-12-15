package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class FriendPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendPage.class);

    private static final By TEST_FRIEND_TOP_BLOCK = By.xpath(".//div[@class='main-content-header_data_nav-menu']");
    private static final String FRIEND_SECTIONS = ".//a[@class='mctc_navMenuSec ' and text()='%s']";

    @Override
    protected void check() {
        $(TEST_FRIEND_TOP_BLOCK).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendVideoPage selectVideoSection() {
        selectSection("Видео");
        return new FriendVideoPage();
    }

    private void selectSection(String sectionName) {
        final String sectionXpath = String.format(FRIEND_SECTIONS, sectionName);
        $(By.xpath(sectionXpath)).shouldBe(Condition.visible).click();
        LOG.info("Clicked on section: {}", sectionName);
    }
}
