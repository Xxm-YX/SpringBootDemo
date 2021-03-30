package cn.itcast.dao.base;

import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.base.insert.InsertMapper;

/**
 * 自定义BaseMapper让子接口继承（BaseMapper不要放在通用Mapper扫描路径下）
 *
 * Mapper接口：支持基本的增、删、改、查方法
 * IdListMapper：支持根据IdList批量查询和删除
 * InsertListMapper：支持批量插入
 *
 * @author qiyu
 */
@RegisterMapper
public interface TkBaseMapper<T> extends Mapper<T>, IdListMapper<T,Long>, InsertMapper<T> {

}
