package learn.caojw.his.medicine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.medicine.entity.Medicine;
import org.apache.ibatis.annotations.Mapper;

/**
 * 药品持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface MedicineRepository extends BaseMapper<Medicine> {
}
