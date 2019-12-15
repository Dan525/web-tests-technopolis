package selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import model.TestBot;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends PageBase {
    private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);

    private static final By LOGIN_PANEL = By.xpath(".//div[contains(@class, 'anonym_login')]");
    private static final By EMAIL = By.name("st.email");
    private static final By PASSWORD = By.name("st.password");
    private static final By SIGN_IN_1 = By.xpath(".//input[@data-l='t,sign_in']");
    private static final By SIGN_IN_2 = By.xpath(".//span[contains(text(), 'Войти в Одноклассники')]");

    @Override
    protected void check() {
        $(LOGIN_PANEL).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public UserPage login(TestBot testBot) {
        $(EMAIL).shouldBe(Condition.visible).val(testBot.getLogin());
        $(PASSWORD).shouldBe(Condition.visible).val(testBot.getPassword());
        final SelenideElement loginButton = $(SIGN_IN_1);
        if (loginButton.isDisplayed()) {
            loginButton.pressEnter();
        } else {
            $(SIGN_IN_2).shouldBe(Condition.visible).click();
        }
        LOG.info("Logged in for user: {}", testBot.getLogin());
        return new UserPage();
    }
}
