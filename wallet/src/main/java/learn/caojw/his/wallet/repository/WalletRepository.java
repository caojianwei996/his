package learn.caojw.his.wallet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.wallet.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WalletRepository extends BaseMapper<Wallet> {
}
