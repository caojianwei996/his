package learn.caojw.his.system.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.system.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * 菜单持久层
 *
 * @author 曹健伟
 */
@Mapper
@Repository
public interface MenuRepository extends BaseMapper<Menu> {
    Collection<Menu> selectAll();

    Collection<Menu> selectByRole(String role);
}
