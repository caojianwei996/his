package learn.caojw.his.inspect.service;

import learn.caojw.his.inspect.entity.Inspect;

import java.util.Collection;

public interface InspectService {
    void insert(Inspect inspect);

    void delete(Long id);

    Collection<Inspect> selectAll(Integer page, Integer size);

    Collection<Inspect> selectByCode(Integer page, Integer size, String code);
}
