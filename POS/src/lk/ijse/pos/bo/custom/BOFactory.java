package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.custom.impl.CustomerBoImpl;

public class BOFactory {
    public static BOFactory boFactory;

    public BOFactory() {
    }

    public static BOFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BOFactory()) : (boFactory);
    }

    public <T> T getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            default:
                return null;
        }
    }
}
