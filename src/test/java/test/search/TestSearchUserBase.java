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

    @Test
    public void testRebuildSearch() throws InterruptedException {
        searchModule.searchByRebuild();
    }

    @Test
    public void testWildSearch() throws IOException {
        searchModule.searchByWildCard("userName", "*林*");
    }
}
