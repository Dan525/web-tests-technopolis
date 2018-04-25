package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WatchLaterRemovingTest extends TestBase {

    @Before
    public void preCondition() {
        log("Проверка на наличие тестового видео в отложенных");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = userMainPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.videoList());
        if (!isVideoPresent) {
            log("Видео отсутствует в отложенных - необходимо добавить");
            FriendsMainPage friendsMainPage = videoPage.clickFriendsOnToolbar();
            FriendPage friendPage = friendsMainPage.chooseFriend();
            FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
            FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
            VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        } else log("Видео изначально присутствовало в отложенных - подготовка к тесту не требуется");
        videoPage.clickUserMenu();
        videoPage.clickExitButton();
        videoPage.confirmExit();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
    }

    @Test
    public void watchLaterRemovingTest() throws Exception {
        System.out.println();
        log("Запущен тест");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new UserMainPage(driver);
        VideoPage videoPage = userMainPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.videoList());
        Assert.assertTrue("Видео с таким именем отсутствует", isVideoPresent);
        videoPage.clickOnVideoByName(watchLaterVideoName, videoPage.videoList());
        VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
        videoPlayerPage.clickWatchLater();
        videoPlayerPage.closeVideo();
        driver.navigate().refresh();
        VideoPage videoPageAfterClose = new VideoPage(driver);
        videoPageAfterClose.clickMyVideo();
        videoPageAfterClose.clickWatchLaterSection();
        Boolean isVideoPresentAfterRemoving = videoPageAfterClose.checkVideoByName(watchLaterVideoName, videoPageAfterClose.videoList());
        Assert.assertFalse("Видео с таким именем присутствует", isVideoPresentAfterRemoving);
    }
}
