package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import sample.View;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PageObject extends FunctionalTest {
    protected WebDriverWait wait;
    protected Select select;
    private Date date;
    private SimpleDateFormat simpleDateFormat;
    private Calendar calendar;
    private String customer_file = "C:/test/Customer.txt";
    private String nl = System.getProperty("line.separator");
    private File file = new File(customer_file);

    /**
     * PageFactory использует ленивую инициализацию элементов страницы, осуществляемую при помощи вызова driver.findElement.
     * Вызов driver.findElement будет происходить каждый раз и только в момент обращения к элементу в тесте.
     */
    public PageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
        FunctionalTest.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    protected void setSelect(WebElement element) {
        select = new Select(element);
    }

    /**
     * @param format - формат возвращаемой текущей двты
     * @param amount - количество месяцев прибавленных к текущей дате.
     */
    protected final String time(String format, int amount) {
        date = new Date();
        simpleDateFormat = new SimpleDateFormat(format);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, amount);
        return (amount == 0) ? simpleDateFormat.format(date) : simpleDateFormat.format(calendar.getTime());
    }

    protected final String formatDate(boolean bool) {
        String string = String.valueOf(View.getDatePicker().getValue());
        String[] stringParts = string.split("-");
        String year = stringParts[0], mount = stringParts[1], day = stringParts[2];
        return (!bool) ? day + "." + mount + "." + year : mount;
    }

    protected void clickFindBy(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
    }

    protected void clickBy(By by) {
        driver.findElement(by).click();
    }

    protected boolean loadingPage() {
        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");//interactive
    }

    protected void loadingAjax() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) driver -> (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0"));
    }

    protected void waiting(WebElement webElement) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            if (webElement.getText().length() == 0) {
                Thread.sleep(500);
            } else
                break;
        }
    }

    private boolean compare(String res, WebElement value) throws InterruptedException {
        for (int i = 0; i < 10; i++)
            if (!res.equals(value.getText()))
                Thread.sleep(500);
            else
                break;
        return true;
    }

    protected boolean searchHashMap(long key, WebElement value) throws IOException, InterruptedException {
        HashMap<Long, String> hashMap;
        Path path = FileSystems.getDefault().getPath(customer_file);
        hashMap = (HashMap<Long, String>) Files.lines(path)
                .filter(s -> s.matches("^\\d+,\\D+"))
                .collect(Collectors.toMap(k -> Long.parseLong(k.split(",")[0]), v -> v.split(",")[1]));
        //Если требуемого заказчика нет в hashmap, то записываем его туда.
        if (hashMap.get(key) == null) {
            writeCustomer(key, value);
            return true;
        }
        //Если требуемый заказчик не равен текущему, то ожидаем.
        if (!hashMap.get(key).equals(value.getText()))
            compare(hashMap.get(key), value);
        return true;
    }

    protected boolean readCustomer(long key, String value) throws IOException {
        HashMap<Long, String> hashMap;
        Path path = FileSystems.getDefault().getPath(customer_file);
        hashMap = (HashMap<Long, String>) Files.lines(path)
                .filter(s -> s.matches("^\\d+,\\D+"))
                .collect(Collectors.toMap(k -> Long.parseLong(k.split(",")[0]), v -> v.split(",")[1]));
        if (hashMap.get(key) == null)
            return false;
        return hashMap.get(key).equals(value);
    }

    private void writeCustomer(long key, WebElement value) throws InterruptedException, IOException {
        HashMap<Long, String> hash = new HashMap<>();
        Thread.sleep(5000);
        hash.put(key, value.getText());
        FileWriter fileWriter = new FileWriter(file, true);
        for (Long p : hash.keySet())
            fileWriter.write(p + "," + hash.get(p) + nl);
        fileWriter.close();
    }

    protected class Pair<U, V> {
        private U first;
        private V second;

        public Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        U getFirst() {
            return first;
        }

        V getSecond() {
            return second;
        }
    }

    public void waitForPageLoad() {
        Wait<WebDriver> wait = new WebDriverWait(driver, 30);
        wait.until(driver -> {
            System.out.println("Current Window State       : " + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
            return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")).equals("complete");
        });
    }
}