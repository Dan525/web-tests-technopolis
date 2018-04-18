package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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



    public String nextVideoName;
    public String actualnextVideoName;

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
         //.pause(10).build().perform();
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
        new Actions(driver).moveToElement(driver.findElement(PLAYER)).pause(10).build().perform();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (driver.findElement(NEXT_VIDEO_BUTTON).isDisplayed()) {
                    new Actions(driver).moveToElement(driver.findElement(NEXT_VIDEO_BUTTON)).pause(10).build().perform();
                    //System.out.println("Проверка: " + driver.findElement(NEXT_VIDEO_NAME).getText());
                    return !(driver.findElement(NEXT_VIDEO_NAME).getText().isEmpty());
                }
                return false;
            }
        });
        nextVideoName = driver.findElement(NEXT_VIDEO_NAME).getText();
        click(NEXT_VIDEO_BUTTON);
        System.out.println("Название следующего видео: " + nextVideoName);
    }

    public void checkNextVideo() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.stalenessOf(driver.findElement(PLAYER)));
        } catch (TimeoutException e) {
            System.out.println("Плеер не обновился или обновился ранее");
        }
        actualnextVideoName = driver.findElement(VIDEO_NAME).getText();
        System.out.println("Реальное название следующего видео: " + actualnextVideoName);
        Assert.assertEquals(nextVideoName, actualnextVideoName);
    }

    public String getVideoName() {
        return driver.findElement(VIDEO_NAME).getText();
    }

    public int getLikeCount() {
        return Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
    }

    public void closeVideo() {
        click(CLOSE_VIDEO);
    }
/*
    public void checkLike() {
        likeAfter = Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
        click(CLOSE_VIDEO);

        UserMainPage checkFeedback = new LoginMainPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        String actualLikeFeedbackText = checkFeedback.checkFeedback();
        Assert.assertEquals(likeFeedbackText, actualLikeFeedbackText);
        Assert.assertEquals(likeBefore,likeAfter - 1);
        /*if (actualLikeFeedbackText.equals(likeFeedbackText) && likeBefore == likeAfter - 1) {
            System.out.println("Лайк поставлен! Тест пройден!");
            System.out.println("Число лайков до нажатия: " + likeBefore);
            System.out.println("Число лайков после нажатия: " + likeAfter);
        } else {
            System.out.println("Тест не пройден!");
            System.out.println("Число лайков до нажатия: " + likeBefore);
            System.out.println("Число лайков после нажатия: " + likeAfter);
            System.out.println("Ожидаемая строка: " + likeFeedbackText);
            System.out.println("Полученная строка: " + actualLikeFeedbackText);
        }
    }*/
}
