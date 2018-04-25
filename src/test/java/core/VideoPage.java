package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class VideoPage extends Toolbar {

    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By MY_VIDEO = By.xpath(".//a[@id='vv_btn_myVideo']");
    public static final By VIDEO_PREVIEW = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]");
    public static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SECTIONS_BLOCK) && isElementPresent(SEARCH_VIDEO);
            }
        });
    }

    public void clickMyVideo() {
        Assert.assertTrue("Отсутствует кнопка \"Моё видео\"", isElementPresent(MY_VIDEO));
        click(MY_VIDEO);
    }

    public void clickWatchLaterSection() {
        Assert.assertTrue("Отсутствует кнопка \"Отложенное видео\"", isElementPresent(WATCHLATER_VIDEO));
        click(WATCHLATER_VIDEO);
    }

    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public Boolean checkVideoByName(String videoName, List<VideoWrapper> videoList) {
        System.out.println("Зашел в метод: " + dateFormat.format(new Date()));
        if (videoList.isEmpty()) {
            System.out.println("Выполнил условие в методе: " + dateFormat.format(new Date()));
            return false;
        }
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) return true;
        }
        return false;
    }

    public void clickOnVideoByName(String videoName, List<VideoWrapper> videoList) {
        Assert.assertFalse("Список видео не должен быть пустым", videoList.isEmpty());
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) {
                video.getMainElement().click();
                break;
            }
        }
    }

    public List<VideoWrapper> videoList() {
        System.out.println("Зашел в метод videolist: " + dateFormat.format(new Date()));
        if (isElementVisible(VIDEO_PREVIEW)) {
            System.out.println("Прошел проверку на видимость: " + dateFormat.format(new Date()));
            List<WebElement> videos = driver.findElements(VIDEO_PREVIEW);
            return VideoTransformer.wrap(videos, driver);
        }
        System.out.println("Не прошел проверку на видимость: " + dateFormat.format(new Date()));
        return Collections.emptyList();
    }
}
