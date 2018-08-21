package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import tests.PageObject;

import java.util.ArrayList;

public class ARM extends PageObject {

    ARM(WebDriver driver) {
        super(driver);
    }

    // Кнопка в АРМ ГРБС "Согласование"
    private By arm = By.xpath("//*[@id=\"plan-coordination-purchase-plan-show\"]/div[2]/div/div/div[1]/a[3]");


    // Кнопка в АРМ подтверждения согласования
    private By armY = By.xpath("//*[@id=\"approvement-confirmation-modal\"]/div/div/div[3]/button[2]");

    // Кнопка в АРМ подтверждения подтверждения
    private By armYY = By.cssSelector("#approvement-modal > div > div > div.modal-footer > div > div.pull-right > button.btn.green-btn");

    public void setArm(String link) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        driver.get(link);
        Thread.sleep(6000);
        clickBy(arm);
        Thread.sleep(2000);
        clickBy(armY);
        Thread.sleep(5000);
        clickBy(armYY);
        Thread.sleep(6000);
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }
}