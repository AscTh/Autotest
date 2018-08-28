package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import helper.PageObject;

public class CreateNewOZ extends PageObject {

    CreateNewOZ(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "pgzReason")
    private WebElement inputSubject;

    // Планируемый год размещения о закупке
    @FindBy(name = "plannedPublishYear")
    private WebElement selectYearOfPlacement;

    // КПГЗ
    @FindBy(id = "kpgzName")
    private WebElement selectKPGZ;

    // Поле ввода КПГЗ
    @FindBy(xpath = "//*[@id=\"kpgz-select-modal\"]/div/div/div[2]/div[1]/input")
    private WebElement nameKPGZ;

    // Кнопка закрытия модального окна КПГЗ
    @FindBy(xpath = "xpath=(//button[@type='button'])[17]")
    private WebElement webElement;

    @FindBy(css = "label > span.ng-binding")
    private WebElement idKPGZ;

    @FindBy(css = "div.modal-footer > button.btn.green-btn")
    private WebElement buttonSelectKPGZ;

    @FindBy(xpath = "//*[@id=\"purchase-financial-provision\"]/div[2]/div/div/div/a")
    private WebElement addFinancialSecurity;

    @FindBy(name = "limitPurchaseSum")
    private WebElement inputSum;

    @FindBy(xpath = "//*[@id=\"finance-limit-select\"]/div/div[2]/div[1]/div/div/div[1]/div[3]/label")
    private WebElement PFXD;

    @FindBy(xpath = "//*[@id=\"pfhd-table\"]/table/tbody/tr[2]/td[6]")
    private WebElement balance;

    @FindBy(xpath = "//*[@id=\"finance-limit-select\"]/div/div[3]/div[1]/button")
    private WebElement buttonChoose;

    @FindBy(name = "expectedResult")
    private WebElement expectedResult;

    @FindBy(name = "fullName")
    private WebElement fullName;

    @FindBy(name = "infoClaim7p2a17")
    private WebElement infoClaim;

    //Дата осуществления закупки
    @FindBy(xpath = "//*[@id=\"purchase-terms\"]/div/div/div/div/div/div[2]/div/div[2]/span/span/input")
    private WebElement data;

    //Сохранение ОЗ
    @FindBy(css = "#applicationPanel > div.row > div:nth-child(1) > button.btn.blue-btn.btn-size-34.btn-save")
    private WebElement saveOZ;

    // Утверждение ОЗ
    @FindBy(xpath = "//*[@id=\"applicationPanel\"]/div[4]/button[1]")
    private WebElement approveOZ;

    // Детализация ОЗ
    @FindBy(xpath = "//*[@id=\"applicationPanel\"]/div[4]/button[2]")
    private WebElement detailOZ;

    //Дата периодичности
    @FindBy(css = "#purchase-terms > div > div > div > div > div > div:nth-child(2) > div > div.col-md-3 > span > span > span")
    private WebElement srok;

    @FindBy(linkText = "янв")
    private WebElement january;
    @FindBy(linkText = "фев")
    private WebElement february;
    @FindBy(linkText = "мар")
    private WebElement march;
    @FindBy(linkText = "апр")
    private WebElement april;
    @FindBy(linkText = "май")
    private WebElement may;
    @FindBy(linkText = "июн")
    private WebElement june;
    @FindBy(linkText = "июл")
    private WebElement july;
    @FindBy(linkText = "авг")
    private WebElement august;
    @FindBy(linkText = "сен")
    private WebElement september;
    @FindBy(linkText = "окт")
    private WebElement october;
    @FindBy(linkText = "ноя")
    private WebElement november;
    @FindBy(linkText = "дек")
    private WebElement december;

    private void inputSrok() {
        srok.click();
        String mount = formatDate(true);
        switch (mount) {
            case "01":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("янв")));
                january.click();
                break;
            case "02":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("фев")));
                february.click();
                break;
            case "03":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("мар")));
                march.click();
                break;
            case "04":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("апр")));
                april.click();
                break;
            case "05":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("май")));
                may.click();
                break;
            case "06":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("июн")));
                june.click();
                break;
            case "07":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("июл")));
                july.click();
                break;
            case "08":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("авг")));
                august.click();
                break;
            case "09":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("сен")));
                september.click();
                break;
            case "10":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("окт")));
                october.click();
                break;
            case "11":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("ноя")));
                november.click();
                break;
            case "12":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("дек")));
                december.click();
                break;
        }
    }

    public void inputSubject(String str1) {
        inputSubject.sendKeys(str1);
        expectedResult.sendKeys(str1);
        infoClaim.sendKeys(str1);
        inputSrok();
        fullName.sendKeys(str1);
    }

    public void selectYearOfPlacement() {
        String year = formatDate(false).substring(6);
        setSelect(selectYearOfPlacement);
        select.selectByVisibleText(year);
    }

    //Выбор КПГЗ
    public void selectKPGZ(String code) throws InterruptedException {
        selectKPGZ.click();
        nameKPGZ.sendKeys(code);
        Thread.sleep(2000);
        idKPGZ.click();
        buttonSelectKPGZ.click();
    }

    //Финансовое обеспечение закупки
    public void addFinancialSecurity(String sum) {
        clickFindBy(addFinancialSecurity);
        inputSum.sendKeys(sum);
        PFXD.click();
        balance.click();
        buttonChoose.click();
    }

    public void saveOZ() {
        clickFindBy(saveOZ);
    }

    public Pair<String, CreateNewDOZ> getNewDOZ() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applicationPanel\"]/div[4]/button[1]")));
        clickFindBy(approveOZ);
        Thread.sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"applicationPanel\"]/div[4]/button[2]")));
        clickFindBy(detailOZ);
        return new Pair<>(driver.getCurrentUrl(), new CreateNewDOZ(driver));
    }
}