package cn.com.frame.service;

import cn.com.frame.mapper.SfBCommonChildMapper;
import cn.com.frame.mapper.SfBCommonMapper;
import cn.com.frame.model.SfBCommon;
import cn.com.frame.model.SfBCommonChild;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 王晨 on 2017/4/13.
 */
@Service
public class CommonManagerService extends BaseService {
    @Autowired
    public void setMapper(SfBCommonMapper mapper) {
        super.mapper = mapper;
    }

    public CommonManagerService() {
        SfBCommon sfBCommon = new SfBCommon();
        commonInstance = sfBCommon;
    }
}
