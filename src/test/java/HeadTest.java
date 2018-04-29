import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeadTest extends LoginTest {

    //Проверяем, что при клике на поля в верхней части страницы (шапка зеленого цвета) происходит переход на нужную страницу (если есть такой переход)
    WebDriverWait wait = new WebDriverWait(driver, 20);

    @Test
    public void Head() throws InterruptedException {

        //Задаем ожидания загрузки страницы
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("a.main-link.main-link-dashboard"))));
        System.out.println("Main Page ____" + driver.getTitle());

        //Выбираем поля в центральной части шапки
        List<WebElement> headLeft = driver.findElements(By.cssSelector("a.main-link.main-link"));
        System.out.println(headLeft.size());

        for (int i = 0; i < headLeft.size(); i++) {
            List<WebElement> headLeft1 = driver.findElements(By.cssSelector("a.main-link.main-link"));
            headLeft1.get(i).click();
            System.out.println("Page" + i + " ____" + driver.getTitle());

            //Возвращаемся на главную страницу
            driver.navigate().to("https://lingualeo.com/ru/dashboard");
        }
    }


    //Проверяем всплывающий список
    @Test
    public void PopUp() throws InterruptedException, IOException {
        //Задаем ожидания загрузки страницы
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        Actions action = new Actions(driver);
        WebElement pop = driver.findElement(By.cssSelector("a.b-info-user__link"));

        //Переместили указатель  на поле с логотипом, чтобы появился всплывающий список
        action.moveToElement(pop);
        action.perform();

        //Получаем все поля всплывающего списка
        List<WebElement> popList = driver.findElements(By.cssSelector("a.b-userpopup__link"));
        System.out.println(popList.size());

        ArrayList<String> popText = new ArrayList<String>();
        // Заполняем список popText текстом элементов из списка popList
        for (int i =0; i <popList.size(); i++){
            popText.add(popList.get(i).getText());
        }

        //Записать этот список в файл
        FileWriter writer = new FileWriter("output.txt", true);
        for(String str: popText) {
            writer.write(str + "\n");
        }
        writer.close();


        //Сделать невидимые поля видимыми
        driver.findElement(By.xpath("//*/a[6]")).click();

        // Отменяем рассылку новостей
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#subscription_news"))));
        WebElement news = driver.findElement(By.cssSelector("input#subscription_news"));

        //Если рассылка новостей выбрана, кликаем, чтобы отменить
        System.out.println("New features ---" + news.isSelected());
        if (news.isSelected()) {
            news.click();
        }
    }

    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
