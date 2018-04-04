package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.common.tools.security.VerifyCodeUtils;
import cn.com.frame.common.util.ParamUtil;
import cn.com.frame.model.SfSUser;
import cn.com.frame.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Controller
@RequestMapping("userManager")
public class UserManagerController extends BaseController {
    @Autowired
    private UserManagerService userManagerService;
    @Autowired
    private UserRoleManagerService userRoleService;
    @Autowired
    private RolePermManagerService rolePermService;
    @Autowired
    private UserGroupManagerService userGroupService;
    @Autowired
    private RoleManagerService roleManagerService;

    @Autowired
    public void setService(UserManagerService service) {
        super.service = service;
    }

    public UserManagerController() {
        commonInstance = new SfSUser();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName("cn.com.frame.model.SfSUser");
        commonReflect = new CommonReflect();
        commonReflect.setTableName("cn.com.frame.model.SfSUser");
    }

    /*判断用户名是否重复   success为true 则重复*/
    @RequestMapping("/isDuplicateUsername")
    @ResponseBody
    public Map isDuplicateUsername(String username) {
        boolean success = userManagerService.isDuplicate(username);
        System.out.println("userManagerController中的判断用户名是否重复" + success);
        Map map = new HashMap();
        map.put("success", success);
        return map;
    }

    /*获取验证码*/
    @RequestMapping("/getVcode")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //生成随机字符串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession();
        //删除以前
        session.removeAttribute("TEMP_USER_CODE");
        session.setAttribute("TEMP_USER_CODE", verifyCode.toLowerCase());
        //生成图片
        int w = 150, h = 50;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /*用户注册  */
    @RequestMapping("/regedit")
    public Map register(String username, String password, String vcode, HttpServletRequest request) {
        Map map = new HashMap();
        boolean success = false;
        HttpSession session = request.getSession();
        String tempusercode = (String) session.getAttribute("TEMP_USER_CODE");
        if ((vcode.toLowerCase().equals(tempusercode)) || (vcode.toUpperCase().equals(tempusercode))) {
            success = userManagerService.register(username, password);
            map.put("success", success);
        } else {
            map.put("success", success);
        }
        return map;
    }

