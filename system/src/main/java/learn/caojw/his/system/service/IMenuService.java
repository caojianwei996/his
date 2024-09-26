package learn.caojw.his.system.service;

import learn.caojw.his.system.entity.Menu;

import java.util.Collection;

public interface IMenuService {
    void insert(Menu menu);

    void delete(String id);

    void update(Menu menu);

    Collection<Menu> select();
}
