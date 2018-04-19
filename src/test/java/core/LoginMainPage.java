package core;

import model.TestBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginMainPage extends PageBase{

    public LoginMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public UserMainPage doLogin(TestBot testBot) {
        type(testBot.getLogin(), By.id("field_email"));
        type(testBot.getPassword(), By.id("field_password"));
        click(By.xpath(".//*[contains(@value,'Войти')]"));
        return new UserMainPage(driver);
    }
}