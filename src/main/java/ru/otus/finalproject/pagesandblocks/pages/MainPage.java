package ru.otus.finalproject.pagesandblocks.pages;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import ru.otus.finalproject.enums.NavigationBar;
import ru.otus.finalproject.pagesandblocks.blocks.EventNavigationNavBarBlock;

@Component
public class MainPage extends AbstractPage{
    @Autowired
    public EventNavigationNavBarBlock eventNavigationNavBarBlock;

    public MainPage(SelfHealingDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void open(String url){
        driver.get(url);
        Assert.assertEquals(driver.getCurrentUrl(),url,"Сайт не открылся");
    }

    public void goToNavView(NavigationBar button){
        switch (button){
            case CALENDAR:
                WebElement calendarButton = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.visibilityOf(eventNavigationNavBarBlock.calendarButton));
                calendarButton.click();
                break;
            case EVENTS:
                WebElement eventButton = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.visibilityOf(eventNavigationNavBarBlock.eventsButton));
                eventButton.click();
                break;
            case TALKS_LIBRARY:
                WebElement talksButton = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.visibilityOf(eventNavigationNavBarBlock.talksLibraryButton));
                talksButton.click();
                break;
            case SPEAKERS:
                WebElement speakersButton = (new WebDriverWait(driver, 10))
                        .until(ExpectedConditions.visibilityOf(eventNavigationNavBarBlock.speakersButton));
                speakersButton.click();
                break;
        }
    }

}
