package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendsMainPage extends Toolbar {

    public static final By TEST_FRIEND = By.xpath(".//a[@class='o' and text()='Денис Борисов']");

    public FriendsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendPage chooseFriend() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(TEST_FRIEND);
            }
        });
        click(TEST_FRIEND);
        return new FriendPage(driver);
    }

}
