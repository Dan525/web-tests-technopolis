package core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoTransformer {

    public static List<VideoWrapper> wrap(List<WebElement> elements, WebDriver driver) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<VideoWrapper> videoNameList = new ArrayList<VideoWrapper>();
        for (WebElement video : elements) {
            videoNameList.add(new VideoWrapper(video, driver));
        }
        return videoNameList;
    }
}