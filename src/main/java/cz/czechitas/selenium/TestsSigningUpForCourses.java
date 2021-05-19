package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestsSigningUpForCourses {

    private static final String URL_APLIKACE = "https://cz-test-dva.herokuapp.com/";   //konstanta

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void parentLogsInToApplication() {
        browser.navigate().to(URL_APLIKACE);
        WebElement buttonLogIn = browser.findElement(By.className("qa-login-button"));
        buttonLogIn.click();

        logIn();

        WebElement parentName = browser.findElement(By.xpath("//a[" + className("qa-logged-in-username") + "]"));
        Assertions.assertEquals("Arthur Dent", parentName.getText(), "Log in FAILED");
    }

    @Test
    public void courseSelectionThenAccountLoginThenRegistrationForTheCourse() {
        browser.navigate().to(URL_APLIKACE);
        WebElement buttonSelectProgrammingCourse = browser.findElement(By.xpath("//a[contains(@href,'programova')]"));
        buttonSelectProgrammingCourse.click();

        WebElement buttonSelectJavaCourse = browser.findElement(By.xpath("//a[contains(@href,'71-java-1')]"));
        buttonSelectJavaCourse.click();

        logIn();

        WebElement buttonChooseDate = browser.findElement(By.xpath("//button[@data-id='term_id']"));
        buttonChooseDate.click();
        WebElement buttonChosenDateOfCourse = browser.findElement(By.id("bs-select-1-0"));
        buttonChosenDateOfCourse.click();

        fillInForm();

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[@class='mb-1 btn btn-sm btn-success']"));
        Assertions.assertNotNull(registrationConfirmation, "Course selection -> " +
                "Login -> Registration - FAILED");
    }

    @Test
    public void accountLoginThenCourseSelectionThenRegistration() {
        browser.navigate().to(URL_APLIKACE);
        WebElement buttonLogIn = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-user')]"));
        buttonLogIn.click();

        logIn();

        WebElement buttonNewApplication = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-plus-circle')]"));
        buttonNewApplication.click();

        WebElement buttonSelectWebCourse = browser.findElement(By.xpath("//a[contains(@href,'11-trimesicni-kurzy-webu')]"));
        buttonSelectWebCourse.click();

        WebElement buttonSelectHTML1Course = browser.findElement(By.xpath("//a[contains(@href,'41-html-1')]"));
        buttonSelectHTML1Course.click();

        fillInForm();

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[@class='mb-1 btn btn-sm btn-success']"));
        Assertions.assertNotNull(registrationConfirmation, "Course selection -> " +
                "Login -> Registration - FAILED");
    }

    @Test
    public void correctNumberOfRowsOnPagesWithApplications() {
        browser.navigate().to(URL_APLIKACE);
        WebElement buttonLogIn = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-user')]"));
        buttonLogIn.click();

        logIn();

        WebElement selectNumberOfRows = browser.findElement(By.xpath("//div/label/select[@name='DataTables_Table_0_length']"));
        selectNumberOfRows.click();
        WebElement number15 = browser.findElement(By.xpath("//div/label/select/option[@value='15']"));
        number15.click();
        int intNumber15 = Integer.parseInt(number15.getText());
        List<WebElement> rows = browser.findElements(By.xpath("//table/tbody/tr"));

        Assertions.assertEquals(intNumber15, rows.size());

        selectNumberOfRows.click();
        WebElement number30 = browser.findElement(By.xpath("//div/label/select/option[@value='15']"));
        number30.click();
        int intNumber30 = Integer.parseInt(number30.getText());

        Assertions.assertEquals(intNumber30, rows.size());
    }

    @AfterEach
    public void tearDown() {
        browser.close();
    }


    public void logIn() {
        enterEmail();
        enterPassword();
        clickOnLogInButton();
    }

    public void enterEmail() {
        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys("arthur.dent@alphacentauri.org");
    }

    public void enterPassword() {
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys("theAnswerIs42");
    }

    public void clickOnLogInButton() {
        WebElement logInButton = browser.findElement(By.xpath("//button[" + className("qa-login-button") + "]"));
        logInButton.click();
    }

    public void fillInForm() {
        WebElement buttonChooseDate = browser.findElement(By.xpath("//button[@data-id='term_id']"));
        buttonChooseDate.click();
        WebElement buttonChosenDateOfCourse = browser.findElement(By.id("bs-select-1-0"));
        buttonChosenDateOfCourse.click();
        WebElement fieldFirstNameOfStudent = browser.findElement(By.id("forename"));
        fieldFirstNameOfStudent.sendKeys("Douglas");
        WebElement fieldSurnameOfStudent = browser.findElement(By.id("surname"));
        fieldSurnameOfStudent.sendKeys("Adams");
        WebElement fieldBirthdate = browser.findElement(By.id("birthday"));
        fieldBirthdate.sendKeys("25.05.2001");
        WebElement labelPaymentCash = browser.findElement(By.xpath("//label[@for='payment_cash']"));
        labelPaymentCash.click();
        WebElement labelRestrictions = browser.findElement(By.xpath("//label[@for='restrictions_yes']"));
        labelRestrictions.click();
        WebElement fieldRestrictions = browser.findElement(By.id("restrictions"));
        fieldRestrictions.sendKeys("He is carrying a towel.");
        WebElement labelTermsConditions = browser.findElement(By.xpath("//label[@for='terms_conditions']"));
        labelTermsConditions.click();
        WebElement buttonSubmit = browser.findElement(By.xpath("//input[@type='submit']"));
        buttonSubmit.click();
    }

    public static String className(String htmlClass) {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + htmlClass + " ')";
    }
}
