package test.jep;

import com.cn.common.RandomModule;
import com.cn.jep.JepSample;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nfunk.jep.ParseException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by jupiter on 15-12-2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestJepSample {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(TestJepSample.class);

    @Resource
    private JepSample jepSample;

    @Resource
    private RandomModule randomModule;

    @Test
    public void testJepSample() throws ParseException {
        for (int i = 0; i < 1000; ++i) {
            jepSample.x = randomModule.getRandomInt(100);
            jepSample.y = randomModule.getRandomInt(100);
            jepSample.z = randomModule.getRandomInt(100);

            jepSample.calculate();
        }
    }
}
