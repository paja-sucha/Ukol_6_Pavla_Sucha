package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestsSigningUpForCourses {

    WebDriver browser;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void parentLogsInToApplication() {
        browser.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement buttonLogIn = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-user')]"));
        buttonLogIn.click();

        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys("arthur.dent@alphacentauri.org");
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys("theAnswerIs42");
        WebElement logInButton = browser.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();

        WebElement arthurDent = browser.findElement(By.xpath("//a[@class='dropdown-toggle']"));
        Assertions.assertEquals("Arthur Dent", arthurDent.getText(), "Log in FAILED");

    }

    @Test
    public void courseSelectionThenAccountLoginThenRegistrationForTheCourse() {
        browser.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement buttonSelectProgrammingCourse = browser.findElement(By.xpath("//a[contains(@href,'programova')]"));
        buttonSelectProgrammingCourse.click();

        WebElement buttonSelectJavaCourse = browser.findElement(By.xpath("//a[contains(@href,'71-java-1')]"));
        buttonSelectJavaCourse.click();

        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys("arthur.dent@alphacentauri.org");
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys("theAnswerIs42");
        WebElement logInButton = browser.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();

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

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[@class='mb-1 btn btn-sm btn-success']"));
        Assertions.assertEquals("Stáhnout potvrzení o přihlášení", registrationConfirmation.getText(), "Course selection -> " +
                "Login -> Registration - FAILED");

        WebElement buttonDanger = browser.findElement(By.xpath("//div/a[contains(@class, 'danger')]"));
        buttonDanger.click();
        WebElement labelLoggedOut = browser.findElement(By.xpath("//label[@for='logged_out_other']"));
        labelLoggedOut.click();
        WebElement fieldReason = browser.findElement(By.id("logged_out_reason"));
        fieldReason.sendKeys("Test");
        WebElement buttonSubmitLogOut = browser.findElement(By.xpath("//td/input[@type='submit']"));
        buttonSubmitLogOut.click();

    }

    @Test
    public void accountLoginThenCourseSelectionThenRegistration() {
        browser.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement buttonLogIn = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-user')]"));
        buttonLogIn.click();

        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys("arthur.dent@alphacentauri.org");
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys("theAnswerIs42");
        WebElement logInButton = browser.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();

        WebElement buttonNewApplication = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-plus-circle')]"));
        buttonNewApplication.click();

        WebElement buttonSelectWebCourse = browser.findElement(By.xpath("//a[contains(@href,'11-trimesicni-kurzy-webu')]"));
        buttonSelectWebCourse.click();

        WebElement buttonSelectHTML1Course = browser.findElement(By.xpath("//a[contains(@href,'41-html-1')]"));
        buttonSelectHTML1Course.click();

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

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[@class='mb-1 btn btn-sm btn-success']"));
        Assertions.assertEquals("Stáhnout potvrzení o přihlášení", registrationConfirmation.getText(), "Course selection -> " +
                "Login -> Registration - FAILED");

        WebElement buttonDanger = browser.findElement(By.xpath("//div/a[contains(@class, 'danger')]"));
        buttonDanger.click();
        WebElement labelLoggedOut = browser.findElement(By.xpath("//label[@for='logged_out_other']"));
        labelLoggedOut.click();
        WebElement fieldReason = browser.findElement(By.id("logged_out_reason"));
        fieldReason.sendKeys("Test");
        WebElement buttonSubmitLogOut = browser.findElement(By.xpath("//td/input[@type='submit']"));
        buttonSubmitLogOut.click();

    }

    @Test
    public void correctNumberOfRowsOnPagesWithApplications() {
        browser.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement buttonLogIn = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-user')]"));
        buttonLogIn.click();

        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys("arthur.dent@alphacentauri.org");
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys("theAnswerIs42");
        WebElement logInButton = browser.findElement(By.xpath("//button[@type='submit']"));
        logInButton.click();

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
}