    /**
     * 验证用户的登录情况  username password 一致并且审核通过的用户可登陆
     * username        用户名
     * password     	密码
     * encode			密码是否进行md5加密。true加密，false或不填不加密
     * vcode			验证码
     */
    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, String encode, String vcode, HttpServletRequest request) {
        /*
        * success :  true/false*/
        Map map = new HashMap();
        boolean success = false;
        map.put("success", false);
        map.put("errorcode", 1);
        try {
          /*
          验证码的验证
          * */
            HttpSession session = request.getSession();
            String tempusercode = (String) session.getAttribute("TEMP_USER_CODE");
            if (!"".equals(vcode)) {
                if (vcode.toUpperCase().equals(tempusercode) || vcode.toLowerCase().equals(tempusercode)) {
                    success = true;
                } else {
                    map.put("errorcode", 2);
                    return map;
                }
            } else if (ParamUtil.LOGIN_CODER == 0) {
                map.put("errorcode", 2);
                return map;
            }

            if ("".equals(username) || "".equals(password)) {
                map.put("success", false);
                return map;
            }

            List<SfSUser> result = new ArrayList();
            SfSUser user = null;
            result = userManagerService.findByCondition("a01 = '_user' and a11 = '" + username + "' and a12 = '" + password + "' and a09 = '审核通过'", 0, 0, null);
            if (result.size() > 0) {
                user = (SfSUser) result.get(0);
                map.put("success", true);
                map.put("user", user);
                this.pushSessionByUser(user, request);
            } else {
                // 获取系统内置用户
                if (ParamUtil.SYS_USER_NAME != null && ParamUtil.SYS_USER_PASS != null) {
                    // 是内置用户
                    if (username.equals(ParamUtil.SYS_USER_NAME) && password.equals(ParamUtil.SYS_USER_PASS)) {
                        //创建实体
                        SfSUser admin = new SfSUser();
                        admin.setId(-1L);
                        admin.setUuid("-1");
                        admin.setA01("_user");
                        admin.setA10("内置管理员");
                        admin.setA11(ParamUtil.SYS_USER_NAME);
                        admin.setA12(ParamUtil.SYS_USER_PASS);
                        map.put("success", true);
                        map.put("user", admin);
                        this.pushSessionByUser(admin, request);
                        System.out.println("-------------内置用户登录成功-------------------");
                        return map;
                    }
                }
                map.put("success", false);
                map.put("errorcode", 1);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("errorcode", 0);
        return map;
    }

    /*根据用户设置session*/
    private Map pushSessionByUser(SfSUser user, HttpServletRequest request) {
        Map displayMap = new HashMap();
    /*SYS_USER              用户信息Map          	user
    SYS_USER_ID             用户id					user.id
    SYS_USER_UUID			用户uuid				user.uuid
    SYS_USER_USERNAME		用户名					user.a11
    SYS_USER_REALNAME		用户姓名				user.a10
    SYS_USER_SEX			用户性别				user.a13
    SYS_USER_USERLEVEL		用户级别				user.a14
    SYS_USER_TYPE			用户类型				user.a15
    SYS_USER_PHOTO          用户头像                user.a16
    */
        HttpSession session = request.getSession();
        displayMap = new HashMap();
        session.setAttribute("SYS_USER", user);
        displayMap.put("SYS_USER_ID", user.getId());
        session.setAttribute("SYS_USER_ID", user.getId());
        displayMap.put("SYS_USER_UUID", user.getUuid());
        session.setAttribute("SYS_USER_UUID", user.getUuid());
        displayMap.put("SYS_USER_USERNAME", user.getA11());
        session.setAttribute("SYS_USER_USERNAME", user.getA11());
        displayMap.put("SYS_USER_REALNAME", user.getA10());
        session.setAttribute("SYS_USER_REALNAME", user.getA10());
        displayMap.put("SYS_USER_SEX", user.getA13());
        session.setAttribute("SYS_USER_SEX", user.getA13());
        displayMap.put("SYS_USER_USERLEVEL", user.getA14());
        session.setAttribute("SYS_USER_USERLEVEL", user.getA14());
        displayMap.put("SYS_USER_STATUS", user.getA09());
        session.setAttribute("SYS_USER_STATUS", user.getA09());
        displayMap.put("SYS_USER_TYPE", user.getA15());
        session.setAttribute("SYS_USER_TYPE", user.getA15());
        displayMap.put("SYS_USER_PHOTO", user.getA16());
        session.setAttribute("SYS_USER_PHOTO", user.getA16());
        /*displayMap.put("SYS_USER_TYPE", user.getA20());
        session.setAttribute("SYS_USER_TYPE", user.getA20());*/
        // 保存该部门所有用户的uuid,用户可以查看该部门所有文档
        List<String> useruuids = userGroupService.saveAllGroupUser((String) user.getUuid());
        displayMap.put("SYS_SAMEORG_USERUUID", useruuids);
        session.setAttribute("SYS_SAMEORG_USERUUID", useruuids);

        //获取用户的角色uuid
        String roleuuids = userRoleService.getRoleuuidByUseruuid(user.getUuid());
        displayMap.put("SYS_USER_ROLEUUIDS", roleuuids);
        session.setAttribute("SYS_USER_ROLEUUIDS", roleuuids);

        //根据角色名称获取权限key  以逗号隔开
        String keys = rolePermService.getPermByUseruuid(roleuuids, "", "");
        displayMap.put("SYS_USER_PERMKEYS", keys);
        session.setAttribute("SYS_USER_PERMKEYS", keys);

        //获取所有用户的数据
        List<SfSUser> userList = userManagerService.findByCondition("id>0", 0, 0, null);
        Map userMap = new HashMap();
        for (SfSUser u : userList) {
            if (userMap.get(u.getUuid()) == null) {
                userMap.put(u.getUuid(), u);
            }
        }
        session.setAttribute("SYS_USERMAP", userMap);
        return displayMap;
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    @ResponseBody
    public Map logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        //用户
        session.removeAttribute("SYS_USER");
        session.removeAttribute("SYS_USER_ID");
        session.removeAttribute("SYS_USER_UUID");
        session.removeAttribute("SYS_USER_USERNAME");
        session.removeAttribute("SYS_USER_REALNAME");
        session.removeAttribute("SYS_USER_TYPE");
        session.removeAttribute("SYS_USER_SEX");
        session.removeAttribute("SYS_USER_USERLEVEL");
        session.removeAttribute("SYS_USER_PHOTO");

        session.removeAttribute("SYS_ACCESS");
        session.removeAttribute("SYS_ACCESS_JSON");
        session.removeAttribute("SYS_ROLE_NAME");
        session.removeAttribute("grantorid");
        session.removeAttribute("SYS_ORG_ID");
        session.removeAttribute("SYS_ORG_NAME");

        //session.getAttribute("TEMP_USER_CODE");
        session.removeAttribute("TEMP_USER_CODE");

        Map result = new HashMap();
        result.put("success", true);
        return result;
    }


    /**
     * 插入或更新用户组织机构
     *
     * @return success : true/false
     * @Params useruuid        用户uuid
     * username 		用户名
     * groupuuid		组织机构uuid
     * groupname		组织机构名
     * groupid			组织机构id
     */
    @ResponseBody
    @RequestMapping("/saveOrUpdateUserGroup")
    public Map saveOrUpdateUserGroup(String useruuid, String username, String groupuuid, String groupname, String groupid) {
        Map map = new HashMap();
        boolean success = userGroupService.saveOrUpdateUserGroup(useruuid, username, groupuuid, groupid, groupname);
        map.put("success", success);
        return map;
    }

    /**
     * 删除用户组织机构
     *
     * @return success : true/false
     * @Params String useruuid
     */
    @ResponseBody
    @RequestMapping("/deleteUserGroup")
    public Map deleteUserGroup(String useruuid) {
        Map map = new HashMap();
        boolean success = userGroupService.deleteUserGroup(useruuid);
        map.put("success", success);
        return map;
    }

    /**
     * 获取到相同组织机构的所有用户的uuid
     *
     * @Params String useruuid
     * @Return List: displayList
     */
    @ResponseBody
    @RequestMapping("/getSameGroupUseruuid")
    public Map getSameGroupUseruuid(String useruuid) {
        Map<String, Object> map = new HashMap();
        List<String> uuids = userGroupService.saveAllGroupUser(useruuid);
        map.put("displayList", uuids);
        return map;
    }
}
