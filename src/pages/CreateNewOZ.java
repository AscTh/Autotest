package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.PageObject;

public class CreateNewOZ extends PageObject {

    CreateNewOZ(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "pgzReason")
    private WebElement inputSubject;

    @FindBy(name = "plannedPublishYear")
    private WebElement selectYearOfPlacement;

    // КПГЗ
    @FindBy(id = "kpgzName")
    private WebElement selectKPGZ;

    // Поле ввода КПГЗ
    @FindBy(xpath = "//*[@id=\"kpgz-select-modal\"]/div/div/div[2]/div[1]/input")
    private WebElement nameKPGZ;

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

    @FindBy(xpath = "//a[@data-value='2018/0/1']")
    private WebElement january;
    @FindBy(xpath = "//a[@data-value='2018/1/1']")
    private WebElement february;
    @FindBy(xpath = "//a[@data-value='2018/2/1']")
    private WebElement march;
    @FindBy(xpath = "//a[@data-value='2018/3/1']")
    private WebElement april;
    @FindBy(xpath = "//a[@data-value='2018/4/1']")
    private WebElement may;
    @FindBy(xpath = "//a[@data-value='2018/5/1']")
    private WebElement june;
    @FindBy(xpath = "//a[@data-value='2018/6/1']")
    private WebElement july;
    @FindBy(xpath = "//a[@data-value='2018/7/1']")
    private WebElement august;
    @FindBy(xpath = "//a[@data-value='2018/8/1']")
    private WebElement september;
    @FindBy(xpath = "//a[@data-value='2018/9/1']")
    private WebElement october;
    @FindBy(xpath = "//a[@data-value='2018/10/1']")
    private WebElement november;
    @FindBy(xpath = "//a[@data-value='2018/11/1']")
    private WebElement december;

    private void inputSrok() {
        srok.click();
        String mount = formatDate(true);
        switch (mount) {
            case "01":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/0/1']")));
                january.click();
                break;
            case "02":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/1/1']")));
                february.click();
                break;
            case "03":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/2/1']")));
                march.click();
                break;
            case "04":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/3/1']")));
                april.click();
                break;
            case "05":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/4/1']")));
                may.click();
                break;
            case "06":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/5/1']")));
                june.click();
                break;
            case "07":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/6/1']")));
                july.click();
                break;
            case "08":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/7/1']")));
                august.click();
                break;
            case "09":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/8/1']")));
                september.click();
                break;
            case "10":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/9/1']")));
                october.click();
                break;
            case "11":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/10/1']")));
                november.click();
                break;
            case "12":
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-value='2018/11/1']")));
                december.click();
                break;
        }
    }

    public void inputSubject(String str1) {
        inputSubject.sendKeys(str1);
        expectedResult.sendKeys(str1);
        infoClaim.sendKeys(str1);
        inputSrok();
//        data.sendKeys(time("MM.yyyy", 5));
        fullName.sendKeys(str1);
    }

    public void selectYearOfPlacement(String string) {
        setSelect(selectYearOfPlacement);
        select.selectByVisibleText(string);
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