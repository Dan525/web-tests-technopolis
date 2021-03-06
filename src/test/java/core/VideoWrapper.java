package core;

import com.google.common.base.Preconditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VideoWrapper {

    private static final By VIDEO_NAME = By.xpath(".//div[@class='vid-card_n']");
    private WebElement mainElement;
    private WebDriver driver;
    private String videoName;


    public VideoWrapper(WebElement mainElement, WebDriver driver) {
        this.driver = driver;
        this.mainElement = mainElement;
    }

    public String getVideoName() {
        videoName = mainElement.findElement(VIDEO_NAME).getText();
        Preconditions.checkNotNull(videoName, "Название видео не может быть пустым");
        return videoName;
    }

    public WebElement getMainElement() {
        return mainElement;
    }
}
