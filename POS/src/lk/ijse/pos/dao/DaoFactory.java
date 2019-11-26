package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.CustomerDAOImpl;

public class DaoFactory {
    private static DaoFactory daoDactory;

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return (daoDactory == null) ?
                (daoDactory = new DaoFactory()) :
                (daoDactory);
    }

    public <T> T getDao(DaoType t) {
        switch (t) {
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            default:
                return null;
        }
    }

}
