package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import helper.PageObject;
import helper.PurchasePlanStatus;

import java.sql.SQLException;

import static helper.DataConnection.update;

public class ListPlans extends PageObject {

    public ListPlans(WebDriver driver) {
        super(driver);
    }

    //Версия Плана Закупок
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[4]/div/div[1]/div[2]/table/tbody/tr/td[1]/a")
    private WebElement purchasePlanNumber;

    //Новая версия Плана Закупок
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[4]/div/div[1]/div[2]/table/tbody/tr[2]/td[1]/a")
    private WebElement newPurchasePlanNumber;

    //Версия Плана Графика
    @FindBy(xpath = "//*[@id=\"plan-schedules-index\"]/div[2]/table/tbody/tr/td[1]/a")
    private WebElement schedulePlanNumber;

    //Новая версия Плана Графика
    @FindBy(css = "#plan-schedules-index > div:nth-child(2) > table > tbody > tr:nth-child(2) > td:nth-child(1) > a")
    private WebElement newSchedulePlanNumber;

    // Кнопка создания нового ПЗ
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[4]/div/div[1]/div[1]/div[2]/a")
    private WebElement createNewPurchasePlan;

    //Создание нового ПГ
    @FindBy(xpath = "//*[@id=\"plan-schedules-index\"]/div[1]/div[2]/a")
    private WebElement newSchedulePlan;

    //Статус ПЗ
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[4]/div/div[1]/div[2]/table/tbody/tr/td[3]")
    private WebElement stateOldPP;

    //Статус нового ПЗ
    @FindBy(xpath = "/html/body/div[2]/div[3]/div[1]/div/div[4]/div/div[1]/div[2]/table/tbody/tr[2]/td[3]")
    private WebElement stateNewPP;

    public PurchasePlanPage openPurchasePlan() {
        wait.until(ExpectedConditions.elementToBeClickable(purchasePlanNumber));
        clickFindBy(purchasePlanNumber);
        return new PurchasePlanPage(driver);
    }

    public PurchasePlanPage openNewPurschasePlan() {
        wait.until(ExpectedConditions.elementToBeClickable(newPurchasePlanNumber));
        clickFindBy(newPurchasePlanNumber);
        return new PurchasePlanPage(driver);
    }

    public ScheldulePlanPage openScheldurePlun() {
        wait.until(ExpectedConditions.elementToBeClickable(schedulePlanNumber));
        schedulePlanNumber.click();
        return new ScheldulePlanPage(driver);
    }

    public void openNewScheldurePlun() {
        wait.until(ExpectedConditions.elementToBeClickable(newSchedulePlanNumber));
        newSchedulePlanNumber.click();
    }

    public PurchasePlanPage newPP() {
        wait.until(ExpectedConditions.elementToBeClickable(createNewPurchasePlan));
        createNewPurchasePlan.click();
        return new PurchasePlanPage(driver);
    }

    public ScheldulePlanPage newSP() {
        newSchedulePlan.click();
        return new ScheldulePlanPage(driver);
    }

    public void reconcile() throws SQLException, ClassNotFoundException {
        char[] str = newPurchasePlanNumber.getAttribute("href").replaceAll("\\D+", "").toCharArray();
        String ID = "";
        for (int i = 1; i < str.length; i++) {
            ID = ID.concat(String.valueOf(str[i]));
        }
        int ENTITY_ID = Integer.parseInt(ID);
        update(PurchasePlanStatus.APPROVED.getId(), ENTITY_ID);
    }
}