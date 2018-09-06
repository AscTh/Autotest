package sample;

import com.jfoenix.controls.*;

public class View {
    private static JFXButton create_chain;
    private static JFXButton publish_plans;
    private static JFXButton stop;
    private static JFXTextField login;
    private static JFXPasswordField password;
    private static JFXDatePicker datePicker;
    private static JFXToggleButton default_button;

    public static JFXToggleButton getDefault_button() {
        return default_button;
    }

    static void setDefault_button(JFXToggleButton default_button) {
        View.default_button = default_button;
    }

    public static JFXDatePicker getDatePicker() {
        return datePicker;
    }

    static void setDatePicker(JFXDatePicker datePicker) {
        View.datePicker = datePicker;
    }

    public static JFXTextField getLogin() {
        return login;
    }

    static void setLogin(JFXTextField login) {
        View.login = login;
    }

    public static JFXPasswordField getPassword() {
        return password;
    }

    static void setPassword(JFXPasswordField password) {
        View.password = password;
    }

    private static JFXTextArea result_console;

    public static JFXButton getCreate_chain() {
        return create_chain;
    }

    static void setCreate_chain(JFXButton create_chain) {
        View.create_chain = create_chain;
    }

    public static JFXButton getPublish_plans() {
        return publish_plans;
    }

    static void setPublish_plans(JFXButton publish_plans) {
        View.publish_plans = publish_plans;
    }

    public static JFXButton getStop() {
        return stop;
    }

    static void setStop(JFXButton stop) {
        View.stop = stop;
    }

    public static JFXTextArea getResult_console() {
        return result_console;
    }

    static void setResult_console(JFXTextArea result_console) {
        View.result_console = result_console;
    }
}
