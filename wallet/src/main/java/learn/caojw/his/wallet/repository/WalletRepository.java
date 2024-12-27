package learn.caojw.his.wallet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import learn.caojw.his.wallet.entity.Wallet;
import org.apache.ibatis.annotations.Mapper;

/**
 * 钱包持久层
 *
 * @author 曹健伟
 */
@Mapper
public interface WalletRepository extends BaseMapper<Wallet> {
}
