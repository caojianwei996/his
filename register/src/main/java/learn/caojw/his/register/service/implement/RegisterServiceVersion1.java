package learn.caojw.his.register.service.implement;

import io.seata.spring.annotation.GlobalTransactional;
import learn.caojw.his.common.api.WalletApi;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.common.interceptor.AuthorityInterceptor;
import learn.caojw.his.register.entity.Register;
import learn.caojw.his.register.repository.RegisterRepository;
import learn.caojw.his.register.service.IRegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 挂号业务层实现版本1
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class RegisterServiceVersion1 implements IRegisterService {
    private final RegisterRepository registerRepository;
    private final WalletApi walletApi;

    @GlobalTransactional
    @Override
    public void insert(Register register) {
        Response<Void> response = walletApi.consume(register.getEmployee().getLevel().getFee());
        if (response == null) {
            throw new ServiceException("");
        } else if (response.code() != 20000) {
            throw new ServiceException(response.message());
        } else {
            registerRepository.insert(register);
        }
    }

    @GlobalTransactional
    @Override
    public void update(Register register) {
        Register oldRegister = registerRepository.selectById(register.getId());
        if (oldRegister == null) {
            throw new ServiceException("未查询到挂号信息");
        } else if ("已挂号".equals(oldRegister.getState())) {
            if ("已退费".equals(register.getState())) {
                Response<Void> response = walletApi.recharge(register.getEmployee().getLevel().getFee());
                if (response == null) {
                    throw new ServiceException("");
                } else if (response.code() != 20000) {
                    throw new ServiceException(response.message());
                } else {
                    registerRepository.update(register);
                }
            } else if ("已接诊".equals(register.getState())) {
                registerRepository.update(register);
            } else {
                throw new ServiceException("非法状态转换");
            }
        } else if ("已接诊".equals(oldRegister.getState())) {
            if ("已完成".equals(register.getState())) {
                registerRepository.update(register);
            } else {
                throw new ServiceException("非法状态转换");
            }
        } else {
            throw new ServiceException("非法状态转换");
        }
    }

    @Override
    public Collection<Register> selectAll() {
        return registerRepository.selectAll();
    }

    @Override
    public Register selectById(Long id) {
        return registerRepository.selectById(id);
    }

    @Override
    public Collection<Register> selectByName(String name) {
        return registerRepository.selectByName(name);
    }

    @Override
    public Collection<Register> selectByEmployee() {
        String username = AuthorityInterceptor.getUsername();
        if ("ADMIN".equals(username)) {
            return registerRepository.selectAll();
        } else {
            return registerRepository.selectByEmployee(username);
        }
    }
}
