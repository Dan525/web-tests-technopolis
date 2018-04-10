package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupPage extends PageBase {

    public GroupPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {

    }

    public void clickCreateButton() {
        click(By.id("hook_FormButton_button_create"));
    }

    public void typeGroupName(String groupName) {
        type(groupName, By.id("field_name"));
    }

    public void clickInterestGroup() {
        click(By.xpath(".//*[contains(@class,'create-group-dialog_img __interest')]"));
    }

    public void clickCreateGroup() {
        click(By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]"));
    }
}