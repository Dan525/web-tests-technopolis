package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.VideoPlayerPageFuncEnabled.PLAYER_PANEL;

public class VideoPage extends PageBase{

    public static final By POPULAR_VIDEO = By.xpath(".//a[@id='vv_btn_suggestedAlbums']");
    public static final By SLIDER_VIDEO = By.xpath(".//a[contains(@class,'ucard') and text()='Популярное видео']/ancestor::div[@class='vl_subscriptions']//a[contains(@class, 'vid-card v')][1]");
    public static final By SECTIONS_BLOCK = By.xpath(".//ul[@class='mml_cat_ul']");
    public static final By SEARCH_VIDEO = By.xpath(".//div[@class='it_w search-input' and @id='vv-search']");
    public static final By MY_VIDEO = By.xpath(".//a[@id='vv_btn_myVideo']");
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

    public WatchLaterFactory clickOnFirstVideo(By videoSection) {
        click(videoSection);
        click(SLIDER_VIDEO);
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return isElementPresent(PLAYER_PANEL);
            }
        });
        return new WatchLaterFactory();
    }

}
