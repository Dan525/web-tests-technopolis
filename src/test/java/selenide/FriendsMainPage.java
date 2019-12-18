package selenide;

import com.codeborne.selenide.Condition;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import selenide.wrappers.FriendWrapper;
import utils.Transformer;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static selenide.wrappers.FriendWrapper.FRIEND;

public class FriendsMainPage extends Toolbar {
    private static final Logger LOG = LoggerFactory.getLogger(FriendsMainPage.class);

    private static final By FRIEND_CARDS_BLOCK = By.xpath(".//ul[@class='ugrid_cnt']");

    @Override
    protected void check() {
        $(FRIEND_CARDS_BLOCK).waitUntil(Condition.visible, PAGE_CHECK_TIMEOUT);
    }

    public FriendPage chooseFriend(String name) {
        final List<FriendWrapper> friends = Transformer.wrap($$(FRIEND), FriendWrapper::new);
        Assert.assertFalse("Friends list is empty", friends.isEmpty());
        for (FriendWrapper friend : friends) {
            if (friend.getFriendName().equals(name)) {
                friend.getMainElement().shouldBe(Condition.visible).click();
                LOG.info("Clicked on friend: {}", name);
                return new FriendPage();
            }
        }
        LOG.error("Can't find friend with name: {}", name);
        Assert.fail();
        return null;
    }
}
