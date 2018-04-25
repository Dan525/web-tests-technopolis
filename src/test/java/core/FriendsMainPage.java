package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FriendsMainPage extends Toolbar {

    private static final By TEST_FRIEND = By.xpath(".//a[@class='o' and text()='Денис Борисов']");
    private static final By FRIEND_CARDS_BLOCK = By.xpath(".//ul[@class='ugrid_cnt']");

    public FriendsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(FRIEND_CARDS_BLOCK);
            }
        });
    }

    public FriendPage chooseFriend() {
        Assert.assertTrue("Отсутствует карточка друга в списке друзей", isElementPresent(TEST_FRIEND));
        click(TEST_FRIEND);
        log("Переход на страницу друга");
        return new FriendPage(driver);
    }

}
