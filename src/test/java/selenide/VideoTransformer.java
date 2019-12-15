package selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoTransformer {

    public static List<VideoWrapper> wrap(ElementsCollection elements) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<VideoWrapper> videoList = new ArrayList<>();
        for (SelenideElement video : elements) {
            videoList.add(new VideoWrapper(video));
        }
        return videoList;
    }
}