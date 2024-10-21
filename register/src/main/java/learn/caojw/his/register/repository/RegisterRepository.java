package learn.caojw.his.register.repository;

import learn.caojw.his.register.entity.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

/**
 * 挂号持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface RegisterRepository {
    void insert(Register register);

    void update(Register register);

    Collection<Register> select();

    Collection<Register> selectAll();

    Register selectById(Long id);

    Collection<Register> selectByName(String name);

    Collection<Register> selectByEmployee(String employee);
}
