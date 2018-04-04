package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSUserRole;
import cn.com.frame.service.UserRoleManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 王晨 on 2017/4/11.
 */
@Controller
@RequestMapping("/userRoleController")
public class UserRoleManagerController extends BaseController{
    @Autowired
    public void setService(UserRoleManagerService service){
        super.service = service;
    }
    public UserRoleManagerController() {
        commonInstance = new SfSUserRole();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUserRole");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUserRole");
    }

    /**
     * 插入或更新用户角色  更新或插入user_role表中相关的数据
     *
     * @Param         useruuid:用户uuid
     *                 roleuuid:角色uuid
     *                 username:用户名
     *                 roleid:角色id
     *                 a10：rolename
     * @Return success:  true/false
     *          instance
     *
     */
    @RequestMapping("/saveorupdate")
    @ResponseBody
    public Map configUserRole(HttpServletRequest request) {
        Map result = new HashMap();
        result.put("success",false);
        try{
            result = super.saveOrUpdate(request);
        }catch(Exception e){
            return result;
        }
        return result;
    }

    /**
     * 删除用户角色  删除user_role表中相关的数据
     *
     * @Param   id    condition
     * @Return success:  true/false
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map deleteUserRole(HttpServletRequest request) {
        Map result = new HashMap();
        result.put("success",false);
        try{
            result = super.saveOrUpdate(request);
        }catch(Exception e){
            return result;
        }
        return result;
    }

    /**
     * 用户角色列表  获取user_role表中相关的数据
     *
     * @Param   start  limit  nopage  condition
     * @Return success:  true/false
     *           limit   list
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map listUserRole(HttpServletRequest request) {
        Map result = new HashMap();
        result.put("success",false);
        try{
            result = super.saveOrUpdate(request);
        }catch(Exception e){
            return result;
        }
        return result;
    }


}
