package test.search;

import com.cn.search.SearchModule;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by linpeng109 on 15-8-24.
 */
public class TestSearchUserBase extends TestBase {
    /**
     * 日志
     */
    final Logger logger = Logger.getLogger(TestSearchUserBase.class);

    @Resource
    public SearchModule searchModule;

    //@Test
    public void testSearchUserBaseByUserBaseName() {
        try {
            searchModule.searchUserBase("userName", "吴*");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testWildSearch() throws IOException {
        searchModule.wildCardSearcher("userName", "*林*");
    }


}
