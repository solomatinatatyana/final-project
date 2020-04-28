package ru.otus.finalproject.services;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import ru.otus.finalproject.config.ui.BrowserType;

public interface WebApplicationService {
    WebDriver initDriver(BrowserType browser, MutableCapabilities options);
}
