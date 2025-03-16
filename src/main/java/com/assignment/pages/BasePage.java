package com.assignment.pages;

import com.codeborne.selenide.Selenide;

public abstract class BasePage {
    protected static String url;

    public void open() {
        Selenide.open(url);
    }

}
