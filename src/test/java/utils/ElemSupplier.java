package utils;

import com.codeborne.selenide.SelenideElement;
import selenide.wrappers.Wrapper;

@FunctionalInterface
public interface ElemSupplier<T extends Wrapper> {
    T get(SelenideElement elem);
}
