package cn.itcast.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.LogicDelete;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tk_user")
public class TkUserPojo extends BasePojo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    //姓名
    private String name;

    private Integer age;
}
