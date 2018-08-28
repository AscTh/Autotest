package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helper.PageObject;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка "Войти в систему"
    @FindBy(xpath = "/html/body/div[2]/div/div[6]/button")
    private WebElement entryButton;

    // Поле логина
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.eld-body > div > div > div.b-table.b-table_width_full.ng-scope > div:nth-child(1) > input")
    private WebElement loginField;

    //Поле пароля
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.eld-body > div > div > div.b-table.b-table_width_full.ng-scope > div:nth-child(2) > input")
    private WebElement passwordField;

    //Кнопка "Вход"
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.eld-body > div > div > div.b-table.b-table_width_full.b-table__buttons > div.b-cell.a-right.a-middle > button")
    private WebElement loginButton;

    public void inputRegisterUser(String login, String password) {
        entryButton.click();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
    }

    public HomePage getHomePage() {
        loginButton.click();
        return new HomePage(driver);
    }
}