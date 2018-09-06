package pages;

import helper.PurchasePlanStatus;
import helper.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PurchasePlanPage extends PageObject {
    private final String string = "http://eaist2-f.mos.ru/index.html#/purchase-plan/purchase-plans/new";

    PurchasePlanPage(WebDriver driver) {
        super(driver);
    }

    //Кнопка отправить на согласование
    @FindBy(xpath = "//*[@id=\"purchase-plans-new\"]/form/div/div[1]/input")
    private WebElement sendForApproval;

    //Статус Плана закупок
    @FindBy(xpath = "//*[@id=\"purchase-plans-show\"]/fieldset[1]/div[1]/div[1]/div[3]/div/b")
    private static WebElement state;

    //Кнопка "отозвать с согласования"
    @FindBy(xpath = "//*[@id=\"purchase-plans-show\"]/div[2]/div[1]/a[3]")
    private static WebElement recall;

    //Кнопка утверждения
    @FindBy(css = "#purchase-plans-show > div.controls.fixed-panel.shadow.mb_15.fullwidth > div.pull-left > a.btn.green-btn.ng-scope")
    private WebElement approveButton;

    //Кнопка опубликовать
    @FindBy(css = "#purchase-plans-show > div.controls.fixed-panel.shadow.mb_15.fullwidth > div.pull-left > a.btn.green-btn.ng-scope")
    private WebElement publishButton;

    public void sendForApproval() throws InterruptedException {
        sendForApproval.click();
        Thread.sleep(3000);
        if (driver.getCurrentUrl().equals(string))
            sendForApproval();
    }

    public String setApproveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(approveButton));
        approveButton.click();
        return driver.getCurrentUrl();
    }

    public void publish() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"purchase-plans-show\"]/div[2]/div[1]/a[3]")));
        clickFindBy(publishButton);
        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"purchase-plans-show\"]/fieldset[1]/div[1]/div[1]/div[3]/div/b")));
            if (PurchasePlanStatus.PUBLISHED.getName().equals(state.getText()))
                break;
            publish();
        }
    }
}