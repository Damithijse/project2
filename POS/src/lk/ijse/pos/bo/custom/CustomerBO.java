package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.CustomerDto;

import java.util.List;

public interface CustomerBO {
    public boolean saveCustomer(CustomerDto dto) throws Exception;

    public boolean updateCustomer(CustomerDto dto) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public List<CustomerDto> getAllCustomer() throws Exception;
}
