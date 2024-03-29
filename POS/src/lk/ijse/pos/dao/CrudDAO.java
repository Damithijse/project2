package lk.ijse.pos.dao;

import lk.ijse.pos.entity.SuperEntity;

import java.util.List;

public interface CrudDAO<T extends SuperEntity, ID> {
    public boolean save(T t) throws Exception;

    public boolean update(T t) throws Exception;

    public boolean delete(ID id) throws Exception;

    public T get(ID id);

    public List<T> getAll() throws Exception;
}
