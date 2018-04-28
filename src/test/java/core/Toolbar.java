package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class Toolbar extends PageBase {

    private static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    private static final By FRIENDS = By.xpath(".//li[@data-l='t,friends']");
    public static final By GROUPS = By.xpath(".//*[contains(@data-l,'userAltGroup')]");
    private static final By USER_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    private static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    private static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");
    private static final By FEEDBACK = By.xpath(".//span[contains(@class, 'feedback')]");
    private static final By FEEDBACK_LIKE_VIDEO = By.xpath(".//div[contains(@class,'notif_tx')]");

    public Toolbar(WebDriver driver) {
        super(driver);
    }

    public VideoPage clickVideoOnToolbar() {
        Assert.assertTrue("Отсутствует кнопка \"Видео\" на тулбаре", isElementVisible(VIDEO));
        click(VIDEO);
        log("Переход в раздел \"Видео\"");
        return new VideoPage(driver);
    }

    public FriendsMainPage clickFriendsOnToolbar() {
        Assert.assertTrue("Отсутствует кнопка \"Друзья\" на тулбаре", isElementVisible(FRIENDS));
        click(FRIENDS);
        log("Переход в раздел \"Друзья\"");
        return new FriendsMainPage(driver);
    }

    public void clickUserMenu() {
        Assert.assertTrue("Отсутствует кнопка для открытия меню пользователя", isElementVisible(USER_MENU));
        click(USER_MENU);
        log("Открытие меню пользователя");
    }

    public void clickExitButton() {
        Assert.assertTrue("Отсутствует кнопка \"Выйти\"", isElementVisible(EXIT_BUTTON));
        click(EXIT_BUTTON);
        log("Нажатие на кнопку \"Выйти\"");
    }

    public LoginMainPage confirmExit() {
        Assert.assertTrue("Отсутствует кнопка подтверждения выхода", isElementVisible(CONFIRM_EXIT));
        click(CONFIRM_EXIT);
        log("Нажатие на кнопку подтверждения выхода");
        log("Переход на страницу авторизации");
        return new LoginMainPage(driver);
    }

    public void clickFeedback() {
        Assert.assertTrue("Отсутствует кнопка \"События\" на тулбаре", isElementVisible(FEEDBACK));
        click(FEEDBACK);
        log("Нажатие на кнопку \"События\"");
    }

    public String getActualLikeFeedbackText() {
        return driver.findElements(FEEDBACK_LIKE_VIDEO).get(0).getText();
    }
}
