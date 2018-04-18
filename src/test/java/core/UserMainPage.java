package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends Toolbar{

    public static final By FEEDBACK = By.xpath(".//span[contains(@class, 'feedback')]");
    public static final By FEEDBACK_USER_NAME = By.xpath(".//div[contains(@class,'notif_tx')]//a[contains(text(),'bot59')][0]");
    public static final By FEEDBACK_VIDEO_NAME = By.xpath(".//div[contains(@class,'notif_tx')]//a[contains(@href,'video')][0]");
    public static final By FEEDBACK_LIKE_VIDEO = By.xpath(".//div[contains(@class,'notif_tx')]");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        click(FRIENDS);
        return new FriendsMainPage(driver);
    }


    /*public void clickGroupsOnToolbar() {
        click(GROUPS);
    }

    */

        public String checkFeedback() {
        click(FEEDBACK);
        return driver.findElements(FEEDBACK_LIKE_VIDEO).get(0).getText();
    }
}