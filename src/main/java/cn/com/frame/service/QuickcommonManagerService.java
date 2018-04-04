package cn.com.frame.service;

import cn.com.frame.mapper.SfBQuickcommonMapper;
import cn.com.frame.model.SfBQuickcommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class QuickcommonManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBQuickcommonMapper mapper){
        super.mapper = mapper;
    }
    @Autowired
    public SfBQuickcommonMapper sfBQuickcommonMapper;

    public QuickcommonManagerService()
    {
        SfBQuickcommon sfBQuickcommon= new SfBQuickcommon();
        commonInstance = sfBQuickcommon;
    }

    public int addBatch(List<SfBQuickcommon> sfbs){
        return sfBQuickcommonMapper.addBatch(sfbs);
    }

    public List<SfBQuickcommon> selectPaperByCondition(String school,String grade,String className){
        return sfBQuickcommonMapper.selectPaperByCondition(school,grade,className);
    }

    public int zinsert(SfBQuickcommon sfBQuickcommon){
        return  sfBQuickcommonMapper.zinsert(sfBQuickcommon);
    };
    public int zupdate(SfBQuickcommon sfBQuickcommon){
        return sfBQuickcommonMapper.zupdate(sfBQuickcommon);
    };
}
