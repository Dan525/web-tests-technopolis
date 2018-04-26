package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeRemovingTest extends TestBase {

    @Before
    public void preCondition() {
        log("Проверка на наличие Класса на тестовом видео");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        if (!videoPlayerPage.isLikeAdded()) {
            log("Класс на видео отсутствует - необходимо поставить");
            videoPlayerPage.clickLike();
        } else log("Класс на видео изначально присутствовал - подготовка к тесту не требуется");
        videoPlayerPage.closeVideo();
        friendPlaylistPage.clickUserMenu();
        friendPlaylistPage.clickExitButton();
        friendPlaylistPage.confirmExit();
    }

    @Test
    public void likeRemovingTest() throws Exception {
        System.out.println();
        log("Запущен тест");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        final int likeBefore = videoPlayerPage.getLikeCount();
        log("Количество Классов до нажатия: " + likeBefore);
        final WebElement likeElement = driver.findElement(videoPlayerPage.LIKE_COUNT);
        videoPlayerPage.clickLike();
        videoPlayerPage.waitStalenessOfElement(likeElement);
        final int likeAfter = videoPlayerPage.getLikeCount();
        log("Количество Классов после нажатия: " + likeAfter);
        Assert.assertEquals("Количество лайков не совпадает", likeBefore - 1, likeAfter);
    }
}
