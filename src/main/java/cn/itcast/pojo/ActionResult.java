package cn.itcast.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionResult extends BaseModel{

    /**
     * 状态码
     */
    private int code;

    /**
     * 返给前台数据包
     */
    private Object data;

    /**
     * 后台记录日志信息
     */
    @JsonIgnore
    private String logs;

    /**
     * 操作日志详情信息
     */
    @JsonIgnore
    private String logDescribe;

    public List<Object> getList(){
        return (List<Object>) this.getData();
    }
}
