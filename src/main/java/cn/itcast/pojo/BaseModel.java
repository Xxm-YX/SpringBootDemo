package cn.itcast.pojo;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Transient;
import java.io.Serializable;

/**
 * 基础类
 *
 */
@Data

public class BaseModel implements Serializable {

    /**
     * 大小
     */
    @Transient
    private Integer size;

    /**
     * 页数
     */
    @Transient
    private Integer page;

    public Integer getPage() {
        return ObjectUtils.isEmpty(page) ? 1 : page;
    }

    public Integer getSize() {
        return ObjectUtils.isEmpty(size) ? 10 : size;
    }

    //    /**
//     * token
//     */
//    private String authorization;

    /**
     * 输入框查询参数
     */
    @Transient
    private String keyword;

    //错误码
    @Transient
    private Integer errcode;

    //错误信息
    @Transient
    private String errmsg;

    @Transient
    private String session_key;
}
