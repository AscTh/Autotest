package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tests.PageObject;

public class ScheldulePlanPage extends PageObject {

    ScheldulePlanPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Кнопка сохранить
     */
    @FindBy(css = "#plan-schedules-new > form > div > div.pull-left > input")
    private WebElement saveNewSP;

    /**
     * Поле "Годовой объем"
     */
    @FindBy(css = "#plan-schedules-new > form > fieldset:nth-child(2) > div:nth-child(3) > div.col-md-3 > p > input")
    private WebElement annualVolume;

    /**
     * Кнопка утверждения
     */
    @FindBy(css = "#plan-schedules-show > div.controls.fixed-panel.shadow.mb_20.fullwidth > div.pull-left > a:nth-child(3)")
    private WebElement approveButton;

    /**
     * Кнопка опубликовать
     */
    @FindBy(css = "#plan-schedules-show > div.controls.fixed-panel.shadow.mb_20.fullwidth > div.pull-left > a:nth-child(3)")
    private WebElement publishButton;

    /**
     * Статус Плана Графика
     */
    @FindBy(xpath = "//*[@id=\"plan-schedules-show\"]/fieldset[1]/div[1]/div[1]/div[5]/div/b")
    private static WebElement state;

    public ScheldulePlanPage sendForApproval(String string) {
        annualVolume.sendKeys(string);
        saveNewSP.click();
        return this;
    }

    public ScheldulePlanPage setApproveButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#plan-schedules-show > div.controls.fixed-panel.shadow.mb_20.fullwidth > div.pull-left > a:nth-child(3)")));
        approveButton.click();
        return this;
    }

    public ScheldulePlanPage publish() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"plan-schedules-show\"]/div[2]/div[1]/a[3]")));
        publishButton.click();
        for (int i = 0; i < 10; i++) {
            System.out.println("Publish");
            Thread.sleep(1500);
            if ("Опубликован".equals(state.getText()))
                break;
            clickFindBy(publishButton);
        }
        return this;
    }
}