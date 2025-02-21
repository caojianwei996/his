package learn.caojw.his.register.mybatis;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 适用于BIGINT与Boolean[]的MyBatis类型处理器
 *
 * @author 曹健伟
 */
@MappedJdbcTypes(JdbcType.BIGINT)
@MappedTypes(Boolean[].class)
public class ScheduleTypeHandler extends BaseTypeHandler<Boolean[]> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setLong(i, decode(parameter));
    }

    @Override
    public Boolean[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return encode(rs.getLong(columnName));
    }

    @Override
    public Boolean[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return encode(rs.getLong(columnIndex));
    }

    @Override
    public Boolean[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return encode(cs.getLong(columnIndex));
    }

    private Boolean[] encode(long data) {
        Boolean[] result = new Boolean[10];
        for (int j = 10; j > 0; j--) {
            result[j - 1] = (data & 1) == 1;
            data = data >> 1;
        }
        return result;
    }

    private long decode(Boolean[] data) {
        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = result << 1 | (data[i] ? 1 : 0);
        }
        return result;
    }
}
