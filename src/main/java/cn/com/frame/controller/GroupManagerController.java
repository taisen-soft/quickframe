package cn.com.frame.controller;

import cn.com.frame.common.tools.reflect.CommonReflect;
import cn.com.frame.dto.TreeDto;
import cn.com.frame.model.SfSGroup;
import cn.com.frame.service.GroupManagerService;
import cn.com.frame.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengbin on 2017/4/13.
 */
@Controller
@RequestMapping("/groupController")
public class GroupManagerController extends BaseController {
    @Autowired
    public void setService(GroupManagerService service) {
        super.service = service;
    }

    public GroupManagerController() {
        commonInstance = new SfSGroup();
        commonMainReflect = new CommonReflect();
        commonMainReflect
                .setTableName( "cn.com.frame.model.SfSGroup" );
        commonReflect = new CommonReflect();
        commonReflect.setTableName( "cn.com.frame.model.SfSGroup" );
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.POST, produces = "application/json")
    public List<TreeDto> handler(HttpServletRequest request) {
        List<SfSGroup> sfSGroupList = new ArrayList<SfSGroup>();
        Map<String, Object> map = new HashMap<>();
        try {
            map = super.list( request );
            sfSGroupList = (List<SfSGroup>) map.get( "list" );
            List<TreeDto> treeList = new ArrayList<TreeDto>();
            for (SfSGroup sfSGroup : sfSGroupList) {
                TreeDto tree = new TreeDto();
                tree.setId( sfSGroup.getId() );
                tree.setName( sfSGroup.getA10() );
                tree.setpId( sfSGroup.getA02() );
                tree.setDescribe( sfSGroup.getA03() );
                tree.setIcon( sfSGroup.getA04() );
                tree.setIconOpen(sfSGroup.getA05());
                tree.setIconClose( sfSGroup.getA06() );
                treeList.add( tree );
            }
            List<TreeDto> childrenList = new ArrayList<TreeDto>();
            List<TreeDto> d = TreeUtils.getAllChildrenList( childrenList, treeList, "0" );
            return d;

        } catch (Exception e) {
            return null ;
        }

    }
}
