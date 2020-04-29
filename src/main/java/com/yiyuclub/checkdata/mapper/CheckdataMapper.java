package com.yiyuclub.checkdata.mapper;

import com.yiyuclub.checkdata.models.Checkdata;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yiyu
 * @since 2020-04-27
 */
@Repository
public interface CheckdataMapper extends BaseMapper<Checkdata> {
    @Select("select xh from checkdata where type=#{type} order by xh desc  LIMIT 1")
   Integer selectLastData(@Param("type")String type);
}
