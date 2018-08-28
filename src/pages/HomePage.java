package pages;

import helper.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;

public class HomePage extends PageObject {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Выбор заказчика
    @FindBy(xpath = "/html/body/div[4]/div[1]/div/div[4]/button")
    private WebElement customer;

    //Поле ввода заказчика
    @FindBy(xpath = "//*[@id=\"ngdialog1\"]/div[2]/div/div[2]/div[1]/div/input")
    private WebElement customerField;

    // Клик по нужному заказчику
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.dialog-body > div > participant-tree-builder > div > div.organization-tree__node > div.organization-tree__node-name")
    private WebElement customerName;

    // Кнопка выбрать
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.dialog-footer > button:nth-child(1)")
    private WebElement buttonChoose;

    // Кнопка закрытия
    @FindBy(css = "#ngdialog4 > div.ngdialog-content > div > div.dialog-footer > button.gn-btn.gn-btn_background_grey")
    private WebElement exit;

    //Выбор года
    @FindBy(css = "body > div.eaist-header-layout.ng-isolate-scope > div.eaist-header > div > div.eaist-dropdown.ng-scope > button")
    private WebElement selectYear;

    //Кнопка планирования
//    @FindBy(linkText = "Планирование")
    @FindBy(xpath = "//a[contains(text(),'Планирование')]")
    private WebElement planing;

    //Кнопка планы
    @FindBy(linkText = "Планы")
    private WebElement plans;

    public HomePage searchCustomer(final String customer_id) throws IOException, InterruptedException {
        waiting(customer);
        // Проверка выбран ли уже требуемый заказчик
        if (readCustomer(Long.parseLong(customer_id), customer.getText()))
            return this;
        else {
            customer.click();
            wait.until(ExpectedConditions.elementToBeClickable(customerField));
            customerField.sendKeys(customer_id);
            searchHashMap(Long.parseLong(customer_id), customerName);
            customerName.click();
            wait.until(ExpectedConditions.elementToBeClickable(buttonChoose));
            buttonChoose.click();
            print("Успешный выбор заказчика\n", false);

        }
        return this;
    }

    public HomePage selectYear() {
        String year = formatDate(false).substring(6);
        if (selectYear.getText().equals("2018")) {
            return this;
        } else {
            print("Failed to select planning year\n", false);
            selectYear.click();
        }
        return this;
    }

    public ListOZ getListOZ() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        wait.until(ExpectedConditions.elementToBeClickable(planing));
        planing.click();
        return new ListOZ(driver);
    }

    public void plans() {
        wait.until(ExpectedConditions.elementToBeClickable(plans));
        plans.click();
    }
}