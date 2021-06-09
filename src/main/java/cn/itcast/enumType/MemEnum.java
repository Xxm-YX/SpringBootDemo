package cn.itcast.enumType;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@JSONType(serializeEnumAsJavaBean = true)
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MemEnum {
    GOLD_MEMBER(1,"黄金会员"),
    SILVER_MEMBER(2, "白银会员");

    private final Integer type;
    private final String desc;


    MemEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

//    protected abstract BigDecimal calculateFinalPrice(BigDecimal originPrice);


    public Integer getType() {
        return type;
    }


    public String getDesc() {
        return desc;
    }

    public static MemEnum getEnumByType(Integer type){
        MemEnum[]  values = MemEnum.values();
        for (MemEnum memberEnum : values) {
            if(memberEnum.getType().equals(type)){
                return memberEnum;
            }
        }
        throw new IllegalArgumentException("Invalid Enum type:" + type);
    }
}
