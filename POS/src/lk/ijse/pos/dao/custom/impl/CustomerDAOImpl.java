package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudUtil;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer t) throws Exception {
        return CrudUtil.execute("INSERT INTO Customer VALUES(?,?,?,?)", t.getId(), t.getName(), t.getAddress(), t.getSalary());
    }

    @Override
    public boolean update(Customer t) throws Exception {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?", t.getName(), t.getAddress(), t.getSalary(), t.getId());

    }

    @Override
    public boolean delete(String id) throws Exception {

        return CrudUtil.execute("DELETE FROM customer WHERE id=?", id);
    }

    @Override
    public Customer get(String id) {
        return null;
    }

    @Override
    public List<Customer> getAll() throws Exception {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM Customer");
        List<Customer> list = new ArrayList<>();
        while (resultSet.next()) {
            list.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            ));
        }
        return list;
    }
}
