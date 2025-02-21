package learn.caojw.his.register.service;

import learn.caojw.his.register.entity.Register;

import java.util.Collection;

/**
 * 挂号业务层
 *
 * @author 曹健伟
 */
public interface IRegisterService {
    void insert(Register register);

    void update(Register register);

    Collection<Register> select();

    Register selectById(Long id);

    Collection<Register> selectByName(String name);

    Collection<Register> selectByEmployee();
}
