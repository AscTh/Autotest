package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import helper.PageObject;

public class ListOZ extends PageObject {

    ListOZ(WebDriver driver) {
        super(driver);
    }

    //Кнопка добавить объект закупки
    @FindBy(xpath = "//a[contains(text(),'Добавить объект закупки')]")
    private WebElement addOZbutton;

    public CreateNewOZ getNewOZ() {
        addOZbutton.click();
        return new CreateNewOZ(driver);
    }
}