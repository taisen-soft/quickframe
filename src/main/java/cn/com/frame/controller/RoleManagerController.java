package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.model.SfSRole;
import cn.com.frame.service.RoleManagerService;
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
@RequestMapping("/roleController")
public class RoleManagerController extends BaseController{
    @Autowired
    public void setService(RoleManagerService service){
        super.service = service;
    }
    public RoleManagerController() {
        commonInstance = new SfSRole();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSRole");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSRole");
    }


    /**
     * 增加或更新角色信息
     *
     * @return success : true/false
     *          instance
     * @Params request
     *          a01:_role  rolename:rolename  roleid:roleid  rolestate:rolestate
     */
    @ResponseBody
    @RequestMapping("/saveorupdate")
    public Map saveOrUpdateRole(HttpServletRequest request){
        Map result = new HashMap();
        result.put("success",false);
        try{
            result =  super.saveOrUpdate(request);
        }catch(Exception e) {
            return result;
        }
        return  result;
    }

    /**
     * 删除角色信息
     *
     * @return success : true/false
     *          instance
     * @Params request
     *          id:数据库ID或id的集合以逗号分开    condition sql语句where后面的部分
     */
    @ResponseBody
    @RequestMapping("/delete")
    public Map deleteRole(HttpServletRequest request){
        Map result = new HashMap();
        result.put("success",false);
        try{
            result = super.delete(request);
        }catch(Exception e){
            return result;
        }
        return result;
    }


    /**
     * 角色信息列表
     *
     * @return success : true/false
     *          list
     *          count
     * @Params request   base64加密
     *          start       nopage:true/false
     *          limit       condition:
     */
    @ResponseBody
    @RequestMapping("/list")
    public Map listRole(HttpServletRequest request){
        Map result = new HashMap();
        result.put("success",false);
        try{
            result = super.list(request);
        }catch(Exception e){
            return result;
        }
        return result;
    }

}
