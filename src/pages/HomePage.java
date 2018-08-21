package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.PageObject;

import java.io.IOException;

public class HomePage extends PageObject {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Выбор заказчика
     */
    @FindBy(xpath = "/html/body/div[4]/div[1]/div/div[4]/button")
    private WebElement customer;

    /**
     * Поле ввода заказчика
     */
    @FindBy(xpath = "//*[@id=\"ngdialog1\"]/div[2]/div/div[2]/div[1]/div/input")
    private WebElement customerField;

    /**
     * Клик по нужному заказчику
     */
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.dialog-body > div > participant-tree-builder > div > div.organization-tree__node > div.organization-tree__node-name")
    private WebElement customerName;

    /**
     * Кнопка выбрать
     */
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div.dialog-footer > button:nth-child(1)")
    private WebElement buttonChoose;

    /**
     * Кнопка закрытия
     */
    @FindBy(css = "#ngdialog4 > div.ngdialog-content > div > div.dialog-footer > button.gn-btn.gn-btn_background_grey")
    private WebElement exit;

    /**
     * Выбор года
     */
    @FindBy(css = "body > div.eaist-header-layout.ng-isolate-scope > div.eaist-header > div > div.eaist-dropdown.ng-scope > button")
    private WebElement selectYear;

    /**
     * Кнопка планирования
     */
    @FindBy(css = "body > div.eaist-header-layout.ng-isolate-scope > div.eaist-navigation > div > div.eaist-navigation__systems-container.eaist-navigation__systems-container_width_full > a:nth-child(2)")
    private WebElement planing;

    /**
     * Кнопка планы
     */
    private By plan = By.xpath("/html/body/div[2]/div[2]/eaist-sidebar-tree/ul/li[3]/a");

    public HomePage searchCustomer(String str) throws IOException, InterruptedException {
        waiting(customer);
        // Проверка выбран ли уже требуемый заказчик
        if (readCustomer(Long.parseLong(str), customer.getText()))
            return this;
        else {
            clickFindBy(customer);
            customerField.sendKeys(str);
            if (searchHashMap(Long.parseLong(str), customerName)) {
                clickFindBy(customerName);
                clickFindBy(buttonChoose);
                Thread.sleep(5000);
                System.out.println("Success");
            } else
                System.out.println("Error");
        }
        return this;
    }

    public HomePage selectYear(String string) {
        if (selectYear.getText().equals(string)) {
            return this;
        } else {
            System.out.println("Exception");
            selectYear.click();
        }
        return this;
    }

    public ListOZ getListOZ() {
        clickFindBy(planing);
        /*List<WebElement> listTagA = driver.findElements(By.tagName("a"));
        for (WebElement aListTagA : listTagA) {
            if (aListTagA.getAttribute("href").contains("/index.html#/plan-objects")) {
                aListTagA.click();
                System.result_console.setText("Выбор заказчика: ");
                break;
            }
        }*/
        return new ListOZ(driver);
    }

    public void plans() {
        wait.until(ExpectedConditions.elementToBeClickable(plan));
        clickBy(plan);
    }
}