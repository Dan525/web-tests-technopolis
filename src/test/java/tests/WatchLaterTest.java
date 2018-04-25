package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WatchLaterTest extends TestBase {

    @Before
    public void preCondition() throws Exception {
        log("Проверка на отсутствие тестового видео в отложенных");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = userMainPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = "1";
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.videoList());
        if (isVideoPresent) {
            log("Видео присутствует в отложенных - необходимо удалить");
            videoPage.clickOnVideoByName(watchLaterVideoName, videoPage.videoList());
            VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        } else log("Видео изначально отсутствовало в отложенных - подготовка к тесту не требуется");
        videoPage.clickUserMenu();
        videoPage.clickExitButton();
        videoPage.confirmExit();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
    }

    @Test
    public void watchLaterTest() throws Exception {
        System.out.println();
        log("Запущен тест");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new UserMainPage(driver);
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        videoPlayerPage.clickWatchLater();
        String videoName = videoPlayerPage.getVideoName();
        log("Название отложенного видео: " + videoName);
        videoPlayerPage.closeVideo();
        VideoPage videoPage = new FriendVideoPage(driver).clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = videoPage.videoList().get(0).getVideoName();
        log("Название последнего добавленного видео в списке отложенных видео: " + watchLaterVideoName);
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, watchLaterVideoName);
    }
}
