package learn.caojw.his.wallet.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import learn.caojw.his.common.exception.ServiceException;
import learn.caojw.his.common.interceptor.AuthorityInterceptor;
import learn.caojw.his.wallet.entity.Record;
import learn.caojw.his.wallet.entity.Wallet;
import learn.caojw.his.wallet.repository.RecordRepository;
import learn.caojw.his.wallet.repository.WalletRepository;
import learn.caojw.his.wallet.service.IWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * 钱包业务层实现版本1
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class WalletService implements IWalletService {
    private final WalletRepository walletRepository;
    private final RecordRepository recordRepository;

    @Override
    public void insert(Wallet wallet) {
        walletRepository.insert(wallet);
    }

    @Override
    public void delete(String id) {
        walletRepository.deleteById(id);
    }

    @Override
    public Wallet select() {
        return walletRepository.selectOne(Wrappers.<Wallet>lambdaQuery().eq(Wallet::getOwner, AuthorityInterceptor.getUsername()));
    }

    @Transactional
    @Override
    public void consume(BigDecimal money) {
        Wallet wallet = select();
        if (wallet == null) {
            throw new ServiceException("钱包未开通");
        } else if (wallet.getMoney().compareTo(money) < 0) {
            throw new ServiceException("钱包余额不足");
        } else {
            Record record = new Record();
            record.setWallet(wallet.getId());
            record.setSpend(money);
            record.setBefore(wallet.getMoney());
            record.setAfter(wallet.getMoney().subtract(money));
            record.setTime(OffsetDateTime.now());
            recordRepository.insert(record);
            wallet.setMoney(wallet.getMoney().subtract(money));
            walletRepository.updateById(wallet);
        }
    }

    @Transactional
    @Override
    public void recharge(BigDecimal money) {
        Wallet wallet = select();
        if (wallet == null) {
            throw new ServiceException("钱包未开通");
        } else {
            Record record = new Record();
            record.setWallet(wallet.getId());
            record.setSpend(money);
            record.setBefore(wallet.getMoney());
            record.setAfter(wallet.getMoney().add(money));
            record.setTime(OffsetDateTime.now());
            recordRepository.insert(record);
            wallet.setMoney(wallet.getMoney().add(money));
            walletRepository.updateById(wallet);
        }
    }

    @Override
    public Collection<Record> selectRecord(int page, int size) {
        if (AuthorityInterceptor.getAuthority().equals("ADMIN")) {
            return recordRepository.selectPage(new Page<>(page, size), null).getRecords();
        } else {
            return recordRepository.selectPage(new Page<>(page, size), Wrappers.<Record>lambdaQuery().eq(Record::getWallet, AuthorityInterceptor.getUsername())).getRecords();
        }
    }
}
