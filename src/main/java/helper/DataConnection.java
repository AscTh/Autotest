package helper;

import java.sql.*;

public class DataConnection {
    public static void update(final int STATUS_ID, final int ENTITY_ID) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:oracle:thin:@//172.17.42.4:1521/eaistr";
        Class.forName("oracle.jdbc.OracleDriver");

        Connection connection = null;
        PreparedStatement statement, statement1;

        try {
            connection = DriverManager.getConnection(url, "EAIST_SHARD_1_3", "eaist");
            statement = connection.prepareStatement("UPDATE EAIST_SHARD_1_3 . D_PURCHASE_PLAN_VERSION\n" +
                    "SET STATUS_ID = ?\n" +
                    "WHERE ENTITY_ID = ?");
            statement.setInt(1, STATUS_ID);
            statement.setInt(2, ENTITY_ID);
            statement.executeUpdate();
            statement.close();
            statement1 = connection.prepareStatement("UPDATE EAIST_SHARD_1_3 . D_PURCHASE_PLAN_INDEX\n" +
                    "SET STATUSID = ?\n" +
                    "WHERE ID = ?");
            statement1.setInt(1, STATUS_ID);
            statement1.setInt(2, ENTITY_ID);
            statement1.executeUpdate();
            statement1.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null && connection.isClosed())
                connection.close();
        }
    }
}