import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Main {

    public static void main(String args[]) {
        try {
            System.out.println("スクレイピング開始");

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            WebDriverManager.chromedriver().setup();

            System.setProperty("webdriver.chrome.driver","/opt/chromedriver/107.0.5304.62/chromedriver_linux64");
            WebDriver driver = new ChromeDriver(options);

            driver.get("https://www.google.com/?hl=ja");

            // wait
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));

            // 要素を検索するためのコマンドを送信します
            WebElement textBox = driver.findElement(By.className("gLFyf"));

            // 要素に対してアクションを実行する
            textBox.sendKeys("Selenium");
            textBox.sendKeys(Keys.ENTER);

            System.out.println("Title:" + driver.getTitle());
            System.out.println("スクレイピング完了");

            // セッションを終了します
            driver.quit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
