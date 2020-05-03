package ru.otus.finalproject.pagesandblocks.pages;

import com.epam.healenium.SelfHealingDriver;
import com.epam.healenium.annotation.DisableHealing;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.finalproject.enums.Events;
import ru.otus.finalproject.pagesandblocks.blocks.EventsTabsNavBlock;

import java.util.List;

@Component
public class EventsPage extends AbstractPage {
    @Autowired
    public EventsTabsNavBlock eventsTabsNavBlock;


    private static final String EVENT_CARD_LOADER_LOCATOR = ".evnt-cards-loading";

    public EventsPage(SelfHealingDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @DisableHealing
    @FindBy(css = EVENT_CARD_LOADER_LOCATOR)
    public WebElement eventCardLoader;

    @FindBy(css = ".evnt-event-card")
    public List<WebElement> eventCardList;

    public void goToEventsView(Events eventType){
        switch (eventType){
            case PAST_EVENTS:
                WebElement pastEventButton = (new WebDriverWait(driver, 50))
                        .until(ExpectedConditions.visibilityOf(eventsTabsNavBlock.pastEventsButton));
                pastEventButton.click();
                break;
            case UPCOMING_EVENTS:
                WebElement upcomingEventButton = (new WebDriverWait(driver, 50))
                        .until(ExpectedConditions.visibilityOf(eventsTabsNavBlock.upcomingEventsButton));
                upcomingEventButton.click();
                break;
        }
    }

    private List<WebElement> getAllEventsCard() {
        do{
            scrollPageToTheBottom();
        }while (eventCardLoader.isDisplayed());
        return eventCardList;
    }

    public int getEventsCount(){
        return getAllEventsCard().size();
    }

    public boolean isElementPresent(By by) {
        try {
            WebDriverWait wait = (new WebDriverWait(driver, 10));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void scrollPageToTheBottom(){
        ((JavascriptExecutor) driver).executeScript("" +
                "function f(){" +
                "window.scrollTo(0, document.body.scrollHeight);" +
                "setTimeout(function(){" +
                " if ($(window).scrollTop() != $(document).height()-$(window).height()){" +
                "f();" +
                "}" +
                "}" +
                ",500);" +
                "}" +
                "f()");
    }

    public void scrollBy(int y){ ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,"+ y +")"); }
}
