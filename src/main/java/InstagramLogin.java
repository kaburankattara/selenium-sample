import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InstagramLogin {

    public static void main(String args[]) {
        try {
            WebDriverManager.chromedriver().setup();

            System.setProperty("webdriver.chrome.driver","/opt/chromedriver/107.0.5304.62/chromedriver_linux64");
            ChromeDriver driver = new ChromeDriver();

            // ログイン
            driver.get("https://www.instagram.com/");
            WebElement usernameTextBox = driver.findElement(By.name("username"));
            WebElement passwordTextBox = driver.findElement(By.name("password"));
            WebElement submitButton = driver.findElement(By.className("_acan"));
            usernameTextBox.sendKeys("");
            passwordTextBox.sendKeys("");
            submitButton.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
            try {
                WebElement messageOfIsSaveLoginInfo = driver.findElement(By.className("_aa58"));
                if ("ログイン情報を保存しますか？".equals(messageOfIsSaveLoginInfo.getText())) {
                    WebElement submitButtonOfIsSaveLoginInfo = driver.findElement(By.className("_acan"));
                    submitButtonOfIsSaveLoginInfo.click();
                }
            } catch (Exception e) {
                System.out.println("「ログイン情報を保存しますか？」：無し");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());
            }

            while (!(driver.findElements(By.cssSelector("button[class='_a9-- _a9_0']")).size() > 0)) {
                continue;
            }
            WebElement submitButtonOfIsONInfoMessage = driver.findElement(By.cssSelector("button[class='_a9-- _a9_0']"));
            submitButtonOfIsONInfoMessage.click();

            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
            driver.get("https://www.instagram.com/explore/tags/%E9%A3%9F/");

            // セッションを終了します
//            driver.quit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
}
