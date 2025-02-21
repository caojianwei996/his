package learn.caojw.his.dispose.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.util.PinyinUtil;
import learn.caojw.his.dispose.entity.Dispose;
import learn.caojw.his.dispose.repository.DisposeRepository;
import learn.caojw.his.dispose.service.IDisposeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class DisposeService implements IDisposeService {
    private final DisposeRepository disposeRepository;

    @Override
    public void insert(Dispose dispose) {
        dispose.setCode(PinyinUtil.toPinyin(dispose.getCode()));
        disposeRepository.insert(dispose);
    }

    @Override
    public void delete(Long id) {
        disposeRepository.deleteById(id);
    }

    @Override
    public Collection<Dispose> selectAll(Integer page, Integer size) {
        return disposeRepository.selectPage(new Page<>(page, size), null).getRecords();
    }

    @Override
    public Collection<Dispose> selectByCode(Integer page, Integer size, String code) {
        return disposeRepository.selectPage(new Page<>(page, size), Wrappers.<Dispose>lambdaQuery().eq(Dispose::getCode, code)).getRecords();
    }
}
