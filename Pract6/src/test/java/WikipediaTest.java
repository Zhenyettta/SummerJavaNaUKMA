import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.edge.EdgeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class WikipediaTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.edge.driver", "C:\\users\\zheck\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void testWikipediaTitle() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org/");
        String title = driver.getTitle();
        assertEquals("Wikipedia", title);
        driver.quit();
    }

    @Test
    public void testWikipediaLanguageLinks() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org/");
        WebElement englishLink = driver.findElement(By.id("js-link-box-en"));
        assertNotNull(englishLink);
        driver.quit();
    }

    @Test
    public void testWikipediaSearch() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.wikipedia.org/");
        WebElement searchInput = driver.findElement(By.name("search"));
        searchInput.sendKeys("Selenium (software)");
        searchInput.submit();
        WebElement heading = driver.findElement(By.id("firstHeading"));
        assertEquals("Selenium (software)", heading.getText());
        driver.quit();
    }
}
