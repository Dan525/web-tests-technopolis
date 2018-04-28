package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class VideoPage extends Toolbar {

    private static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    private static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    private static final By MY_VIDEO = By.xpath(".//a[@id='vv_btn_myVideo']");
    private static final By VIDEO_PREVIEW = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]");
    private static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementVisible(SECTIONS_BLOCK) && isElementVisible(SEARCH_VIDEO);
            }
        });
    }

    public void clickMyVideo() {
        Assert.assertTrue("Отсутствует кнопка \"Моё видео\"", isElementVisible(MY_VIDEO));
        click(MY_VIDEO);
        log("Переход на страницу \"Моё видео\"");
    }

    public void clickWatchLaterSection() {
        Assert.assertTrue("Отсутствует кнопка \"Отложенное видео\"", isElementVisible(WATCHLATER_VIDEO));
        click(WATCHLATER_VIDEO);
        log("Переход на страницу \"Отложенное видео\"");
    }

    public Boolean checkVideoByName(String videoName, List<VideoWrapper> videoList) {
        log("Поиск в списке видео с названием \"" + videoName + "\"");
        if (videoList.isEmpty()) {
            log("Список пуст");
            return false;
        }
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) {
                log("Видео с названием \"" + videoName + "\" найдено");
                return true;
            }
        }
        log("Видео с названием \"" + videoName + "\" отсутствует");
        return false;
    }

    public void clickOnVideoByName(String videoName, List<VideoWrapper> videoList) {
        Assert.assertFalse("Список видео не должен быть пустым", videoList.isEmpty());
        for (VideoWrapper video:videoList) {
            if (video.getVideoName().equals(videoName)) {
                video.getMainElement().click();
                log("Переход к видео с названием \"" + videoName + "\"");
                break;
            }
        }
    }

    public List<VideoWrapper> videoList() {
        log("Создание списка обернутых видео элементов");
        if (isElementVisible(VIDEO_PREVIEW)) {
            List<WebElement> videos = driver.findElements(VIDEO_PREVIEW);
            return VideoTransformer.wrap(videos, driver);
        }
        log("Видео на странице не найдены - формирование пустого списка");
        return Collections.emptyList();
    }
}
