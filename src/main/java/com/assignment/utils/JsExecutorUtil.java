package com.assignment.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.assignment.utils.WaitUtl.sleep;

public class JsExecutorUtil {

    public static void scrollToElement(SelenideElement element) {
        Selenide.executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
        sleep(1_500);
        element
                .should(Condition.visible);
    }
}
