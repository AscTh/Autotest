package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import helper.PageObject;

public class CreateNewLot extends PageObject {

    CreateNewLot(WebDriver driver) {
        super(driver);
    }

    // Тип контракта
    @FindBy(name = "contractTypeId")
    private WebElement typeContract;

    // Способ определения поставщика
    @FindBy(css = "select[name=\"methodOfSupplierId\"]")
    private WebElement definitionMethod;

    // Основание заключения контракта с единственным поставщиком
    @FindBy(name = "reasonMethodOfSupplierId")
    private WebElement basisOfConclusion;

    // Обоснование способа определения поставщика
    @FindBy(name = "decipherReasonMethodOfSupplier")
    private WebElement justificationOfMethod;

    // Файл-обоснование способа определения поставщика
    @FindBy(name = "fileReasonMethodOfSupplier")
    private WebElement fileReasonMethodOfSupplier;

    // Размещение среди СМП
    @FindBy(name = "smpAccomodation")
    private WebElement smpAccomodation;

    // Кнопка поставщик
    @FindBy(css = "#info > fieldset > div:nth-child(19) > div.col-md-8 > a")
    private WebElement providerButton;

    // Выбор поставщика
    @FindBy(css = "#select-supplier-modal > div > div > div.modal-body > div:nth-child(1) > div > input")
    private WebElement inpurProvider;

    // Клик по выбранному поставщику
    @FindBy(css = "#select-supplier-modal > div > div > div.modal-body > div.row.table-row > div > div > table > tbody > tr")
    private WebElement selectProvider;

    // Кнопка выбора поставщика
    @FindBy(css = "#select-supplier-modal > div > div > div.modal-footer > button.btn.green-btn")
    private WebElement selectProviderButton;

    // Планируемая дата публикации
    @FindBy(css = "#info > fieldset > div:nth-child(21) > div.col-md-8 > span > span > input")
    private WebElement datePlannedPublication;

    // Срок исполнения контракта
    @FindBy(css = "#info > fieldset > div:nth-child(23) > div.col-md-8 > span > span > input")
    private WebElement datePerformanceContract;

    // Обоснование метода определения НМЦ
    @FindBy(css = "#nmc > fieldset > div:nth-child(5) > div > textarea")
    private WebElement justificationNMC;

    // Обоснование невозможности применения методов определения НМЦК, указанных в ч.1 ст.22 44-ФЗ
    @FindBy(css = "#nmc > fieldset > div:nth-child(6) > div > textarea")
    private WebElement impossibilityNMCK;

    // Дата оплаты
    @FindBy(css = "#spec > fieldset > div:nth-child(9) > div:nth-child(2) > div.col-md-2 > span > span > input")
    private WebElement dateOfPayment;

    // Кнопка сохранения лота
    @FindBy(css = "#applicationPanel > div.row > div > div.pull-left > button")
    private WebElement saveLot;

    // Отправить на согласование в ГРБС
    @FindBy(css = "#application-navigation > div.row > div > div.pull-right > button.btn.green-btn.ng-scope")
    private WebElement sendToGRBS;

    // Кнопка подтверждения отправки в ГРБС
    @FindBy(css = "#ngdialog1 > div.ngdialog-content > div > div > div.modal-footer > button.g-btn.g-btn_background_green")
    private WebElement yes_sendToGRBS;

    // Кнопка согласовать
//    @FindBy(css = "#application-navigation > div.row > div > div.pull-right > button:nth-child(6)")
    @FindBy(xpath = "//*[@id=\"application-navigation\"]/div[3]/div/div[2]/button[6]")
    private WebElement approve;

    // Кнопка подтверждения согласования
    @FindBy(xpath = ".//button[text()='Да']")
    private WebElement yes_approve;

    // Утвердить
//    @FindBy(css = "#application-navigation > div.row > div > div.pull-right > button.btn.green-btn.ng-scope")
    @FindBy(xpath = "(//button[@type='button'])[12]")
    private WebElement reconcile;

    public void selectTypeContract(String string) {
        setSelect(typeContract);
        select.selectByVisibleText(string);
    }

    public CreateNewLot selectDefinitionMethod(String string, String basis) {
        setSelect(definitionMethod);
        select.selectByVisibleText(string);
        selectBasisOfConclusion(basis);
        return this;
    }

    private void selectBasisOfConclusion(String string) {
        setSelect(basisOfConclusion);
        select.selectByVisibleText(string);
    }

    public void selectSMP() {
        setSelect(smpAccomodation);
        select.selectByVisibleText("Нет");
    }

    public void inputJustificationOfMethod(String str_1, String file) {
        justificationOfMethod.sendKeys(str_1);
        fileReasonMethodOfSupplier.sendKeys(file);
        justificationNMC.sendKeys(str_1);
        impossibilityNMCK.sendKeys(str_1);
    }

    public void selectProvider(String string) throws InterruptedException {
        clickFindBy(providerButton);
        inpurProvider.sendKeys(string);
        Thread.sleep(3000);
        selectProvider.click();
        selectProviderButton.click();
    }

    public void createDate() {
        datePlannedPublication.sendKeys(formatDate(false));
        datePerformanceContract.sendKeys(formatDate(false));
        dateOfPayment.sendKeys(formatDate(false));
    }

    public void saveLot() {
        saveLot.click();
    }

    public String setSendToGRBS() {
        wait.until(ExpectedConditions.elementToBeClickable(sendToGRBS));
        sendToGRBS.click();
        yes_sendToGRBS.click();
        return driver.getCurrentUrl();
    }

    public CreateNewLot setApprove() throws InterruptedException {
        Thread.sleep(3000);
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(approve));
        approve.click();
        Thread.sleep(3000);
        wait.until(ExpectedConditions.elementToBeClickable(yes_approve));
        yes_approve.click();
        return this;
    }

    public CreateNewLot setReconcile() throws InterruptedException {
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(reconcile));
        reconcile.click();
        return this;
    }
}