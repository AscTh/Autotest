package tests;

import pages.*;
import sample.View;

import java.io.IOException;

import static java.lang.System.currentTimeMillis;

/**
 * @author Timur Magomedov
 */

public class LoginTest extends FunctionalTest {
    private final static String url = "http://eaist2-f.mos.ru/panel-new.html";
    private final static String ARM = "http://eaist2-f.mos.ru/arm-grbs.html#/plan-coordination/purchase-plan/coordination/";
    private final static String CUSTOMER = "7701015505";
    private final static String VOLUME_OF_PURCHASE = "100";
    private final static String PURCHASE_AMOUNT = "1000";
    private final static String KPGZ = "01.01.01.01.02";
    private final static String FILE_PATH = "C:/test/Doc1.docx";
    private final static String CODE_FIAS = "107241";
    private final static String YEAR = "2018";
    private final static String PROVIDER = "7710912452";
    private final static String TEXT = View.getLogin().getText();
    private final static String TYPE_CONTRACT = "Поставка товаров";
    private final static String DEFINITION_METHOD = "Закупка у единственного поставщика";

    private static HomePage homePage;

    public static void create_chain() throws InterruptedException, IOException {
        before();

        long start = currentTimeMillis();

        driver.get(url);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.inputRegisterUser(View.getLogin().getText(), View.getPassword().getText());

        homePage = loginPage.getHomePage();

        View.getResult_console().setText("Регистрация пользователя: " + ((currentTimeMillis() - start) / 1000) + "s\n");
        homePage.searchCustomer(CUSTOMER).selectYear(YEAR);
        View.getResult_console().appendText("Выбор заказчика: " + ((currentTimeMillis() - start) / 1000) + "s\n");

        ListOZ listOZ = homePage.getListOZ();

        CreateNewOZ createNewOZ = listOZ.getNewOZ();
        View.getResult_console().appendText("Создание ОЗ: " + ((currentTimeMillis() - start) / 1000) + "s\n");
        createNewOZ.inputSubject(TEXT);
        createNewOZ.selectYearOfPlacement(YEAR);
        createNewOZ.selectKPGZ(KPGZ);
        createNewOZ.addFinancialSecurity(PURCHASE_AMOUNT);
        createNewOZ.saveOZ();
        View.getResult_console().appendText("Сохранение ОЗ: " + ((currentTimeMillis() - start) / 1000) + "s ");

        PageObject.Pair<String, CreateNewDOZ> pair_1 = createNewOZ.getNewDOZ();
        CreateNewDOZ createNewDOZ = pair_1.getSecond();
        View.getResult_console().appendText(pair_1.getFirst() + "\n" + "Создание ДОЗ: " + ((currentTimeMillis() - start) / 1000) + "s\n");
        createNewDOZ.addSpecification(VOLUME_OF_PURCHASE);
        createNewDOZ.inputFile(TEXT, FILE_PATH);
        createNewDOZ.editDelivery();
        createNewDOZ.buttonSaveSpec();
        createNewDOZ.fiasIuput(CODE_FIAS);
        CalculationOfNMC calculationOfNMC = createNewDOZ.getCalculatorNMC();
        calculationOfNMC.calculation(PURCHASE_AMOUNT, TEXT);
        createNewDOZ.saveDOZ();
        View.getResult_console().appendText("Сохранение ДОЗ: " + ((currentTimeMillis() - start) / 1000) + "s ");

        PageObject.Pair<String, ListPlans> pair = createNewDOZ.getApproveDoz();
        CreateNewLot lotPage = createNewDOZ.createLot();
        View.getResult_console().appendText(pair.getFirst() + "\n" + "Создание лота: " + ((currentTimeMillis() - start) / 1000) + "s\n");
        lotPage.selectTypeContract(TYPE_CONTRACT);
        lotPage.selectDefinitionMethod(DEFINITION_METHOD).selectSMP();
        lotPage.inputJustificationOfMethod(TEXT, FILE_PATH);
        lotPage.selectProvider(PROVIDER);
        lotPage.createDate();
        lotPage.saveLot();
        View.getResult_console().appendText("Сохранение лота: " + ((currentTimeMillis() - start) / 1000) + "s ");
        String string = lotPage.setSendToGRBS();
        View.getResult_console().appendText(string + "\n");
        lotPage.setApprove();
//        lotPage.setReconcile();
    }

    public static void publish_plans() throws InterruptedException {
//        long start = currentTimeMillis();

        homePage = new HomePage(driver);
        homePage.plans();
        /*ListPlans listPlans = pair.getSecond();

        PurchasePlanPage purchasePlanPage = listPlans.newPP();
        purchasePlanPage.sendForApproval();
        View.getResult_console().appendText("Отправка на согласование ПЗ: " + ((currentTimeMillis() - start) / 1000) + "s ");

        PageObject.Pair<String, ARM> linkARM = listPlans.reconcile(ARM);
        ARM arm = linkARM.getSecond();
        arm.setArm(linkARM.getFirst());

        listPlans.openNewPurschasePlan();

        purchasePlanPage.setApproveButton().publish();
        out.println(((currentTimeMillis() - start) / 1000) + "s");

        driver.get(pair.getFirst());

        homePage.plans();
        ScheldulePlanPage scheldulePlanPage = listPlans.newSP();
        scheldulePlanPage.sendForApproval(PURCHASE_AMOUNT);
        listPlans.openNewScheldurePlun();
        scheldulePlanPage.setApproveButton().publish();*/
    }
}