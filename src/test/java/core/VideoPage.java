package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VideoPage extends PageBase{

    public static final By POPULAR_VIDEO = By.xpath(".//a[@id='vv_btn_suggestedAlbums']");
    public static final By SLIDER_VIDEO = By.xpath(".//a[contains(@class,'ucard') and text()='Популярное видео']/ancestor::div[@class='vl_subscriptions']//a[contains(@class, 'vid-card v')][1]");
    public static final By PLAYER_PANEL = By.xpath(".//div[@class='html5-vpl_panel_cnt']");
    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By PLAYER_WATCHLATER = By.xpath(".//div[contains(@class,'vpl_watchlater')]");
    //(".//a[@id='vv_btn_watchLater']") Отложенные видео
    //(".//a[@id='vv_btn_myVideo']") Мое видео
    //(".//div[@class='vid-card_n']") Имя в отложенных
    //(".//div[contains(@class,'portlet_h__nb')]") Имя видео


    public VideoPage(WebDriver driver) {
        super(driver);
    }

    protected void check() {
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(SECTIONS_BLOCK);
            }
        });
        //Assert.assertTrue("Нет разделов", isElementPresent(SECTIONS_BLOCK));
        Assert.assertTrue("Нет строки поиска", isElementPresent(SEARCH_VIDEO));

    }

    public void clickOnSection() {
       click(POPULAR_VIDEO);
    }

    public void clickOnFirstVideo() {
        click(SLIDER_VIDEO);
        Assert.assertTrue("Видео не запустилось", isElementPresent(PLAYER_PANEL));
        //if (isElementPresent(PLAYER_PANEL)) System.out.println("Видео запустилось!");
    }

    public void clickWatchLater() {
        click(PLAYER_WATCHLATER);
    }

}
