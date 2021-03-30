package cn.itcast.dao;

import cn.itcast.dao.base.TkBaseMapper;
import cn.itcast.pojo.TkUserPojo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TkUserMapper extends TkBaseMapper<TkUserPojo> {

    /**
     * 批量插入
     * @param list
     */
    void insertBatch(@Param("list") List<TkUserPojo> list);


    /**
     * 批量更新
     *
     * @param list
     */
    void updateBatch(@Param("list") List<TkUserPojo> list);

    /**
     * 批量删除（逻辑删除）
     *
     * @param list
     */
    void deleteBatch(@Param("list") List<TkUserPojo> list);

    /**
     * 批量查询
     *
     * @param ids
     * @return
     */
    List<TkUserPojo> selectBatch(@Param("ids") List<Long> ids);
}
