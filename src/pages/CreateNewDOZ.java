package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.PageObject;

public class CreateNewDOZ extends PageObject {

    CreateNewDOZ(WebDriver driver) {
        super(driver);
    }

    /**
     * Кнопка добавления спецификации
     */
    @FindBy(css = "div.col-md-12 > a.btn.green-btn")
    private WebElement addSpecification;

    /**
     * Кнопка редактирования СПГЗ
     */
    @FindBy(xpath = "(//a[contains(text(),'Редактировать')])[2]")
    private WebElement editSPGZ;

    /**
     * Кнопка выбора СПГЗ из списка доступных
     */
    @FindBy(xpath = "//*[@id=\"spec-spgz-select\"]/div[3]/div/div/table/tbody/tr[2]/td[2]/label")
    private WebElement selectSPGZ;

    /**
     * Кнопка "Выбрать"
     */
    @FindBy(xpath = "//*[@id=\"spec-spgz-select-modal\"]/div/div/div[3]/button[1]")
    private WebElement buttonSelect;

    /**
     * Объем закупки
     */
    @FindBy(name = "purchaseAmount")
    private WebElement amountOfPurchase;

    /**
     * "Устанавливается в лоте"
     */
    @FindBy(css = "div.col-md-8 > input.ng-valid.ng-dirty")
    private WebElement installedInLot;

    /**
     * "Обоснование суммы закупки"
     */
    @FindBy(name = "reason")
    private WebElement justification;

    /**
     * Файл-обоснование суммы закупки
     */
    @FindBy(name = "file")
    private WebElement file;

    /**
     * Добавить этап поставки
     */
    @FindBy(css = "div.col-md-10 > a.btn.green-btn")
    private WebElement addSupplyStage;

    /**
     * Объем поставки
     */
    @FindBy(xpath = "//*[@id=\"delivery-modal\"]/div/div/div[2]/fieldset/div[1]/div[2]/input")
    private WebElement scopeOfSupply;

    /**
     * Выборка этапа поставки по дате
     */
    @FindBy(xpath = "(//input[@name='date-type'])[2]")
    private WebElement selectDate;

    /**
     * Дата начала поставки
     */
    @FindBy(xpath = "(//input[@name=''])[7]")
    private WebElement deliveryStartDate;

    /**
     * Дака окончания поставки
     */
    @FindBy(xpath = "(//input[@name=''])[8]")
    private WebElement deliveryEndDate;

    /**
     * Сохранить спеку
     */
    @FindBy(xpath = "//*[@id=\"delivery-modal\"]/div/div/div[3]/button[1]")
    private WebElement buttonSaveDelivery;

    /**
     * Кнопка "Редактировать"
     */
    @FindBy(xpath = "//*[@id=\"detailed-purchase-spec-new-modal\"]/div/div/div[2]/ng-include/fieldset[4]/div[2]/div/div/table/tbody/tr/td[5]/div/ul/li[1]/a")
    private WebElement buttonEdit;

    /**
     * Кнопка "Сохранить спецификацию"
     */
    @FindBy(xpath = "//*[@id=\"detailed-purchase-spec-new-modal\"]/div/div/div[3]/button[1]")
    private WebElement buttonSaveSpec;

    /**
     * Расчет НМЦ
     */
    @FindBy(css = "i.fa.fa-calculator")
    private WebElement calculatorNMC;

    /**
     * Адрес поставки ФИАС
     */
    @FindBy(id = "fias-input")
    private WebElement fiasInput;

    /**
     * ФИАС код
     */
    @FindBy(id = "ui-id-3")
    private WebElement fiasCode;

    /**
     * Сохранение ДОЗ
     */
    @FindBy(xpath = "//*[@id=\"applicationPanel\"]/div[3]/button")
    private WebElement saveDOZ;

    /**
     * Утверждение ДОЗ
     */
    @FindBy(css = "#applicationPanel > div.pull-right > button:nth-child(2)")
    private WebElement approveDOZ;

    /**
     * Создать лот
     */
    @FindBy(css = "#applicationPanel > div.pull-right > button:nth-child(1)")
    private WebElement createLot;

    // Добавление спецификации
    public void addSpecification(String string) {
        clickFindBy(addSpecification);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[contains(text(),'Редактировать')])[2]")));
        editSPGZ.click();
        selectSPGZ.click();
        buttonSelect.click();
        amountOfPurchase.clear();
        amountOfPurchase.sendKeys(string);
    }

    // Установление признака "Устанавливается в лоте"
    public void installedInLot() {
        installedInLot.click();
    }

    // Обоснование суммы закупки и файл
    public void inputFile(String justificationStr, String filePath) {
        justification.sendKeys(justificationStr);
        file.sendKeys(filePath);
    }

    // Редактирование графика поставки
    public void editDelivery() {
        clickFindBy(buttonEdit);
        deliveryStartDate.sendKeys(formatDate(false));
        buttonSaveDelivery.click();
    }

    // Создание графика поставки
    public void addSupplyStage(String str1) {
        addSupplyStage.click();
        scopeOfSupply.sendKeys(str1);
        selectDate.click();
        deliveryStartDate.sendKeys(formatDate(false));
        deliveryEndDate.sendKeys(formatDate(false));
        buttonSaveDelivery.click();
    }

    public void buttonSaveSpec() {
        buttonSaveSpec.click();
    }

    public void fiasIuput(String string) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fias-input")));
        fiasInput.sendKeys(string);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-5")));
        fiasCode.click();
    }

    public CalculationOfNMC getCalculatorNMC() {
        clickFindBy(calculatorNMC);
        return new CalculationOfNMC(driver);
    }

    public void saveDOZ() throws InterruptedException {
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applicationPanel\"]/div[3]/button")));
        clickFindBy(saveDOZ);
    }

    public Pair<String, ListPlans> getApproveDoz() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#applicationPanel > div.pull-right > button:nth-child(2)")));
        clickFindBy(approveDOZ);
        Thread.sleep(5000);
        return new Pair<>(driver.getCurrentUrl(), new ListPlans(driver));
    }

    public CreateNewLot createLot() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#applicationPanel > div.pull-right > button:nth-child(1)")));
        clickFindBy(createLot);
        return new CreateNewLot(driver);
    }
}