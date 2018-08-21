package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import tests.PageObject;

public class ListOZ extends PageObject {

    ListOZ(WebDriver driver) {
        super(driver);
    }

    //Кнопка добавить объект закупки
    @FindBy(xpath = "//*[@id=\"purchase-objects-index\"]/div[1]/div[2]/a[1]")
    private WebElement addOZbutton;

    public CreateNewOZ getNewOZ() {
        clickFindBy(addOZbutton);
        return new CreateNewOZ(driver);
    }
}