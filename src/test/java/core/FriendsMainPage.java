package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FriendsMainPage extends Toolbar {

    public static final By TEST_FRIEND = By.xpath(".//a[@class='o' and text()='Денис Борисов']");

    public FriendsMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendPage chooseFriend() {
        click(TEST_FRIEND);
        return new FriendPage(driver);
    }

}
