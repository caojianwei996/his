package learn.caojw.his.authorization.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.userdetails.User;

/**
 * 用户持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface UserRepository {
    @Select("select username, password, authority from users where username = #{username}")
    User selectByUsername(String username);
}
