package com.hoby.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * @author hoby
 * @since 2024-01-09
 */
public interface OrderMapper {

	@Select("select 'order'")
	String selectById();

}
