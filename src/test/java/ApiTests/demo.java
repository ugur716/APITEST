package ApiTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class demo {
    Logger logger= LogManager.getLogger(demo.class.getName());


    @Test
    public void tc1(){
        logger.info("open url");
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        logger.debug("continue");
        driver.get("https://www.youtube.com/watch?v=q2wfjl0ffA");
        driver.quit();
    }
}
