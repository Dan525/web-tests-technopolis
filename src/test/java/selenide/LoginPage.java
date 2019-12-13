package selenide;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private static final String URL = "http://ok.ru/";
    private static final By EMAIL = By.name("st.email");
    private static final By PASSWORD = By.name("st.password");
    private static final By SIGN_IN_1 = By.xpath(".//input[@data-l='t,sign_in']");
    private static final By SIGN_IN_2 = By.xpath(".//span[contains(text(), 'Войти в Одноклассники')]");

    public UserPage login(final String login, final String pw) {
        open(URL);
        $(EMAIL).val(login);
        $(PASSWORD).val(pw);
        final ElementsCollection loginButton = $$(SIGN_IN_1);
        if (!loginButton.isEmpty()) {
            loginButton.get(0).pressEnter();
        } else {
            $(SIGN_IN_2).click();
        }
        return new UserPage();
    }
}
