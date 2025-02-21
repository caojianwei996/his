package learn.caojw.his.inspect.service.implement;

import learn.caojw.his.common.api.WalletApi;
import learn.caojw.his.common.entity.Response;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.inspect.entity.Inspect;
import learn.caojw.his.inspect.entity.Record;
import learn.caojw.his.inspect.repository.InspectRepository;
import learn.caojw.his.inspect.repository.RecordRepository;
import learn.caojw.his.inspect.service.IRecordService;
import lombok.RequiredArgsConstructor;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import java.util.Collection;

@RequiredArgsConstructor
@Service
public class RecordService implements IRecordService {
    private final WalletApi walletApi;
    private final InspectRepository inspectRepository;
    private final RecordRepository recordRepository;

    @GlobalTransactional
    @Override
    public void insert(Record record) {
        Inspect inspect = inspectRepository.selectById(record.getInspect());
        record.setState("已安排");
        if (inspect == null) {
            throw new ServiceException("未找到检查");
        } else {
            Response<Void> response = walletApi.consume(inspect.getMoney());
            if (response.code() != 20000) {
                throw new ServiceException(response.message());
            } else {
                recordRepository.insert(record);
            }
        }
    }

    @Override
    public void update(Record record) {
        Record oldRecord = recordRepository.selectById(record.getId());
        Inspect inspect = inspectRepository.selectById(record.getInspect());
        if (oldRecord == null) {
            throw new ServiceException("未查询到安排信息");
        } else if ("已安排".equals(oldRecord.getState())) {
            if ("已退费".equals(record.getState())) {
                Response<Void> response = walletApi.recharge(inspect.getMoney());
                if (response.code() != 20000) {
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
    public Collection<Record> selectAll() {
        return recordRepository.selectList(null);
    }

    @Override
    public Record selectById(Long id) {
        return recordRepository.selectById(id);
    }
}
