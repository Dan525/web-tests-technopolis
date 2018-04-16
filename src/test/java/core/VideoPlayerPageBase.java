package core;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collections;
import java.util.List;

public abstract class VideoPlayerPageBase extends PageBase{
    public static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    public static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    public static final By PLAYER_LIKE = By.xpath(".//div[@class='html5-vpl_ac_i' and contains(@al-click,'Like')]");
    public static final By WATCHLATER_VIDEO = By.xpath(".//a[@id='vv_btn_watchLater']");
    public static final By VIDEO_NAME = By.xpath(".//div[contains(@class,'portlet_h__nb textWrap')]");
    public static final By CLOSE_VIDEO = By.xpath(".//div[@id='vpl_close']/child::div");
    public static final By PLAYER = By.xpath(".//video[contains(@class,'display')]");
    public static final By LIKE_COUNT = By.xpath(".//span[@data-module='LikeComponent']/span[contains(@class,'count')]");
    public static final By EXIT_MENU = By.xpath(".//div[@class='toolbar_dropdown_w h-mod']");
    public static final By EXIT_BUTTON = By.xpath(".//a[contains(@data-l, 'logout') and text()='Выйти']");
    public static final By CONFIRM_EXIT = By.xpath(".//input[contains(@data-l, 'confirm') and @value='Выйти']");

    protected int likeBefore;
    protected int likeAfter;

    public VideoPlayerPageBase(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(PLAYER);
            }
        });
    }

    public void clickWatchLater() {
        Actions mouse = new Actions(driver);
        mouse.moveToElement(driver.findElement(PLAYER)).click(driver.findElement(PLAYER_WATCHLATER)).build().perform();
    }

    public void clickLike() {
        likeBefore = Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).click(driver.findElement(PLAYER_LIKE)).build().perform();
        try {
            new WebDriverWait(driver, 2).until(ExpectedConditions.stalenessOf(driver.findElement(LIKE_COUNT)));
        } catch (TimeoutException e) {

        }

    }

    public abstract void checkWatchLater();

    public abstract void checkLike();

    public List<VideoWrapper> videoList(By videoPreview) {
        if (driver.findElement(videoPreview).isDisplayed()) {
            List<WebElement> videos = driver.findElements(videoPreview);
            return VideoTransformer.wrap(videos, driver);
        }
        return Collections.emptyList();
    }
}
