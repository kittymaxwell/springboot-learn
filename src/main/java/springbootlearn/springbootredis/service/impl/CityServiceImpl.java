package springbootlearn.springbootredis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import springbootlearn.springbootredis.dao.CityDao;
import springbootlearn.springbootredis.dao.CityDao2;
import springbootlearn.springbootredis.entity.CityVo;
import springbootlearn.springbootredis.service.CityService;


import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CityServiceImpl implements CityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	private CityDao cityDao;

	@Autowired
	private CityDao2 cityDao2;

	private RedisTemplate redisTemplate;

	@Autowired(required = false)
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		RedisSerializer stringSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringSerializer);
		redisTemplate.setHashKeySerializer(stringSerializer);
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 获得城市列表
	 */
	@Override
	public List<CityVo> listCities() {
		return this.cityDao.listCities();
	}

	/**
	 * 根据id，获得某个城市
	 */
	@Override
	public CityVo getCityById(Long id) {
		return this.cityDao.getCityById(id);
	}

	/**
	 *
	 * 获取城市逻辑：如果缓存存在，从缓存中获取城市信息.如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
	 *
	 * @param id
	 * @return
	 */
	@Override
	public CityVo findCityById(Long id) {
		//从缓存中获取城市信息
		String key = "city::city_"+id;
		ValueOperations<String,CityVo> operations = redisTemplate.opsForValue();

		//缓存是否存在.如果存在，返回
		boolean has = redisTemplate.hasKey(key);
		if(has){
			CityVo cityVo = operations.get(key);
			LOGGER.info("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + cityVo.toString());
			return cityVo;
		}
		//从DB中获取数据
		CityVo cityVo = cityDao.getCityById(id);
		if(cityVo!=null){
			operations.set(key,cityVo,100, TimeUnit.SECONDS);
			LOGGER.info("CityServiceImpl.findCityById() : 城市插入缓存 >> " + cityVo.toString());
		}
		return cityVo;
	}

	@Override
	public void saveOrupdateCity(CityVo cityVo) {
		if(cityVo.getId()==null){
			cityDao.saveCity(cityVo);
		}else{
			updateCity(cityVo);
		}
	}

	@Override
	public void saveOrupdateCity2(CityVo cityVo) {
		if(cityVo.getId()==null){
			cityDao2.insert(cityVo);
		}else{
			cityDao2.updateById(cityVo);
		}
	}

	@Override
	public IPage<CityVo> selectPage(Page page, QueryWrapper<CityVo> wrapper) {
		return cityDao2.selectPage(page,wrapper);
	}

	@Override
	@Cacheable(value = "city", key = "'city_' + #id", unless = "#result eq null")
	public CityVo findCityById2(Long id) {
		CityVo cityVo = cityDao.getCityById(id);
		return cityVo;
	}

	public void updateCity(CityVo cityVo) {
		//更新数据
		cityDao.updateCity(cityVo);
		//缓存存在，删除缓存
		String key = "city::city_"+cityVo.getId();

		boolean has  = redisTemplate.hasKey(key);
		if(has){
			redisTemplate.delete(key);
			LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + cityVo.toString());
		}

	}

	@Override
	public void deleteCity(Long id) {

		cityDao.deleteCity(id);
		//缓存存在，删除缓存
		String key = "city::city_"+id;

		boolean has  = redisTemplate.hasKey(key);
		if(has){
			redisTemplate.delete(key);
			LOGGER.info("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + id);
		}
	}

	@Override
	@CacheEvict(value = "city", key = "'city_' + #id")
	public void deleteCity2(Long id) {
		cityDao.deleteCity(id);
	}

}
 
