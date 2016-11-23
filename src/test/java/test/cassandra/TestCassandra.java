package test.cassandra;

import com.cn.cassandra.tables.Person;
import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCassandra extends AbstractJUnit4SpringContextTests {
    final Logger logger = Logger.getLogger(TestCassandra.class);

    @Resource
    public CassandraTemplate cassandraTemplate;

    @Test
//    @Ignore
    public void testCassandraTemplateInsert() {
        RandomModule random = new RandomModuleImpl();
        for (int i = 0; i < 100; ++i) {
            String name = random.getRStr(RandomModule.myint_all, 6);
            int age = random.getRandomInt(100);
            String address = random.getRStr(RandomModule.myint_AZ, 10);
            Person person = new Person(name, age, address);
            cassandraTemplate.insert(person);
        }
    }

    public void testCassandraTemplateCounter() {

    }

    @Test
//    @Ignore
    public void testCassandraTemplateCount() {
        long size = cassandraTemplate.count("person");
        logger.debug(String.format("The person table size is [%s] ", size));
    }

    @Test
//    @Ignore`
    public void testCassandraTemplateSelectByLimt() {

        Select select = QueryBuilder.select().from("person");
        select.limit(100);
        List<Person> list = cassandraTemplate.select(select, Person.class);
        for (int i = 0; i < list.size(); ++i) {
            Person person = list.get(i);
            logger.debug(String.format("The persionid is [%s] and it's name is [%s]", person.getId(), person.getName()));
        }

    }

    @Test
//    @Ignore
    public void testCassandraTemplateSelectOne() {
        Select select = QueryBuilder.select().from("person");
        UUID uuid = UUID.fromString("634f884e-2be7-4726-aa4a-fae2db6b9a3d");
        select.where(QueryBuilder.eq("id", uuid));
//        cassandraTemplate.selectOne()
        Person person = cassandraTemplate.selectOne(select, Person.class);
        logger.debug(String.format("The persionid is [%s] and name is [%s]", person.getId(), person.getName()));
    }
}
