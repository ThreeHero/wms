package com.threehero.warehouse.mapper;

import com.threehero.warehouse.dao.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author threehero
 * @since 2022-11-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
