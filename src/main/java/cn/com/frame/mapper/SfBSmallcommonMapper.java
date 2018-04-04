package cn.com.frame.mapper;

import cn.com.frame.model.SfBSmallcommon;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SfBSmallcommonMapper extends Mapper<SfBSmallcommon> {
    public int addBatch(List<SfBSmallcommon> sfbs);
}