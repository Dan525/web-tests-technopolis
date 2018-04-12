package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public class VideoPage extends PageBase{

    public static final By POPULAR_VIDEO = By.xpath(".//a[@id='vv_btn_suggestedAlbums']");
    public static final By SLIDER_VIDEO = By.xpath(".//a[contains(@class,'ucard') and text()='Популярное видео']/ancestor::div[@class='vl_subscriptions']//a[contains(@class, 'vid-card v')][1]");
    public static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    public static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");
    public static final By MY_VIDEO = By.xpath(".//a[@id='vv_btn_myVideo']");
    public static final By VIDEO_NAME = By.xpath(".//div[contains(@class,'portlet_h__nb textWrap')]");
    public static final By CLOSE_VIDEO = By.xpath(".//div[@id='vpl_close']/child::div");
    public static final By PLAYER = By.xpath(".//video[contains(@class,'display')]");
    public static final By VIDEO_PREVIEW = By.xpath(".//div[contains(@class,'vid-card js-frozen js-watched')]");

    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SECTIONS_BLOCK) && isElementPresent(SEARCH_VIDEO);
            }
        });
    }

    public void clickOnSection() {
       click(POPULAR_VIDEO);
    }

    public void clickOnFirstVideo() {
        click(SLIDER_VIDEO);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(PLAYER_PANEL);
            }
        });
    }

    public void clickWatchLater() {
        Actions mouse = new Actions(driver);
        mouse.moveToElement(driver.findElement(PLAYER)).click(driver.findElement(PLAYER_WATCHLATER)).build().perform();
        String videoName = driver.findElement(VIDEO_NAME).getText();
        click(CLOSE_VIDEO);
        click(MY_VIDEO);
        click(WATCHLATER_VIDEO);
        String videoNameWatchLater = videoList(VIDEO_PREVIEW).get(0).getVideoName();
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, videoNameWatchLater);
    }

    public List<VideoWrapper> videoList(By videoPreview) {
        if (driver.findElement(videoPreview).isDisplayed()) {
            List<WebElement> videos = driver.findElements(videoPreview);
            return VideoTransformer.wrap(videos, driver);
        }
        return Collections.emptyList();
    }
}
