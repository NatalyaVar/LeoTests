import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardTest extends LoginTest {

    WebDriverWait wait = new WebDriverWait(driver, 10);



    @Test
    public void NotificTest() throws InterruptedException {

        //Ждем, пока вверху страницы появится поле с заметками
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable
                (By.className("notification-content")));

        System.out.println("Page title is: dashboard_2___" + driver.getTitle());
        //Выводим на консоль содержимое заметки
        System.out.println(driver.findElement(By.className("notification-content")).getText());

        //Закрываем поле notification
        driver.findElement(By.className("close-icon")).click();

        //Убеждаемся, что поле notification стало невидимым
        Boolean invisible;
        invisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className
                ("notification-panel mobile-panel")));
        System.out.println("Element is invisible____" + invisible);
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




