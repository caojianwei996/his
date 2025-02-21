package learn.caojw.his.dispose.service;

import learn.caojw.his.dispose.entity.Dispose;

import java.util.Collection;

public interface IDisposeService {
    void insert(Dispose dispose);

    void delete(Long id);

    Collection<Dispose> selectAll(Integer page, Integer size);

    Collection<Dispose> selectByCode(Integer page, Integer size, String code);
}
