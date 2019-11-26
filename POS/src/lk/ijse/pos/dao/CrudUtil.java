package lk.ijse.pos.dao;

import lk.ijse.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtil {
    public static <T> T execute(String sql, Object... paramas) throws Exception {
        Connection c = DBConnection.getInstance().getConnection();
        PreparedStatement statement = c.prepareStatement(sql);
        for (int i = 0; i < paramas.length; i++) {
            statement.setObject((i + 1), paramas[i]);
        }
        return (sql.startsWith("SELECT")) ?
                (T) (statement.executeQuery()) :
                (T) ((Boolean) (statement.executeUpdate() > 0));
    }
}
