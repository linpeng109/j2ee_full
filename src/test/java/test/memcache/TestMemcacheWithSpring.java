package test.memcache;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.cn.hibernate.entity.UserBase;
import com.google.code.ssm.Cache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by jupiter on 15-11-29.
 */
public class TestMemcacheWithSpring extends TestBase {
    /**
     * 依赖注入获取memcache操作客户端
     */
    @Resource
    public Cache defaultMemcachedClient;
    private Logger logger = Logger.getLogger(TestMemcacheWithSpring.class);
    /**
     * 随机数
     */
    private RandomModule random = new RandomModuleImpl();

    /**
     * 随机字符串
     */
    private String key = random.getUUID();

    @Test
    public void testAdd() throws ParseException, TimeoutException, CacheException {
        for (int i = 0; i < 50; ++i) {
            String key = "key_" + i;
            ArrayList value = new ArrayList();
            for (int j = 0; j < 10; ++j) {
                UserBase user = new UserBase();
                user.setUserId(random.getUUID());
                user.setUserName(random.getRStr(RandomModule.mystring_china, 2));
                user.setPassWord(user.getUserName());
                user.setCreateDate(random.getRandomDate("1999:1:1", "2018:12:30", "yyyy:MM:dd"));
                value.add(user);
            }
            defaultMemcachedClient.add(key, 5000, value, SerializationType.JAVA);
        }

    }

    @Test
    public void testGet() throws TimeoutException, CacheException {

        for (int i = 0; i < 50; ++i) {
            String key = "key_" + i;
            ArrayList<UserBase> list = (ArrayList<UserBase>) defaultMemcachedClient.get(key, SerializationType.JAVA);
            for (int j = 0; j < 10; ++j) {
                UserBase user = list.get(j);
                logger.debug(String.format("userId=[%s];userName=[%s],createDate=[%s]", user.getUserId(), user.getUserName(), user.getCreateDate()));
            }
        }

    }

    public void testDelete() {
        // defaultMemcachedClient.delete()

    }
}
