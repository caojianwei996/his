package learn.caojw.his.register.service;

import learn.caojw.his.register.entity.Department;

import java.util.Collection;

public interface IDepartmentService {
    Collection<Department> selectAll();

    Collection<Department> selectByCode(String code);
}
