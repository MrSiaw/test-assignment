package com.assignment.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public abstract class BasePage {
    protected static String url;

    public void open() {
        Selenide.open(url);
    }

    protected void scrollToElement(SelenideElement element) {
        Selenide.executeJavaScript("arguments[0].scrollIntoView({block: 'center', inline: 'center'});", element);
    }
}
