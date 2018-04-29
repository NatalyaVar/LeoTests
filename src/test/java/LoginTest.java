import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public abstract class LoginTest {

    //Вход на сайт по логину и паролю
    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        LoginTest.driver = driver;
    }

    public static WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();
    //Определяем первую страницу для входа  на сайт.
    private static String baseurl = "https://lingualeo.com/ru/dashboard";

    private String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    @BeforeClass
    public static void setUp() throws Exception {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);

        //Задаем неявную задержку
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //Идем на страницу входа
        driver.navigate().to(baseurl);

        //Ввод e-mail  и пароля
        driver.findElement(By.id("email")).sendKeys("NNNVVV123@mail.ru");
        driver.findElement(By.id("password")).sendKeys("147852");

//Кнопка Вход. Зашли на страницу DASHBOARD.
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div[1]/div[3]/form/input[4]")).click();

        //Ждем, пока откроется страница (станет виден логотип)
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("b-header__logo")));
    }
}


