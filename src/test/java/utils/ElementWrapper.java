package utils;

import com.codeborne.selenide.SelenideElement;
import selenide.wrappers.Wrapper;

@FunctionalInterface
public interface ElementWrapper<T extends Wrapper> {
    T wrap(SelenideElement elem);
}
