package learn.caojw.his.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Collection;

/**
 * 菜单实体
 *
 * @author 曹健伟
 */
@TableName("menus")
@Data
public class Menu {
    private String id;
    private String type;
    private String title;
    private String icon;
    private Long priority;
    private String parent;
    private String role;
    private Collection<Menu> children;
}