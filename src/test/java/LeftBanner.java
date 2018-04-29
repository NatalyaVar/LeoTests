import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;

public class LeftBanner extends LoginTest  {

    WebDriverWait wait = new WebDriverWait(driver, 20);

    //Проверяем, что при нажатии на поля левого баннера открывается новая страница, вернуться на главную страницу, закрыть новую страницу

    @Test
    public void NotificationTest() throws InterruptedException {

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".l-left-banner"))));
        String win = driver.getTitle();
        String urlMain = driver.getWindowHandle();

        String selectIphone = new String("a.l-iphone");
        String selectAndroid = new String("a.l-android");
        String selectWindows = new String("a.l-winphone");

        //Открываем новое окно и закрываем
        OpenWindow(urlMain, selectIphone );
        OpenWindow(urlMain,selectAndroid);
        OpenWindow(urlMain,selectWindows);
    }

    private void OpenWindow(String url, String select) {
        driver.findElement(By.cssSelector(select)).click();

        //Ожидаем открытия окна
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //Получаем список открытых окон
        driver.getWindowHandles();
        System.out.println("Открыто окон_______" + driver.getWindowHandles().size());

        Set<String> winOpen = driver.getWindowHandles();
        int numOpen = winOpen.size();

        String winNew = null;
        if (numOpen > 1) {



            //Преобразовываем Set в List
            ArrayList<String> list = new ArrayList<>();
            list.addAll(winOpen);

            //Выбираем строку, не совпадающую с идентификатором текущей страницы
            for (int i = 0; i < winOpen.size(); i++) {
                if (winOpen.contains(driver.getTitle())) {
                    System.out.println();
                } else winNew = list.get(i);
            }
        }
        else System.out.println("Window did not open");

        driver.switchTo().window(winNew);
        System.out.println(driver.getTitle());

        // закрываем страницу
        driver.close();
        //Возвращаемся на главную страницу. (Стоит ли делать baseurl в LoginTest public?)
        driver.switchTo().window(url);
    }


    @After
    public void stop(){
        driver.quit();
        driver = null;
    }
}
