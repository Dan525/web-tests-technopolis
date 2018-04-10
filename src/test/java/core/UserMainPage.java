package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserMainPage extends HelperBase{

    public static final By VIDEO = By.xpath(".//li[@id='hook_Block_TopMenuVideo']");
    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        //todo
    }

    public void clickGroupsOnToolbar() {
        click(By.xpath(".//*[contains(@data-l,'userAltGroup')]"));
    }

    public void clickVideoOnToolbar() { click(VIDEO);}
}