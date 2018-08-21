package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.PageObject;

public class PurchasePlanPage extends PageObject {

    public PurchasePlanPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Кнопка отправить на согласование
     */
    @FindBy(xpath = "//*[@id=\"purchase-plans-new\"]/form/div/div[1]/input")
    private WebElement sendForApproval;

    /**
     * Статус Плана закупок
     */
    @FindBy(xpath = "//*[@id=\"purchase-plans-show\"]/fieldset[1]/div[1]/div[1]/div[3]/div/b")
    private static WebElement state;

    /**
     * Кнопка "отозвать с согласования"
     */
    @FindBy(xpath = "//*[@id=\"purchase-plans-show\"]/div[2]/div[1]/a[3]")
    private static WebElement recall;

    /**
     * Кнопка утверждения
     */
    @FindBy(css = "#purchase-plans-show > div.controls.fixed-panel.shadow.mb_15.fullwidth > div.pull-left > a.btn.green-btn.ng-scope")
    private WebElement approveButton;

    /**
     * Кнопка опубликовать
     */
    @FindBy(css = "#purchase-plans-show > div.controls.fixed-panel.shadow.mb_15.fullwidth > div.pull-left > a.btn.green-btn.ng-scope")
    private WebElement publishButton;

    public void sendForApproval() {
        sendForApproval.click();
    }

    public PurchasePlanPage setApproveButton() {
        if (!state.getText().equals("Согласовано")) {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#purchase-plans-show > div.controls.fixed-panel.shadow.mb_15.fullwidth > div.pull-left > a.btn.green-btn.ng-scope")));
            clickFindBy(approveButton);
            System.out.println(driver.getCurrentUrl() + " ");
        } else
            approveButton.click();
        return this;
    }

    public PurchasePlanPage publish() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"purchase-plans-show\"]/div[2]/div[1]/a[3]")));
        clickFindBy(publishButton);
        for (int i = 0; i < 20; i++) {
            Thread.sleep(1500);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"purchase-plans-show\"]/fieldset[1]/div[1]/div[1]/div[3]/div/b")));
            if ("Опубликован".equals(state.getText()))
                break;
            clickFindBy(publishButton);
        }
        System.out.println("Публикация ПЗ: ");
        return this;
    }
}