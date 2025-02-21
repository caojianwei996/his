package learn.caojw.his.register.service.implement;

import learn.caojw.his.register.entity.Level;
import learn.caojw.his.register.repository.LevelRepository;
import learn.caojw.his.register.service.ILevelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class LevelService implements ILevelService {
    private final LevelRepository levelRepository;

    @Override
    public Collection<Level> selectAll() {
        return levelRepository.selectList(null);
    }
}
