package learn.caojw.his.medicine.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.medicine.entity.Medicine;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicineRepository extends BaseMapper<Medicine> {
}
