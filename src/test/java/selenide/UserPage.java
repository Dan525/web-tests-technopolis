package selenide;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class UserPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(UserPage.class);

    private static final By SIDE_NAV_BAR = By.xpath(".//div[contains(@class,'nav-side __navigation')]");
    private static final By POSTING_FORM = By.xpath(".//div[@id='hook_Block_PostingForm']");

    @Override
    protected void check() {
        $(SIDE_NAV_BAR).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
        $(POSTING_FORM).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }
}
