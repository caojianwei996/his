package learn.caojw.his.medicine.service;

import java.math.BigDecimal;

public interface ISaleService {
    void sale(Long id, BigDecimal number);

    void back(Long id, BigDecimal number);

    void replenish(Long id, BigDecimal number);
}
