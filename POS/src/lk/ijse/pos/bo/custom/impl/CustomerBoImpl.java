package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DaoFactory;
import lk.ijse.pos.dao.DaoType;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDto;
import lk.ijse.pos.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {

    CustomerDAO dao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDto dto) throws Exception {
        return dao.save(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary()
        ));
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws Exception {
        return dao.update(new Customer(
                dto.getId(),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary()
        ));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return dao.delete(id);
    }

    @Override
    public List<CustomerDto> getAllCustomer() throws Exception {
        List<Customer> all = dao.getAll();
        List<CustomerDto> li = new ArrayList<>();

        for (Customer c : all) {
            li.add(new CustomerDto(
                    c.getId(),
                    c.getName(), c
                    .getAddress(),
                    c.getSalary()
            ));
        }
        return li;
    }
}
