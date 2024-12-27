package learn.caojw.his.authorization.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 适用于VARCHAR与Collection的MyBatis类型处理器
 *
 * @author 曹健伟
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(Collection.class)
public class UserTypeHandler extends BaseTypeHandler<List<? extends GrantedAuthority>> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<? extends GrantedAuthority> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.getFirst().getAuthority());
    }

    @Override
    public List<? extends GrantedAuthority> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return List.of(new SimpleGrantedAuthority(rs.getString(columnName)));
    }

    @Override
    public List<? extends GrantedAuthority> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return List.of(new SimpleGrantedAuthority(rs.getString(columnIndex)));
    }

    @Override
    public List<? extends GrantedAuthority> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return List.of(new SimpleGrantedAuthority(cs.getString(columnIndex)));
    }
}
