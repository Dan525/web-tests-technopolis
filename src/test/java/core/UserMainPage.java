package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserMainPage extends Toolbar {

    private static final By SIDE_NAV_BAR = By.xpath(".//div[contains(@class,'nav-side __navigation')]");
    private static final By POSTING_FORM = By.xpath(".//div[@id='hook_Block_PostingForm']");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SIDE_NAV_BAR) && isElementPresent(POSTING_FORM);
            }
        });
    }
}