package core;

import org.openqa.selenium.WebDriver;

public class VideoPlayerPageFuncEnabled extends VideoPlayerPage {

    public VideoPlayerPageFuncEnabled(WebDriver driver) {
        super(driver);
    }
/*
    @Override
    public void checkWatchLater() {
        String videoName = driver.findElement(VIDEO_NAME).getText();
        click(CLOSE_VIDEO);
        click(VideoPage.MY_VIDEO);
        click(WATCHLATER_VIDEO);
        String videoNameWatchLater = videoList(VideoPage.VIDEO_PREVIEW).get(0).getVideoName();
        Assert.assertNotEquals("Видео присутствует в отложенных", videoName, videoNameWatchLater);
    }

    @Override
    public void checkLike() {
        likeAfter = Integer.parseInt(driver.findElement(LIKE_COUNT).getText());
        Assert.assertEquals(likeBefore,likeAfter + 1);
        /*if (likeBefore == likeAfter + 1) {
            System.out.println("Лайк уже был поставлен, теперь его нет! Тест пройден!");
            System.out.println("Число лайков до нажатия: " + likeBefore);
            System.out.println("Число лайков после нажатия: " + likeAfter);
        } else {
            System.out.println("Тест не пройден!");
            System.out.println("Число лайков до нажатия: " + likeBefore);
            System.out.println("Число лайков после нажатия: " + likeAfter);
        }
    }*/
}
