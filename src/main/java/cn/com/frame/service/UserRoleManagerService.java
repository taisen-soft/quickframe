package cn.com.frame.service;

import cn.com.frame.mapper.SfSRoleMapper;
import cn.com.frame.mapper.SfSUserRoleMapper;
import cn.com.frame.model.SfSRole;
import cn.com.frame.model.SfSUser;
import cn.com.frame.model.SfSUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王晨 on 2017/4/11.
 */
@Service
public class UserRoleManagerService extends BaseService {

    @Autowired
    public void setMapper(SfSUserRoleMapper mapper) {
        super.mapper = mapper;
    }
    public UserRoleManagerService() {
        SfSUserRole sfSUserRole = new SfSUserRole();
        commonInstance = sfSUserRole;
    }

    /*
    * 根据useruuid  获取角色uuid
    *
    * */
    public String getRoleuuidByUseruuid (String useruuid){
        String roleuuid = "";
        List list = new ArrayList();
        try{
            SfSUserRole userrole = null;
            list =  this.findByCondition("USERUUID = '"+useruuid+"'",0,0,"null");
            if(list.size()>0){
                for (int i = 0;i<list.size();i++){
                    userrole =(SfSUserRole)list.get(i);
                    roleuuid = roleuuid+","+userrole.getRoleuuid();
                }
            }else{
                return  roleuuid;
            }
        }catch(Exception e){
            return roleuuid;
        }
        return roleuuid;
    }

}
