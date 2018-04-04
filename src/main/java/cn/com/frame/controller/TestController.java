package cn.com.frame.controller;

import cn.com.frame.model.SfSGroup;
import cn.com.frame.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoer on 2017/8/10.
 */


@Controller
@RequestMapping("/test")
public class TestController{

    @Autowired
    private BaseService groupManagerService ;

    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.POST , produces="application/json")
    public List<SfSGroup> handler(){
        List<SfSGroup> sfSGroupList = new ArrayList<SfSGroup>(  );
        try{
            sfSGroupList =  groupManagerService.findByCondition("a01=_organiz_mechan",0,100,"desc") ;
        }catch(Exception e){

        }
        return sfSGroupList ;
    }
}
