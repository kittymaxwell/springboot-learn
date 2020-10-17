package springbootlearn.springbootredis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springbootlearn.springbootredis.entity.CityVo;
import springbootlearn.springbootredis.service.CityService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api("CityController相关的api")
public class CityController {

	protected static Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	private CityService cityService;

	/**
	 * http://localhost:8080/hello
	 *
	 * @return
	 */
	@GetMapping("/hello")
	@ApiOperation(value="hello")
	public String hello() {
		return "Hello greetings from spring-boot2-mysql-mybatis-xml";
	}

	/**
	 * http://localhost:8080/listCities
	 *
	 * @return
	 */
	@GetMapping("/listCities")
	@ApiOperation(value="查询全部")
	public List<CityVo>  listCities() {
		List<CityVo> list = this.cityService.listCities();
		logger.info("输出查询结果：{}",list);
		return list;
	}

	/**
	 * http://localhost:8080/city/page
	 *
	 * @param page
	 * @return
	 */
	@PostMapping("/city/page")
	@ApiOperation(value="分页查询")
	public IPage<CityVo> selectPage(@RequestBody Page page) {

		QueryWrapper<CityVo> wrapper = new QueryWrapper<>();
		IPage<CityVo> cityVoIPage = cityService.selectPage(page, wrapper);
		return cityVoIPage;
	}

	/**
	 * http://localhost:8080/findCityById?id=1
	 *
	 * @return
	 */
	@GetMapping("/findCityById/{id}")
	@ApiOperation(value="根据ID查询详情")
	@ApiImplicitParam(name = "id", value = "城市ID",  paramType = "path", required = true)
	public CityVo findCityById(@PathVariable Long id) {
		CityVo obj = this.cityService.findCityById(id);
		return obj;
	}

	/**
	 * http://localhost:8080/findCityById?id=1
	 *
	 * @return
	 */
	@GetMapping("/findCityById/cache/annotation/{id}")
	@ApiOperation(value="根据ID查询详情")
	@ApiImplicitParam(name = "id", value = "城市ID",  paramType = "path", required = true)
	public CityVo findCityById2(@PathVariable Long id) {
		CityVo obj = this.cityService.findCityById2(id);
		return obj;
	}

	/**
	 * http://localhost:8080/deleteCity/1
	 *
	 * @return
	 */
	@DeleteMapping("/deleteCity/{id}")
	@ApiOperation(value="根据ID删除")
	@ApiImplicitParam(name = "id", value = "城市ID",  paramType = "path", required = true)
	public void deleteCity(@PathVariable(value = "id") Long id) {
		this.cityService.deleteCity(id);
	}

	/**
	 * http://localhost:8080/deleteCity/1
	 *
	 * @return
	 */
	@DeleteMapping("/deleteCity2/{id}")
	@ApiOperation(value="根据ID删除")
	@ApiImplicitParam(name = "id", value = "城市ID",  paramType = "path", required = true)
	public void deleteCity2(@PathVariable(value = "id") Long id) {
		this.cityService.deleteCity2(id);
	}

	/**
	 * http://localhost:8080/saveOrupdateCity
	 *
	 * @return
	 */
	@PostMapping("/saveOrupdateCity")
	@ApiOperation(value="保持或更新")
	public void saveOrupdateCity(@RequestBody CityVo cityVo) {
		this.cityService.saveOrupdateCity(cityVo);
	}

	/**
	 * http://localhost:8080/saveOrupdateCity2
	 *
	 * @return
	 */
	@PostMapping("/saveOrupdateCity2")
	@ApiOperation(value="保持或更新")
	public void saveOrupdateCity2(@Validated @RequestBody CityVo cityVo) {
		this.cityService.saveOrupdateCity2(cityVo);
	}

}