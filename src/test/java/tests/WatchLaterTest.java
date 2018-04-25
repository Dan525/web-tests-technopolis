package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WatchLaterTest extends TestBase {

    @Before
    public void preCondition() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        VideoPage videoPage = userMainPage.clickVideoOnToolbar();
        videoPage.clickMyVideo();
        System.out.println("Зашел в мое видео: " + dateFormat.format(new Date()));
        videoPage.clickWatchLaterSection();
        System.out.println("Зашел в отложенное: " + dateFormat.format(new Date()));
        String watchLaterVideoName = "1";
        System.out.println("Зашел в чек по имени: " + dateFormat.format(new Date()));
        Boolean isVideoPresent = videoPage.checkVideoByName(watchLaterVideoName, videoPage.videoList());
        System.out.println("Вышел из чека: " + dateFormat.format(new Date()));
        if (isVideoPresent) {
            videoPage.clickOnVideoByName(watchLaterVideoName, videoPage.videoList());
            VideoPlayerPage videoPlayerPage = new VideoPlayerPage(driver);
            videoPlayerPage.clickWatchLater();
            videoPlayerPage.closeVideo();
        }
        System.out.println("Вышел из ифа: " + dateFormat.format(new Date()));
        videoPage.clickUserMenu();
        System.out.println("Нажал на меню: " + dateFormat.format(new Date()));
        videoPage.clickExitButton();
        videoPage.confirmExit();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
    }

    @Test
    public void watchLaterTest() throws Exception {
        UserMainPage userMainPage = new UserMainPage(driver);
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        videoPlayerPage.clickWatchLater();
        String videoName = videoPlayerPage.getVideoName();
        videoPlayerPage.closeVideo();
        VideoPage videoPage = new FriendVideoPage(driver).clickVideoOnToolbar();
        videoPage.clickMyVideo();
        videoPage.clickWatchLaterSection();
        String watchLaterVideoName = videoPage.videoList().get(0).getVideoName();
        Assert.assertEquals("Видео отсутствует в отложенных", videoName, watchLaterVideoName);
    }
}
