package test.memcache;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.cn.hibernate.entity.UserBase;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

/**
 * Created by jupiter on 15-12-1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMemcacheWithoutSpring {
    private Logger logger = Logger.getLogger(TestMemcacheWithoutSpring.class);

    private String memcacheHost = "192.168.0.102";
    private String memcachePort = "11211";
    private int memcachePoolSize = 10;
    private boolean failureMode = true;
    private BinaryCommandFactory commandFactory = new BinaryCommandFactory();

    /**
     * 随机数
     */
    private RandomModule random = new RandomModuleImpl();

    /**
     * 随机字符串
     */
    private String key = random.getUUID();


    private MemcachedClient client;


    public MemcachedClient buildClient() throws IOException {
        MemcachedClientBuilder builder = new XMemcachedClientBuilder(AddrUtil.getAddresses(memcacheHost + ":" + memcachePort));
        builder.setConnectionPoolSize(memcachePoolSize);
        builder.setFailureMode(failureMode);
        builder.setCommandFactory(commandFactory);
        return builder.build();
    }

    @Test
    public void testAdd() throws InterruptedException, MemcachedException, TimeoutException, ParseException, IOException {
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
            boolean result = buildClient().add(key, 5000, value);

            logger.debug(result);
        }

    }

    @Test
    public void testGet() throws InterruptedException, MemcachedException, TimeoutException, IOException {

        for (int i = 0; i < 50; ++i) {
            String key = "key_" + i;
            ArrayList<UserBase> list = (ArrayList<UserBase>) buildClient().get(key);
            for (int j = 0; j < 10; ++j) {
                UserBase user = list.get(j);
                logger.debug(String.format("userId=[%s];userName=[%s],createDate=[%s]", user.getUserId(), user.getUserName(), user.getCreateDate()));
            }
        }

    }

    @Test
    @Ignore
    public void testDelete() {

    }

    @Override
    protected void finalize() throws Throwable {
        client.shutdown();
        super.finalize();
    }
}
