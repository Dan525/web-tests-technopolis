package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.wrappers.FriendToolbarWrapper;
import utils.Transformer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static selenide.wrappers.FriendToolbarWrapper.FRIEND_NAV_ITEM;

public class FriendPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendPage.class);

    private static final By TEST_FRIEND_TOP_BLOCK = By.xpath(".//div[@class='main-content-header_data_nav-menu']");

    @Override
    protected void check() {
        $(TEST_FRIEND_TOP_BLOCK).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendVideoPage selectVideoSection() {
        final SelenideElement section = selectSection("Видео");
        if (section != null) {
            section.click();
            LOG.info("Clicked on video section");
            return new FriendVideoPage();
        }
        return null;
    }

    private SelenideElement selectSection(String sectionName) {
        final List<FriendToolbarWrapper> sections = Transformer.wrap($$(FRIEND_NAV_ITEM), FriendToolbarWrapper::new);
        Assert.assertFalse("No navigation elements", sections.isEmpty());
        for (FriendToolbarWrapper section : sections) {
            if (section.getNavItemName().equals(sectionName)) {
                return section.getMainElement().shouldBe(Condition.visible);
            }
        }
        LOG.error("Can't find section with name: {}", sectionName);
        Assert.fail();
        return null;
    }
}
