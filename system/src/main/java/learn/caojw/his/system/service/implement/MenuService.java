package learn.caojw.his.system.service.implement;

import learn.caojw.his.common.interceptor.AuthorityInterceptor;
import learn.caojw.his.system.entity.Menu;
import learn.caojw.his.system.repository.MenuRepository;
import learn.caojw.his.system.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

/**
 * 菜单业务层实现版本1
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {
    private final MenuRepository menuRepository;

    @Override
    public void insert(Menu menu) {
        menuRepository.insert(menu);
    }

    @Override
    public void delete(String id) {
        menuRepository.deleteById(id);
    }

    @Override
    public void update(Menu menu) {
        menuRepository.updateById(menu);
    }

    @Override
    public Collection<Menu> select() {
        String authority = AuthorityInterceptor.getAuthority();
        if (authority.isBlank()) {
            return Collections.emptyList();
        } else if (authority.equals("ADMIN")) {
            return menuRepository.selectAll();
        } else {
            return menuRepository.selectByRole(authority);
        }
    }
}
