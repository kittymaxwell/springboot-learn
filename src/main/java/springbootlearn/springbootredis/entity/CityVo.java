package springbootlearn.springbootredis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ApiModel(description= "城市基本信息")
@TableName("city")
public class CityVo implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "城市ID")
	private Long id;
	@ApiModelProperty(value = "城市名称")
	@NotBlank(message = "城市名称不能为空")
	private String name;
	@ApiModelProperty(value = "城市状态")
	@NotBlank(message = "城市状态不能为空")
	private String state;
}
 
