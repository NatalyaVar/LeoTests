import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DashboardTest extends LoginTest {
    private static Logger log = (Logger) LoggerFactory.getLogger(DashboardTest.class);

    WebDriverWait wait = new WebDriverWait(driver, 20);



    //Пример использования PO - DashboardPage
    @Test
    public void NotificationTest() throws InterruptedException, IOException {

        DashboardPage dashboardPage = new DashboardPage();
        PageFactory.initElements(driver, dashboardPage);

        WebElement content = dashboardPage.content;
        WebElement close_icon = dashboardPage.close_icon;
        WebElement panel = dashboardPage.panel;
        //Ждем, пока вверху страницы появится поле с заметками
        wait.until(ExpectedConditions.visibilityOf(content));

        //Пишем в файл текст заметки и закрываем ее
        dashboardPage.Notification(driver, content, close_icon);

        //Убеждаемся, что поле notification стало невидимым
        Boolean invisible = wait.until(ExpectedConditions.invisibilityOf(panel));
        log.info("Element is invisible____" + invisible);
    }


    //При нажатии на логотипе и на кнопке "Задания" нет перехода на другую страницу
    @Test
    public void HeaderLogoTest() throws InterruptedException {

        System.out.println("HeaderLogo___" + driver.getTitle());
        driver.findElement(By.className("b-header__logo")).click();

        //Проверяем кнопку "Задания"
        WebElement tasks = driver.findElement(By.className("main-link-dashboard"));
        System.out.println("Tasks____" + tasks.getText());

        //Проверяем текст на кнопке
        Assert.assertEquals("Tasks", tasks.getText());
        tasks.click();
    }

    @Test
    public void TrainingTest() throws InterruptedException {
        System.out.println("Training___" + driver.getTitle());
        WebElement training = driver.findElement(By.className("main-link-training"));
        Assert.assertEquals("Trainings", training.getText());
        System.out.println(driver.getCurrentUrl());
        training.click();

        //Перешли на страницу https://lingualeo.com/ru/training. Ждем, пока станет видима кнопка "Все мои слова"
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select-simple__btn-text")));
        System.out.println("Training___" + driver.getTitle());
        //Возвращаемся на страницу DashBoard
        driver.navigate().back();

        //Проверяем кнопку "Грамматика"
        WebElement grammar = driver.findElement(By.className("main-link-grammar"));
        Assert.assertEquals("Grammar", grammar.getText());
        grammar.click();
        System.out.println("Grammar__" + driver.getTitle());
        System.out.println(driver.getCurrentUrl());

        //По кнопке "Lingualeo" должны возвращаться на DashBoard
        WebElement leo = driver.findElement(By.className("b-header__logo"));
        leo.click();

        //Проверяем кнопку "Курсы"
        WebElement course = driver.findElement(By.className("main-link-course"));

        Assert.assertEquals("Courses", course.getText());
        course.click();
        System.out.println("Сourse__" + driver.getTitle());

        //По кнопке "Lingualeo" должны возвращаться на DashBoard
        WebElement leo1 = driver.findElement(By.className("b-header__logo"));

        leo1.click();
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }

}




