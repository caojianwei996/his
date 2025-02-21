package learn.caojw.his.wallet.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 钱包实体
 *
 * @author 曹健伟
 */
@TableName("wallets")
@Data
public class Wallet {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String owner;
    private BigDecimal money;
}