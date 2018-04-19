package core;

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

    public VideoPlayerPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(PLAYER);
            }
        });
    }

    public void clickWatchLater() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                new Actions(driver).moveToElement(driver.findElement(PLAYER)).pause(10).build().perform();
                return driver.findElement(PLAYER_WATCHLATER).isDisplayed();
            }
        });
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).click(driver.findElement(PLAYER_WATCHLATER)).build().perform();
    }

    public void clickLike() {
        final int likeBefore = getLikeCount();
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
                return isElementVisible(PLAYER_LIKE);
            }
        });
        click(PLAYER_LIKE);
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return getLikeCount() == likeBefore + 1;
            }
        });
    }

    public void clickNextVideo() {
        final String previousVideoName = getVideoName();
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).pause(10).build().perform();
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementVisible(NEXT_VIDEO_BUTTON);
            }
        });
        click(NEXT_VIDEO_BUTTON);
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return !(getVideoName().equals(previousVideoName));
            }
        });
    }

    public String getNextVideoName() {
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).build().perform();
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (isElementVisible(NEXT_VIDEO_BUTTON)) {
                    new Actions(driver).moveToElement(driver.findElement(NEXT_VIDEO_BUTTON)).build().perform();
                    return !(driver.findElement(NEXT_VIDEO_NAME).getText().isEmpty());
                }
                return false;
            }
        });
        new Actions(driver).moveToElement(driver.findElement(NEXT_VIDEO_BUTTON)).build().perform();
        return driver.findElement(NEXT_VIDEO_NAME).getText();
    }

    public String getVideoName() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(VIDEO_NAME);
            }
        });
        return driver.findElement(VIDEO_NAME).getText();
    }

    public int getLikeCount() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(LIKE_COUNT);
            }
        });
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void closeVideo() {
        new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(CLOSE_VIDEO);
            }
        });
        click(CLOSE_VIDEO);
    }
}
