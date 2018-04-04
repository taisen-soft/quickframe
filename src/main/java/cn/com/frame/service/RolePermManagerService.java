package cn.com.frame.service;

import cn.com.frame.common.builder.Sys;
import cn.com.frame.mapper.SfSRolePermMapper;
import cn.com.frame.model.SfSRolePerm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class RolePermManagerService extends BaseService {
    @Autowired
    public void setMapper(SfSRolePermMapper mapper) {
        super.mapper = mapper;
    }

    public RolePermManagerService() {
        SfSRolePerm sfSRolePerm = new SfSRolePerm();
        commonInstance = sfSRolePerm;
    }

    /*
    * 根据user uuid查询用户的权限
    * */
    public String getPermByUseruuid(String roleuuid,String rolename,String roleid){
        String perm = "";
        List list = new ArrayList();
        SfSRolePerm rolePerm = null;
        try{
            if(Sys.getCheckTools().isParamNull(roleuuid)){
                if(roleuuid.contains(",")){
                    String[] uuids = roleuuid.split(",");
                    for(int i = 0;i<uuids.length;i++){
                        list = this.findByCondition("'a01 = "+ uuids[i]+"'",0,0,"null");
                        if(list.size()>0){
                            for(int  j = 0;j<list.size();j++){
                                rolePerm = (SfSRolePerm) list.get(j);
                                perm = perm + ","+rolePerm.getA10();
                            }
                        }
                    }
                    return perm;
                }else{
                    list = this.findByCondition("'a01 = "+ roleuuid+"'",0,0,"id");
                }
            }else if(Sys.getCheckTools().isParamNull(rolename)){
                list = this.findByCondition("'a02 = "+ rolename+"'",0,0,"id");
            }else if(Sys.getCheckTools().isParamNull(roleid)){
                list = this.findByCondition("'a03 = "+ roleid+"'",0,0,"id");
            }
            if(list.size()>0){
                for(int  j = 0;j<list.size();j++){
                    rolePerm = (SfSRolePerm) list.get(j);
                    perm = perm + ","+rolePerm.getA10();
                }
            }
        }catch(Exception e){
            return "";
        }
        return perm;
    }







}
