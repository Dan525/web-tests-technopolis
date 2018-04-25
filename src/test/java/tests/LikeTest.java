package tests;

import core.*;
import model.TestBot;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class LikeTest extends TestBase {

    @Before
    public void preCondition() {
        log("Проверка на отсутствие Класса на тестовом видео");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        if (videoPlayerPage.isLikeAdded()) {
            log("Класс на видео присутствовал - необходимо убрать");
            videoPlayerPage.clickLike();
        } else log("Класс на видео изначально отсутствовал - подготовка к тесту не требуется");
        videoPlayerPage.closeVideo();
        friendPlaylistPage.clickUserMenu();
        friendPlaylistPage.clickExitButton();
        friendPlaylistPage.confirmExit();
        new LoginMainPage(driver).doLogin(new TestBot("QA18testbot58", "QA18testbot"));
    }

    @Test
    public void likeTest() throws Exception {
        System.out.println();
        log("Запущен тест");
        System.out.println("***************************************************");
        UserMainPage userMainPage = new UserMainPage(driver);
        FriendsMainPage friendsMainPage = userMainPage.clickFriendsOnToolbar();
        FriendPage friendPage = friendsMainPage.chooseFriend();
        FriendVideoPage friendVideoPage = friendPage.selectVideoSection();
        FriendPlaylistPage friendPlaylistPage = friendVideoPage.selectPlaylist();
        VideoPlayerPage videoPlayerPage = friendPlaylistPage.selectVideo();
        int likeBefore = videoPlayerPage.getLikeCount();
        log("Количество Классов до нажатия: " + likeBefore);
        final WebElement likeElement = driver.findElement(videoPlayerPage.LIKE_COUNT);
        videoPlayerPage.clickLike();
        videoPlayerPage.waitStalenessOfElement(likeElement);
        int likeAfter = videoPlayerPage.getLikeCount();
        log("Количество Классов после нажатия: " + likeAfter);
        Assert.assertEquals("Количество лайков не совпадает", likeBefore + 1, likeAfter);
        videoPlayerPage.closeVideo();
        friendVideoPage.clickUserMenu();
        friendVideoPage.clickExitButton();
        LoginMainPage loginMainPage = friendVideoPage.confirmExit();
        UserMainPage userMainPage2 = loginMainPage.doLogin(new TestBot("89315960060", "q123451234"));
        userMainPage2.clickFeedback();
        String actualLikeFeedbackText = userMainPage2.getActualLikeFeedbackText();
        log("Текст уведомления о лайке: " + actualLikeFeedbackText);
        Assert.assertTrue("Текст уведомления о лайке не содержит необходимой информации",
        actualLikeFeedbackText.contains("QA18testbot58 QA18testbot58") && actualLikeFeedbackText.contains("1"));
    }
}
