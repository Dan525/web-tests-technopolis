package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPlayerPage extends PageBase{

    public static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    private static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    private static final By PLAYER_LIKE = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]");
    private static final By PLAYER_LIKE_TEXT = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]//div[@class='html5-vpl_ac_i_t']");
    private static final By VIDEO_NAME = By.xpath(".//div[contains(@class,'portlet_h__nb textWrap')]");
    private static final By CLOSE_VIDEO = By.xpath(".//div[@id='vpl_close']/div");
    private static final By PLAYER = By.xpath(".//video[contains(@class,'display')]");
    public static final By LIKE_COUNT = By.xpath(".//span[@data-module='LikeComponent']/span[contains(@class,'count')]");
    private static final By NEXT_VIDEO_NAME = By.xpath(".//div[contains(@class,'vpl_panel-tip_v')]");
    private static final By NEXT_VIDEO_BUTTON = By.xpath(".//div[contains(@class,'vpl_panel_btn') and contains(@al-click,'NextButton')]");
    public static final By TIMER = By.xpath(".//div[@class='html5-vpl_time_t']");

    public VideoPlayerPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementVisible(PLAYER);
            }
        });
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                moveMouseTo(PLAYER);
                if (!isElementVisible(TIMER)) return false;
                final String timerText = driver.findElement(TIMER).getText();
                return !timerText.contains("0:00") && !timerText.isEmpty();
            }
        });
        log("Началось воспроизведение видео");
    }

    public void clickWatchLater() {
        moveMouseTo(PLAYER);
        Assert.assertTrue("Отсутствует кнопка \"Добавить в отложенные\"", isElementVisible(PLAYER_WATCHLATER));
        click(PLAYER_WATCHLATER);
        log("Нажатие на кнопку \"Добавить в отложенные\" в плеере");
    }

    public void clickLike() {
        moveMouseTo(PLAYER);
        Assert.assertTrue("Отсутствует кнопка \"Класс\"", isElementVisible(PLAYER_LIKE));
        click(PLAYER_LIKE);
        log("Нажатие на кнопку \"Класс\" в плеере");
    }

    public void clickNextVideo() {
        moveMouseTo(PLAYER);
        Assert.assertTrue("Отсутствует кнопка \"Следующее видео\"", isElementVisible(NEXT_VIDEO_BUTTON));
        click(NEXT_VIDEO_BUTTON);
        log("Нажатие на кнопку \"Следующее видео\" в плеере");
    }

    public String getNextVideoName() {
        moveMouseTo(PLAYER);
        new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (!isElementVisible(NEXT_VIDEO_BUTTON)) return false;
                moveMouseTo(NEXT_VIDEO_BUTTON);
                return isElementVisible(NEXT_VIDEO_NAME) && !driver.findElement(NEXT_VIDEO_NAME).getText().isEmpty();
            }
        });
        return driver.findElement(NEXT_VIDEO_NAME).getText();
    }

    public String getVideoName() {
        Assert.assertTrue("Отсутствует название видео", isElementPresent(VIDEO_NAME));
        return driver.findElement(VIDEO_NAME).getText();
    }

    public int getLikeCount() {
        Assert.assertTrue("Отсутствует название видео", isElementPresent(LIKE_COUNT));
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void closeVideo() {
        Assert.assertTrue("Отсутствует кнопка \"Закрыть видео\"", isElementPresent(CLOSE_VIDEO));
        click(CLOSE_VIDEO);
        log("Нажатие на кнопку \"Закрыть видео\"");
    }

    public boolean isLikeAdded() {
        new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                moveMouseTo(PLAYER);
                return isElementVisible(PLAYER_LIKE);
            }
        });
        return !driver.findElement(PLAYER_LIKE_TEXT).getText().contains("Класс!");
    }
}
