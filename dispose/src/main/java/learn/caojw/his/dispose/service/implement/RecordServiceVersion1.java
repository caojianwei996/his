package learn.caojw.his.dispose.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.seata.spring.annotation.GlobalTransactional;
import learn.caojw.his.common.api.WalletApi;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.dispose.entity.Dispose;
import learn.caojw.his.dispose.entity.Record;
import learn.caojw.his.dispose.repository.DisposeRepository;
import learn.caojw.his.dispose.repository.RecordRepository;
import learn.caojw.his.dispose.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class RecordServiceVersion1 implements RecordService {
    private final WalletApi walletApi;
    private final DisposeRepository disposeRepository;
    private final RecordRepository recordRepository;

    @GlobalTransactional
    @Override
    public void insert(Record record) {
        Dispose dispose = disposeRepository.selectById(record.getDispose());
        record.setState("已安排");
        if (dispose == null) {
            throw new ServiceException("未找到检查");
        } else {
            Response<Void> response = walletApi.consume(dispose.getMoney());
            if (response == null) {
                throw new ServiceException("");
            } else if (response.code() != 20000) {
                throw new ServiceException(response.message());
            } else {
                recordRepository.insert(record);
            }
        }
    }

    @Override
    public void update(Record record) {
        Record oldRecord = recordRepository.selectById(record.getId());
        Dispose inspect = disposeRepository.selectById(record.getDispose());
        if (oldRecord == null) {
            throw new ServiceException("未查询到挂号信息");
        } else if ("已安排".equals(oldRecord.getState())) {
            if ("已退费".equals(record.getState())) {
                Response<Void> response = walletApi.recharge(inspect.getMoney());
                if (response == null) {
                    throw new ServiceException("");
                } else if (response.code() != 20000) {
                    throw new ServiceException(response.message());
                } else {
                    oldRecord.setState(record.getState());
                    recordRepository.updateById(oldRecord);
                }
            } else if ("已接诊".equals(record.getState())) {
                oldRecord.setState(record.getState());
                recordRepository.updateById(record);
            } else {
                throw new ServiceException("非法状态转换");
            }
        } else if ("已接诊".equals(oldRecord.getState())) {
            if ("已完成".equals(record.getState())) {
                oldRecord.setState(record.getState());
                recordRepository.updateById(record);
            } else {
                throw new ServiceException("非法状态转换");
            }
        } else {
            throw new ServiceException("非法状态转换");
        }
    }

    @Override
    public Record selectById(Long id) {
        return recordRepository.selectOne(Wrappers.<Record>lambdaQuery().eq(Record::getId, id));
    }

    @Override
    public Collection<Record> selectAll() {
        return recordRepository.selectList(null);
    }
}