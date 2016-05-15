package tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author mady
 */
public class SeleniumTests {

    private static WebDriver driver;

    public SeleniumTests() {
    }

    @BeforeClass
    public static void setUpClass() {
        System.setProperty( "webdriver.chrome.driver", "C:/Users/mady/Desktop/MADALINA/jar, js and exe files/chromedriver.exe" );
        driver = new ChromeDriver();
        driver.get( "http://www.polyteknisk.dk/home" );

        //step i
        WebElement bookAuthor = driver.findElement( By.name( "q" ) );
        WebElement searchButton = driver.findElement( By.className( "super_search_submit_button" ) );

        bookAuthor.sendKeys( "Silberschatz" );
        searchButton.click();

        //step ii
        int timeoutInSeconds = 10;
        WebDriverWait wait = new WebDriverWait( driver, timeoutInSeconds );
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.className( "imprint" ) ) );

        List<WebElement> listOfPublishers = driver.findElements( By.className( "imprint" ) );
        System.out.println( "***List of Publishers Start***\n" );
        for ( WebElement we : listOfPublishers ) {
            System.out.println( we.getText() );
        }
        System.out.println( "\n***List of Publishers End***\n" );

        //step iii
        List<WebElement> listOfCartButtons = driver.findElements( By.className( "submit-button" ) );

        listOfCartButtons.get( 0 ).click();
    }

    @AfterClass
    public static void tearDownClass() {
         driver.close();
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void stepIV() {
        String actualBookName= driver.findElement(By.xpath("//*[@id=\"main_content_white\"]/div[2]/div[2]/div[2]/div[1]/div[2]/h4/a")).getText();
        driver.findElement(By.linkText("Til kassen")).click();
        String bookName= driver.findElement(By.xpath("//*[@id=\"checkout_step1_form\"]/table/tbody/tr[1]/td[2]/a")).getText();
        assertTrue( bookName.contains(actualBookName));
    }
    
      @Test
    public void stepV() {
          assertTrue( driver.findElement(By.className("price_total")).getText().equals("778,50 dkk."));
    }
}
