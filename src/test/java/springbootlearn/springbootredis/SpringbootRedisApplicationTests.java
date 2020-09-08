package springbootlearn.springbootredis;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springbootlearn.springbootredis.dao.RedisDao;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootRedisApplicationTests {

    public static Logger logger = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisDao redisDao;

    @Test
    public void testRedis(){
        redisDao.setKey("health","ok");

        logger.info(redisDao.getValue("health"));
    }

}
