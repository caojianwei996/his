package learn.caojw.his.register.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import learn.caojw.his.register.entity.Department;
import learn.caojw.his.register.repository.DepartmentRepository;
import learn.caojw.his.register.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Collection<Department> selectAll() {
        return departmentRepository.selectList(null);
    }

    @Override
    public Collection<Department> selectByCode(String code) {
        return departmentRepository.selectList(Wrappers.<Department>lambdaQuery().eq(Department::getCode, code));
    }
}
