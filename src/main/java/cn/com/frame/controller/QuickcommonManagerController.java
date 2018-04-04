package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBQuickcommon;
import cn.com.frame.service.QuickcommonManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/quickcommonController")
public class QuickcommonManagerController extends BaseController{
    @Autowired
    public void setService(QuickcommonManagerService service){
        super.service = service;
    }
    @Autowired
    public QuickcommonManagerService quickcommonManagerService;
    public QuickcommonManagerController() {
        commonInstance = new SfBQuickcommon();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBQuickcommon");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBQuickcommon");
    }
    @RequestMapping("addBatch")
    public Map addBatch(HttpServletRequest request){
        Map result = new HashMap();
        result.put("success",false);
        List list = new ArrayList<>();
        SfBQuickcommon sfb = null;
        for(int i = 0;i<=9;i++){
            sfb = new SfBQuickcommon();
            sfb.setUuid(UUID.randomUUID()+"");
            sfb.setA01("_test");
            sfb.setA10("test"+i);
            sfb.setA11("test"+i);
            sfb.setA12("test"+i);
            sfb.setA13("test"+i);
            sfb.setA14("test"+i);
            sfb.setOwner("test"+i);
            sfb.setCreateday(new Date());
            list.add(sfb);
        }
        int i = quickcommonManagerService.addBatch(list);
        if(i== list.size()){
            result.put("success",true);
        }
        return result;
    }
}
