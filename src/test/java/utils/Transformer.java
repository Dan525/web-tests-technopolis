package utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import selenide.wrappers.Wrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transformer {

    public static <T extends Wrapper> List<T> wrap(ElementsCollection elements, ElementWrapper<T> wrapper) {
        if (elements.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> wrappers = new ArrayList<>();
        for (SelenideElement elem : elements) {
            wrappers.add(wrapper.wrap(elem));
        }
        return wrappers;
    }
}
