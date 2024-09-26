package learn.leejob.his.clinic.service.impl;


import learn.leejob.his.clinic.dao.CheckApplyMapper;
import learn.leejob.his.clinic.entity.CheckApply;
import learn.leejob.his.clinic.service.CheckApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckApplyServiceImpl implements CheckApplyService {
    private final CheckApplyMapper dao;

    @Override
    public void updateCheckApply(CheckApply checkApply) {
        dao.updateCheckApply(checkApply);
    }

    @Override
    public CheckApply getCheckApplyResult(Integer id) {
        return dao.getCheckApplyById(id);
    }
}
