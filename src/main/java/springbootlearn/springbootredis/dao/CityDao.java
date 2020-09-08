package springbootlearn.springbootredis.dao;

import org.springframework.stereotype.Repository;
import springbootlearn.springbootredis.entity.CityVo;

import java.util.List;

@Repository
public interface CityDao {

	 List<CityVo> listCities();

	 CityVo getCityById(Long id);

	 CityVo updateCity(CityVo cityVo);

	 void deleteCity(Long id);

	 void saveCity(CityVo cityVo);
}
 
