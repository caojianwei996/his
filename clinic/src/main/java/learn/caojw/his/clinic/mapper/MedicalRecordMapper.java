package learn.caojw.his.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.clinic.entity.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicalRecordMapper extends BaseMapper<MedicalRecord> {
}