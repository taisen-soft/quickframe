package cn.com.frame.mapper;

import cn.com.frame.model.SfBQuickcommon;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SfBQuickcommonMapper extends Mapper<SfBQuickcommon> {
    public int addBatch(List<SfBQuickcommon> sfbs);
    public List<SfBQuickcommon> selectPaperByCondition(String school,String grade,String className);

    public int zinsert(SfBQuickcommon sfBQuickcommon);
    public int zupdate(SfBQuickcommon sfBQuickcommon);
}