package cn.com.frame.mapper;

import cn.com.frame.model.SfSUser;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SfSUserMapper extends Mapper<SfSUser> {
    public int addBatch(List<SfSUser> users);
}