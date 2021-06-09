package cn.itcast.web;

import cn.itcast.enumType.MemEnum;
import cn.itcast.pojo.ActionResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/zyx/hello")
    public ActionResult test(){

        ActionResult result = new ActionResult();

        Map<Object,Object> data = new HashMap<Object, Object>();

        data.put("1",MemEnum.values());
        result.setData(data);
        return result;
    }
}
