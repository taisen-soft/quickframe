package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSUserGroup;
import cn.com.frame.service.UserGroupManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/usergroupController")
public class UserGroupManagerController extends BaseController{
    @Autowired
    public void setService(UserGroupManagerService service){
        super.service = service;
    }
    public UserGroupManagerController() {
        commonInstance = new SfSUserGroup();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUserGroup");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUserGroup");
    }
}
