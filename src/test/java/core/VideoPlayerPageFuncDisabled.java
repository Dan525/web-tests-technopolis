package core;

import model.TestBot;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class VideoPlayerPageFuncDisabled extends VideoPlayerPageBase implements WatchLaterInterface, LikeInterface {

    public static final String likeFeedbackText = "QA18testbot59 QA18testbot59 считает классным видео «1» ";

    public VideoPlayerPageFuncDisabled(WebDriver driver) {
        super(driver);
    }

    @Override
    public void checkWatchLater() {
        String videoName = driver.findElement(VIDEO_NAME).getText();
        click(CLOSE_VIDEO);
        click(VideoPage.MY_VIDEO);
        click(WATCHLATER_VIDEO);
        String videoNameWatchLater = videoList(VideoPage.VIDEO_PREVIEW).get(0).getVideoName();
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, videoNameWatchLater);
    }

    @Override
    public void checkLike() {
        likeAfter = Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
        click(CLOSE_VIDEO);
        click(EXIT_MENU);
        click(EXIT_BUTTON);
        click(CONFIRM_EXIT);
        UserMainPage checkFeedback = new LoginMainPage(driver).doLogin(new TestBot("89315960060", "q123451234"));
        String actualLikeFeedbackText = checkFeedback.checkFeedback();
        if (actualLikeFeedbackText.equals(likeFeedbackText) && likeBefore == likeAfter - 1) {
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
    }
}
