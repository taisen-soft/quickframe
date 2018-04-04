package cn.com.frame.service;

import cn.com.frame.mapper.SfBSmallcommonMapper;
import cn.com.frame.model.SfBSmallcommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class SmallcommonManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBSmallcommonMapper mapper) {
        super.mapper = mapper;
    }

    @Autowired
    public SfBSmallcommonMapper sfBSmallcommonMapper;

    public SmallcommonManagerService() {
        SfBSmallcommon sfBSmallcommon = new SfBSmallcommon();
        commonInstance = sfBSmallcommon;
    }

    public int addBatch(List<SfBSmallcommon> sfbs) {
        return sfBSmallcommonMapper.addBatch(sfbs);
    }

    public boolean saveOrUpdateSmallcommon(SfBSmallcommon sfBSmallcommon) {
        int result = 0;
        if (sfBSmallcommon.getId() == null) {
            result = sfBSmallcommonMapper.insert(sfBSmallcommon);
        } else {
            result = sfBSmallcommonMapper.updateByPrimaryKey(sfBSmallcommon);
        }
        return result > 0;
    }

}
