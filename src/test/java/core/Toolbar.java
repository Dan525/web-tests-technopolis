package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Toolbar extends PageBase {

    public static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    public static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    public static final By GROUPS = By.xpath(".//*[contains(@data-l,'userAltGroup')]");
    public static final By USER_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    public static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    public static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");
    public static final By FEEDBACK = By.xpath(".//span[contains(@class, 'feedback')]");
    public static final By FEEDBACK_LIKE_VIDEO = By.xpath(".//div[contains(@class,'notif_tx')]");
    public static final String likeFeedbackText = "QA18testbot58 QA18testbot58 считает классным видео «1» ";

    public Toolbar(WebDriver driver) {
        super(driver);
    }

    public VideoPage clickVideoOnToolbar() {
        click(VIDEO);
        return new VideoPage(driver);
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        click(FRIENDS);
        return new FriendsMainPage(driver);
    }

    public void clickGroupsOnToolbar() {
        click(GROUPS);
    }

    public void clickUserMenu() {
        click(USER_MENU);
    }

    public void clickExitButton() {
        click(EXIT_BUTTON);
    }

    public LoginMainPage confirmExit() {
        click(CONFIRM_EXIT);
        return new LoginMainPage(driver);
    }

    public void clickFeedback() {
        click(FEEDBACK);
    }

    public String getActualLikeFeedbackText() {
        return driver.findElements(FEEDBACK_LIKE_VIDEO).get(0).getText();
    }

    public String getLikeFeedbackText() {
        return likeFeedbackText;
    }


}
