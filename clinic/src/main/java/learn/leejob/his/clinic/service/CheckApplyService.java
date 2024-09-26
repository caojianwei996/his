package learn.leejob.his.clinic.service;

import learn.leejob.his.clinic.entity.CheckApply;

public interface CheckApplyService {
    void updateCheckApply(CheckApply checkApply);

    CheckApply getCheckApplyResult(Integer id);
}
