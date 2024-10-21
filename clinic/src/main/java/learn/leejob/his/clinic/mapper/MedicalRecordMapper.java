package learn.leejob.his.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.leejob.his.clinic.entity.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MedicalRecordMapper extends BaseMapper<MedicalRecord> {
}