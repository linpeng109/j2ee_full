package test.cassandra;

import com.cn.cassandra.CassandraDAO;
import com.cn.cassandra.tables.Person;
import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestCassandra {
    final Logger logger = Logger.getLogger(TestCassandra.class);

    @Resource
    public CassandraDAO cassandraTemplate;

    @Test
    public void testCassandraTemplateInsert() {
        RandomModule random = new RandomModuleImpl();
        for (int i = 0; i < 100; ++i) {
            String name = random.getRStr(RandomModule.myint_all, 6);
            int age = random.getRandomInt(100);
            String address = random.getRStr(RandomModule.myint_AZ, 10);
            Person person = new Person(name, age, address);
            cassandraTemplate.getCassandraTemplate().insert(person);
        }
    }

    public void testCassandraTemplateCounter() {

    }

    @Test
    public void testCassandraTemplateCount() {
        long size = cassandraTemplate.getCassandraTemplate().count("person");
        logger.debug(String.format("The person table size is [%s] ", size));
    }

    @Test
    public void testCassandraTemplateSelectByLimt() {

        Select select = QueryBuilder.select().from("person");
        select.limit(100);
        List<Person> list = cassandraTemplate.getCassandraTemplate().select(select, Person.class);
        for (int i = 0; i < list.size(); ++i) {
            Person person = list.get(i);
            logger.debug(String.format("The persionid is [%s] and it's name is [%s]", person.getId(), person.getName()));
        }

    }

    @Test
    public void testCassandraTemplateSelectOne() {
        Select select = QueryBuilder.select().from("person");
        UUID uuid = UUID.fromString("591f180b-cf42-4f60-9e4a-374e75a6221c");
        select.where(QueryBuilder.eq("id", uuid));
        Person person = cassandraTemplate.getCassandraTemplate().selectOne(select, Person.class);
        logger.debug(String.format("The persionid is [%s] and name is [%s]", person.getId(), person.getName()));
    }
}
