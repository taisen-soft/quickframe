package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSRolePerm;
import cn.com.frame.service.RolePermManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/rolepermController")
public class RolePermManagerController extends BaseController{
    @Autowired
    public void setService(RolePermManagerService service){
        super.service = service;
    }
    public RolePermManagerController() {
        commonInstance = new SfSRolePerm();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSRolePerm");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSRolePerm");
    }

    /**
     * 插入或更新用户权限  更新或插入role_perm表中相关的数据
     *
     * @Param         a01:角色uuid
     *                 a02:角色名
     *                 a03：角色id
     *
     *                 a10：key
     *                 a11：描述
     * @Return success:  true/false
     *          instance
     *
     */
    @RequestMapping("/saveorupdate")
    @ResponseBody
    public Map configRolePerm(HttpServletRequest request) {
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
     * 删除角色权限  删除role_perm表中相关的数据
     *
     * @Param   id    condition
     * @Return success:  true/false
     *
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Map deleteRolePerm(HttpServletRequest request) {
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
     * 角色权限列表  获取role_perm表中相关的数据
     *
     * @Param   start  limit  nopage  condition
     * @Return success:  true/false
     *           limit   list
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map listRolePerm(HttpServletRequest request) {
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
