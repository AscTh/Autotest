package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helper.PageObject;

public class CalculationOfNMC extends PageObject {

    CalculationOfNMC(WebDriver driver) {
        super(driver);
    }

    /**
     * Дата расчета
     */
    @FindBy(xpath = "//*[@id=\"spgz-index\"]/fieldset[1]/div[5]/div[2]/span/span/input")
    private WebElement calculationDate;

    /**
     * Иной
     */
    @FindBy(xpath = "//*[@id=\"spgz-index\"]/fieldset[2]/table/tbody/tr[10]/td[1]/input")
    private WebElement other;

    /**
     * НМЦ Рассчитанная
     */
    @FindBy(xpath = "//*[@id=\"spgz-index\"]/fieldset[2]/table/tbody/tr[10]/td[3]/input")
    private WebElement sum;

    /**
     * Обоснование выбора иного метода расчета
     */
    @FindBy(xpath = "//*[@id=\"spgz-index\"]/fieldset[2]/table/tbody/tr[11]/td[2]/textarea")
    private WebElement entryField;

    /**
     * Сохранить НМЦ
     */
    @FindBy(xpath = "//*[@id=\"spgz-index\"]/fieldset[3]/span/div/div/button")
    private WebElement saveNMC;

    /**
     * Расчет НМЦ
     */
    public CreateNewDOZ calculation(String amount, String string) {
        calculationDate.sendKeys(formatDate(false));
        other.click();
        sum.clear();
        sum.sendKeys(amount);
        entryField.sendKeys(string);
        saveNMC.click();
        return new CreateNewDOZ(driver);
    }
}