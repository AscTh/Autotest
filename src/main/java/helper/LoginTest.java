package helper;

import pages.*;
import sample.View;

import java.io.IOException;
import java.sql.SQLException;

import static java.lang.System.currentTimeMillis;
import static helper.PageObject.print;

/**
 * @author Timur Magomedov
 */

public class LoginTest extends FunctionalTest {
    private final static String url = "http://eaist2-f.mos.ru/panel-new.html";
    private final static String CUSTOMER = "7701015505";
    private final static String VOLUME_OF_PURCHASE = "100";
    private final static String PURCHASE_AMOUNT = "1000";
    private final static String KPGZ = "01.01.01.01.02";
    private final static String FILE_PATH = "C:/test/Doc1.docx";
    private final static String CODE_FIAS = "107241";
    private final static String PROVIDER = "7710912452";
    private final static String LOGIN = View.getLogin().getText();
    private final static String PASSWORD = View.getPassword().getText();
    private final static String TYPE_CONTRACT = "Поставка товаров";
    private final static String DEFINITION_METHOD = "закупка у единственного поставщика";
    private final static String BASIS_CONCLUSION = "1. Закупка у субъектов естественных монополий";

    private static HomePage homePage;

    public static void create_chain() throws InterruptedException, IOException {
        before();

        long start = currentTimeMillis();

        driver.get(url);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputRegisterUser(LOGIN, PASSWORD);

        homePage = loginPage.getHomePage();

        print("Регистрация пользователя: " + ((currentTimeMillis() - start) / 1000) + "s\n", true);
        homePage.searchCustomer(CUSTOMER).selectYear();
        print("Выбор заказчика: " + ((currentTimeMillis() - start) / 1000) + "s\n", false);

        ListOZ listOZ = homePage.getListOZ();

        CreateNewOZ createNewOZ = listOZ.getNewOZ();
        print("Создание ОЗ: " + ((currentTimeMillis() - start) / 1000) + "s\n", false);
        createNewOZ.inputSubject(LOGIN);
        createNewOZ.selectYearOfPlacement();
        createNewOZ.selectKPGZ(KPGZ);
        createNewOZ.addFinancialSecurity(PURCHASE_AMOUNT);
        createNewOZ.saveOZ();
        print("Сохранение ОЗ: " + ((currentTimeMillis() - start) / 1000) + "s ", false);

        PageObject.Pair<String, CreateNewDOZ> pair_1 = createNewOZ.getNewDOZ();
        CreateNewDOZ createNewDOZ = pair_1.getSecond();
        print(pair_1.getFirst() + "\n" + "Создание ДОЗ: " + ((currentTimeMillis() - start) / 1000) + "s\n", false);
        createNewDOZ.addSpecification(VOLUME_OF_PURCHASE);
        createNewDOZ.inputFile(LOGIN, FILE_PATH);
        createNewDOZ.editDelivery();
        createNewDOZ.buttonSaveSpec();
        createNewDOZ.fiasIuput(CODE_FIAS);
        CalculationOfNMC calculationOfNMC = createNewDOZ.getCalculatorNMC();
        calculationOfNMC.calculation(PURCHASE_AMOUNT, LOGIN);
        createNewDOZ.saveDOZ();
        print("Сохранение ДОЗ: " + ((currentTimeMillis() - start) / 1000) + "s ", false);

        PageObject.Pair<String, ListPlans> pair = createNewDOZ.getApproveDoz();
        CreateNewLot lotPage = createNewDOZ.createLot();
        print(pair.getFirst() + "\n" + "Создание лота: " + ((currentTimeMillis() - start) / 1000) + "s\n", false);
        lotPage.selectTypeContract(TYPE_CONTRACT);
        lotPage.selectDefinitionMethod(DEFINITION_METHOD, BASIS_CONCLUSION).selectSMP();
        lotPage.inputJustificationOfMethod(LOGIN, FILE_PATH);
        lotPage.selectProvider(PROVIDER);
        lotPage.createDate();
        lotPage.saveLot();
        print("Сохранение лота: " + ((currentTimeMillis() - start) / 1000) + "s ", false);
        String string = lotPage.setSendToGRBS();
        print(string + "\n", false);
        lotPage.setApprove();
//        lotPage.setReconcile();
    }

    public static void publish_plans() throws InterruptedException, SQLException, ClassNotFoundException {
        long start = currentTimeMillis();

        homePage = new HomePage(driver);
        homePage.plans();

        ListPlans listPlans = new ListPlans(driver);

        PurchasePlanPage purchasePlanPage = listPlans.newPP();
        purchasePlanPage.sendForApproval();
        print("Отправка на согласование ПЗ: " + ((currentTimeMillis() - start) / 1000) + "s\n", false);

        listPlans.reconcile();
        listPlans.openNewPurschasePlan();

        String urlPP = purchasePlanPage.setApproveButton();
        purchasePlanPage.publish();
        print("Публикация ПЗ: " + ((currentTimeMillis() - start) / 1000) + "s " + urlPP, false);

        homePage.plans();
        ScheldulePlanPage scheldulePlanPage = listPlans.newSP();
        scheldulePlanPage.sendForApproval(PURCHASE_AMOUNT);
        listPlans.openNewScheldurePlun();
        scheldulePlanPage.setApproveButton().publish();
    }
}