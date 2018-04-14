package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends HelperBase{

    public static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    public static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    public static final By GROUPS = By.xpath(".//*[contains(@data-l,'userAltGroup')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public void clickGroupsOnToolbar() {
        click(GROUPS);
    }

    public VideoPage clickVideoOnToolbar() {
        click(VIDEO);
        return new VideoPage(driver);
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        click(FRIENDS);
        return new FriendsMainPage(driver);
    }
}