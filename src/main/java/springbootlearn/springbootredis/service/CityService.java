package springbootlearn.springbootredis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import springbootlearn.springbootredis.entity.CityVo;

import java.util.List;

public interface CityService {
 
	 List<CityVo> listCities();
 
	 CityVo getCityById(Long id);

	/**
	 *
     * 获取城市逻辑：如果缓存存在，从缓存中获取城市信息.如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
	 *
	 * @param id
	 * @return
	 */
	 CityVo findCityById(Long id);

	/**
	 *更新城市逻辑：如果缓存存在，删除,如果缓存不存在，不操作
	 *
	 * @param cityVo
	 */
	void saveOrupdateCity(CityVo cityVo);


	/**
	 * 缓存存在，删除缓存
	 *
	 * @param id
	 */
	void deleteCity(Long id);

	/**
	 * 缓存存在，删除缓存
	 *
	 * @param id
	 */
	void deleteCity2(Long id);

	/**
	 *更新城市逻辑：如果缓存存在，删除,如果缓存不存在，不操作
	 *
	 * @param cityVo
	 */
    void saveOrupdateCity2(CityVo cityVo);

	/**
	 * 分页查询城市列表
	 *
	 * @param page
	 * @param wrapper
	 * @return
	 */
    IPage<CityVo> selectPage(Page page, QueryWrapper<CityVo> wrapper);

	/**
	 * 获取城市逻辑：如果缓存存在，从缓存中获取城市信息.如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
	 *
	 * @param id
	 * @return
	 */
	CityVo findCityById2(Long id);
}