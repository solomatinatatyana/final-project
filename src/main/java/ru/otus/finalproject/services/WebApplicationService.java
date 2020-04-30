package ru.otus.finalproject.services;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.MutableCapabilities;
import ru.otus.finalproject.config.ui.BrowserType;

public interface WebApplicationService {
    SelfHealingDriver initDriver(BrowserType browser, MutableCapabilities options);
}
