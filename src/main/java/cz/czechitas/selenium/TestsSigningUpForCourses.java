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

        goToLoginPage();
        logIn("arthur.dent@alphacentauri.org", "theAnswerIs42");

        WebElement parentName = browser.findElement(By.xpath("//a[" + className("qa-logged-in-username") + "]"));
        Assertions.assertEquals("Arthur Dent", parentName.getText(), "Log in FAILED");
    }

    @Test
    public void courseSelectionThenAccountLoginThenRegistrationForTheCourse() {
        browser.navigate().to(URL_APLIKACE);

        goToProgrammingCoursesPage();
        selectJavaCourse();
        logIn("arthur.dent@alphacentauri.org", "theAnswerIs42");
        fillInForm("Douglas", "Adams", "25.05.2001", "He is carrying a towel.");

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[" +className("qa-confirmation-certificate-button") + "]"));
        Assertions.assertNotNull(registrationConfirmation, "Course selection -> " +
                "Login -> Registration - FAILED");
    }

    @Test
    public void accountLoginThenCourseSelectionThenRegistration() {
        browser.navigate().to(URL_APLIKACE);

        goToLoginPage();
        logIn("arthur.dent@alphacentauri.org", "theAnswerIs42");
        clickOnNewAplicationBtn();
        goToWebCoursesPage();
        selectHTML1Course();
        fillInForm("Douglas", "Adams", "25.05.2001", "He is carrying a towel.");

        WebElement registrationConfirmation = browser.findElement(By.xpath("//tr/td/a[" +className("qa-confirmation-certificate-button") + "]"));
        Assertions.assertNotNull(registrationConfirmation, "Course selection -> " +
                "Login -> Registration - FAILED");
    }

    @Test
    public void correctNumberOfRowsOnPagesWithApplications() {
        browser.navigate().to(URL_APLIKACE);

        goToLoginPage();
        logIn("arthur.dent@alphacentauri.org", "theAnswerIs42");
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

    //----------------------------------------------------------------

    public void goToLoginPage() {
        WebElement buttonLogIn = browser.findElement(By.className("qa-login-button"));
        buttonLogIn.click();
    }

    public void logIn(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickOnLogInButton();
    }

    public void enterEmail(String email) {
        WebElement fieldEmail = browser.findElement(By.id("email"));
        fieldEmail.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement fieldPassword = browser.findElement(By.id("password"));
        fieldPassword.sendKeys(password);
    }

    public void clickOnLogInButton() {
        WebElement logInButton = browser.findElement(By.xpath("//button[" + className("qa-login-button") + "]"));
        logInButton.click();
    }

    public void goToProgrammingCoursesPage() {
        WebElement buttonSelectProgrammingCourse = browser.findElement(By.xpath("//a[contains(@href,'programova')]"));
        buttonSelectProgrammingCourse.click();
    }

    public void selectJavaCourse() {
        WebElement buttonSelectJavaCourse = browser.findElement(By.xpath("//a[contains(@href,'71-java-1')]"));
        buttonSelectJavaCourse.click();
    }

    public void fillInForm(String firstName, String surname, String birthDate, String restrictions) {
        chooseDateOfCourse();
        enterFirstName(firstName);
        enterSurname(surname);
        enterBirthDate(birthDate);
        checkTheBoxPayByCash();
        checkTheBoxRestrictions();
        enterRestrictions(restrictions);
        chceckTheBoxTermsConditions();
        clickOnSubmitBtn();
    }

    public void chooseDateOfCourse() {
        WebElement buttonChooseDate = browser.findElement(By.xpath("//button[@data-id='term_id']"));
        buttonChooseDate.click();
        WebElement buttonChosenDateOfCourse = browser.findElement(By.id("bs-select-1-0"));
        buttonChosenDateOfCourse.click();
    }

    public void enterFirstName(String firstName) {
        WebElement fieldFirstNameOfStudent = browser.findElement(By.id("forename"));
        fieldFirstNameOfStudent.sendKeys(firstName);
    }

    public void enterSurname(String surname) {
        WebElement fieldSurnameOfStudent = browser.findElement(By.id("surname"));
        fieldSurnameOfStudent.sendKeys(surname);
    }

    public void enterBirthDate(String birthDate) {
        WebElement fieldBirthdate = browser.findElement(By.id("birthday"));
        fieldBirthdate.sendKeys(birthDate);
    }

    public void checkTheBoxPayByCash() {
        WebElement labelPaymentCash = browser.findElement(By.xpath("//label[@for='payment_cash']"));
        labelPaymentCash.click();
    }

    public void checkTheBoxRestrictions() {
        WebElement labelRestrictions = browser.findElement(By.xpath("//label[@for='restrictions_yes']"));
        labelRestrictions.click();
    }

    public void enterRestrictions(String restrictions) {
        WebElement fieldRestrictions = browser.findElement(By.id("restrictions"));
        fieldRestrictions.sendKeys(restrictions);
    }

    public void chceckTheBoxTermsConditions() {
        WebElement labelTermsConditions = browser.findElement(By.xpath("//label[@for='terms_conditions']"));
        labelTermsConditions.click();
    }

    public void clickOnSubmitBtn() {
        WebElement buttonSubmit = browser.findElement(By.xpath("//input[@type='submit']"));
        buttonSubmit.click();
    }

    public void clickOnNewAplicationBtn() {
        WebElement buttonNewApplication = browser.findElement(By.xpath("//div/a/i[contains(@class, 'fa-plus-circle')]"));
        buttonNewApplication.click();
    }

    public void goToWebCoursesPage() {
        WebElement buttonSelectWebCourse = browser.findElement(By.xpath("//a[contains(@href,'11-trimesicni-kurzy-webu')]"));
        buttonSelectWebCourse.click();
    }

    public void selectHTML1Course() {
        WebElement buttonSelectHTML1Course = browser.findElement(By.xpath("//a[contains(@href,'41-html-1')]"));
        buttonSelectHTML1Course.click();
    }

    public static String className(String htmlClass) {
        return "contains(concat(' ', normalize-space(@class), ' '), ' " + htmlClass + " ')";
    }
}
