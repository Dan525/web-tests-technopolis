package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NewVideoWrapper {
    public static final By LEFT_TOOLBAR_BUTTON = By.xpath(".//li[@class='mml_cat_li']");
    public static final By BUTTON_NAME = By.xpath(".//span[@class='mml_cat_n ellip']");
    private WebElement mainElement;
    private WebDriver driver;
    private String sectionName;


    public NewVideoWrapper(WebElement mainElement, WebDriver driver) {
        this.driver = driver;
        this.mainElement = mainElement;
        String name = mainElement.findElement(BUTTON_NAME).getText();
        this.sectionName = name;
    }

    public List<NewVideoWrapper> getButtons() {
        List<WebElement> elements = driver.findElements(LEFT_TOOLBAR_BUTTON);
        System.out.println("Count of elements: " + elements.size());
        return NewVideoTransformer.wrap(elements, driver);
    }

}
