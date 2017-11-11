package delfiStepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.CommentHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageStepDefs {
    private WebDriver driver;
    private WebDriverWait wait;
    private CommentHelper commentHelper;
    private int desktopCommentCount;
    private int mobileCommentCount;

    private static final Logger LOGGER = LogManager.getLogger(HomePageStepDefs.class);

    private String DESKTOP_PAGE_URL = "http://www.delfi.lv";
    private String MOBILE_PAGE_URL = "http://m.delfi.lv";
    private static final By FOLLOWING_SIBLING = By.xpath("following-sibling::*");

    @Given("Initialize web driver")
    public void initializeWebDriver() {
        LOGGER.info("Initializing new driver.");
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
        commentHelper = new CommentHelper();
    }

    @Given("Open Delfi desktop home")
    public void openDesktopHome() {
        LOGGER.info("Opening desktop home page.");
        driver.get(DESKTOP_PAGE_URL);
    }

    @Given("Open Delfi mobile home")
    public void openMobileHome() {
        LOGGER.info("Opening mobile home page.");
        driver.get(MOBILE_PAGE_URL);
    }

    @When("Look at comment count of article (.*) on desktop")
    public void getArticleCommentCountDesktop(String articleTitle) {
        LOGGER.info("Getting comment count for article: " + articleTitle);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(articleTitle)));
        if (element.findElements(FOLLOWING_SIBLING).size() != 0) {
            element = element.findElement(FOLLOWING_SIBLING);
            String elementText = element.getText();
            this.desktopCommentCount = commentHelper.stringToInt(elementText);
        } else {
            this.desktopCommentCount = 0;
        }
    }

    @When("Look at comment count of article (.*) on mobile")
    public void getArticleCommentCountMobile(String articleTitle) {
        LOGGER.info("Getting comment count for article: " + articleTitle);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(articleTitle)));
        if (element.findElements(FOLLOWING_SIBLING).size() != 0) {
            element = element.findElement(FOLLOWING_SIBLING);
            String elementText = element.getText();
            this.mobileCommentCount = commentHelper.stringToInt(elementText);
        } else {
            this.mobileCommentCount = 0;
        }
    }

    @Then("Comment counts should be same")
    public void commentCountsAreSame() {
        Assert.assertEquals("Comment counts aren't same", this.desktopCommentCount, this.mobileCommentCount);
    }
}
