package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfBCommon;
import cn.com.frame.service.CommonManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/commonController")
public class CommonManagerController extends BaseController{
    @Autowired
    public void setService(CommonManagerService service){
        super.service = service;
    }
    public CommonManagerController() {
        commonInstance = new SfBCommon();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfBCommon");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfBCommon");
    }

}
