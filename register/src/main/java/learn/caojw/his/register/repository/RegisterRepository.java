package learn.caojw.his.register.repository;

import learn.caojw.his.register.entity.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface RegisterRepository {
    void insert(Register register);

    void update(Register register);

    Collection<Register> selectAll();

    Register selectById(Long id);

    Collection<Register> selectByName(String name);

    Collection<Register> selectByEmployee(String employee);
}
