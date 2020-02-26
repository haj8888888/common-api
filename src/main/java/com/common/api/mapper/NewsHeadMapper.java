package com.common.api.mapper;

import com.common.api.entity.NewsHead;
import com.common.api.entity.NewsHeadExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface NewsHeadMapper {
    int countByExample(NewsHeadExample example);

    int deleteByExample(NewsHeadExample example);

    int deleteByPrimaryKey(Long id);

    int insert(NewsHead record);

    int insertSelective(NewsHead record);

    List<NewsHead> selectByExampleWithBLOBsWithRowbounds(NewsHeadExample example, RowBounds rowBounds);

    List<NewsHead> selectByExampleWithBLOBs(NewsHeadExample example);

    List<NewsHead> selectByExampleWithRowbounds(NewsHeadExample example, RowBounds rowBounds);

    List<NewsHead> selectByExample(NewsHeadExample example);

    NewsHead selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") NewsHead record, @Param("example") NewsHeadExample example);

    int updateByExampleWithBLOBs(@Param("record") NewsHead record, @Param("example") NewsHeadExample example);

    int updateByExample(@Param("record") NewsHead record, @Param("example") NewsHeadExample example);

    int updateByPrimaryKeySelective(NewsHead record);

    int updateByPrimaryKeyWithBLOBs(NewsHead record);

    int updateByPrimaryKey(NewsHead record);
}