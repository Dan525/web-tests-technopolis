package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class NewVideoTransformer {
    public static List<NewVideoWrapper> wrap(List<WebElement> elements, WebDriver driver) {
        List<NewVideoWrapper> buttons = new ArrayList<NewVideoWrapper>();
        for (WebElement button : elements) {
            buttons.add(new NewVideoWrapper(button, driver));
        }
        return buttons;
    }
}