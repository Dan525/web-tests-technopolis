package selenide.wrappers;

import com.codeborne.selenide.SelenideElement;

public abstract class Wrapper {
    protected final SelenideElement mainElement;

    protected Wrapper(SelenideElement mainElement) {
        this.mainElement = mainElement;
    }

    public SelenideElement getMainElement() {
        return mainElement;
    }
}
