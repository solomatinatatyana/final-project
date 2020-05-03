package ru.otus.finalproject.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.otus.finalproject.FinalProjectApplication;
import ru.otus.finalproject.config.BaseWebDrivingTest;
import ru.otus.finalproject.config.Config;
import ru.otus.finalproject.enums.Events;
import ru.otus.finalproject.enums.NavigationBar;
import ru.otus.finalproject.pagesandblocks.pages.EventsPage;
import ru.otus.finalproject.pagesandblocks.pages.MainPage;

@SpringBootTest(classes = FinalProjectApplication.class)
@ContextConfiguration(classes = Config.class)
@Test(groups = "smoke")
public class UpcomingEventsTest extends BaseWebDrivingTest {

    @Autowired
    private MainPage mainPage;
    @Autowired
    private EventsPage eventsPage;

    @BeforeClass(alwaysRun = true)
    public void init(){
        mainPage.open(config.getUrl());
    }

    @Test(description = "Перейти на вкладку Events. Проверить, что переход выполнен")
    public void goToEventsView(){
        mainPage.goToNavView(NavigationBar.EVENTS);
        Assert.assertEquals(driver.getCurrentUrl(),"https://events.epam.com/events");
    }

    @Test(description = "Перейти на Upcoming Events. " +
            "Проверить, что отображаются карточки предстоящих мероприятий. " +
            "Количество карточек равно счетчику на кнопке Upcoming Events",dependsOnMethods = "goToEventsView")
    public void checkUpcomingEvents(){
        eventsPage.goToEventsView(Events.UPCOMING_EVENTS);
        /*Проверить, что отображаются карточки предстоящих мероприятий*/
        int currentUpcomingEventsCount= eventsPage.getEventsCount();
        softAssert.assertTrue(currentUpcomingEventsCount!=0,"Предстоящих мероприятий нет!");
        //Проверить, что количество карточек соотвествует счетчику
        int eventsExpectedCount = Integer.parseInt(eventsPage.eventsTabsNavBlock.upcomingEventsCounter.getText());
        softAssert.assertEquals(currentUpcomingEventsCount,eventsExpectedCount,
                "Количество отображаемых карточек НЕ совпадает с количеством указанном на счетчике событий. Ошибка!");
        softAssert.assertAll();
    }
}
