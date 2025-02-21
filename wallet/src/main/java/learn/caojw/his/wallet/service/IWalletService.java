package learn.caojw.his.wallet.service;

import learn.caojw.his.wallet.entity.Record;
import learn.caojw.his.wallet.entity.Wallet;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 钱包业务层
 *
 * @author 曹健伟
 */
public interface IWalletService {
    void insert(Wallet wallet);

    void delete(String id);

    Wallet select();

    void consume(BigDecimal money);

    void recharge(BigDecimal money);

    Collection<Record> selectRecord(int page, int size);
}
