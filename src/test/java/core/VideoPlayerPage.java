package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPlayerPage extends PageBase{

    public static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    public static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    public static final By PLAYER_LIKE = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]");
    public static final By VIDEO_NAME = By.xpath(".//div[contains(@class,'portlet_h__nb textWrap')]");
    public static final By CLOSE_VIDEO = By.xpath(".//div[@id='vpl_close']/child::div");
    public static final By PLAYER = By.xpath(".//video[contains(@class,'display')]");
    public static final By LIKE_COUNT = By.xpath(".//span[@data-module='LikeComponent']/span[contains(@class,'count')]");
    public static final By NEXT_VIDEO_NAME = By.xpath(".//div[contains(@class,'vpl_panel-tip_v')]");
    public static final By NEXT_VIDEO_BUTTON = By.xpath(".//div[contains(@class,'vpl_panel_btn') and contains(@al-click,'NextButton')]");
    public static final By TIMER = By.xpath(".//div[@class='html5-vpl_time_t']");

    public VideoPlayerPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(TIMER);
            }
        });
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
                final String timerText = driver.findElement(TIMER).getText();
                return !(timerText.contains("0:00")) && !(timerText.isEmpty());
            }
        });
    }

    public void clickWatchLater() {
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Добавить в отложенные\"", isElementPresent(PLAYER_WATCHLATER));
        click(PLAYER_WATCHLATER);
    }

    public void clickLike() {
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Класс\"", isElementPresent(PLAYER_LIKE));
        click(PLAYER_LIKE);
    }

    public void clickNextVideo() {
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
        Assert.assertTrue("Отсутствует кнопка \"Следующее видео\"", isElementPresent(NEXT_VIDEO_BUTTON));
        click(NEXT_VIDEO_BUTTON);
    }

    public String getNextVideoName() {
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
        new WebDriverWait(driver, 5).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                 new Actions(driver).moveToElement(driver.findElement(NEXT_VIDEO_BUTTON)).build().perform();
                 return !(driver.findElement(NEXT_VIDEO_NAME).getText().isEmpty());
            }
        });
        return driver.findElement(NEXT_VIDEO_NAME).getText();
    }

    public String getVideoName() {
        return driver.findElement(VIDEO_NAME).getText();
    }

    public int getLikeCount() {
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void closeVideo() {
        Assert.assertTrue("Отсутствует кнопка \"Закрыть видео\"", isElementPresent(CLOSE_VIDEO));
        click(CLOSE_VIDEO);
    }
}
