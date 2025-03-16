package com.assignment.utils;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class JsExecutorUtil {

    public static void scrollToElement(SelenideElement element) {
        Selenide.executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }
}
