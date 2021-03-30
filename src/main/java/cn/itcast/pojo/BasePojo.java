package cn.itcast.pojo;

import lombok.Getter;
import lombok.Setter;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.Column;
import java.util.Date;

@Getter
@Setter
public class BasePojo {

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否删除，数据库删除标志统一用deleted，所有POJO直接
     */
    @LogicDelete
    private Boolean deleted;
}
