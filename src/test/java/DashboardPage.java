import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DashboardPage {


    private static Logger log = (Logger) LoggerFactory.getLogger(DashboardTest.class);

    private static WebDriver driver;
    public WebDriverWait wait;


    //Кнопки для проверки всплывающего окна - NotificationTest
    @FindBy(how = How.CSS, css = ".notification-content")
    public WebElement content;

    @FindBy(how = How.CSS, css = ".close-icon")
    public WebElement close_icon;

    @FindBy(how = How.CSS, css = ".notification-panel")
    public WebElement panel;


    //Методы для NotificationTest
    public void Notification (WebDriver driver, WebElement content, WebElement close_icon)
            throws IOException {

        //Пишем в файл out.txt содержимое заметки
        FileWriter writer = new FileWriter("output.txt", true);
        writer.write(content.getText() + "________Notification content" + "\n");
        writer.close();
        log.error("Write___");

        //Закрываем поле notification
       close_icon.click();
    }

}
