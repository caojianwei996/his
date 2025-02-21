package learn.caojw.his.inspect.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.util.PinyinUtil;
import learn.caojw.his.inspect.entity.Inspect;
import learn.caojw.his.inspect.repository.InspectRepository;
import learn.caojw.his.inspect.service.IInspectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class InspectService implements IInspectService {
    private final InspectRepository inspectRepository;

    @Override
    public void insert(Inspect inspect) {
        inspect.setCode(PinyinUtil.toPinyin(inspect.getCode()));
        inspectRepository.insert(inspect);
    }

    @Override
    public void delete(Long id) {
        inspectRepository.deleteById(id);
    }

    @Override
    public Collection<Inspect> selectAll(Integer page, Integer size) {
        return inspectRepository.selectPage(new Page<>(page, size), null).getRecords();
    }

    @Override
    public Collection<Inspect> selectByCode(Integer page, Integer size, String code) {
        return inspectRepository.selectPage(new Page<>(page, size), Wrappers.<Inspect>lambdaQuery().eq(Inspect::getCode, code)).getRecords();
    }
}
