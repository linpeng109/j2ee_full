package test;

import junit.framework.TestCase;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public abstract class TestBase extends TestCase {

    @Resource
    public SessionFactory sessionFactory;
    public Session session;

    @Override
    public void setUp() throws Exception {
        session = sessionFactory.openSession();
        session.setFlushMode(FlushMode.ALWAYS);
        TransactionSynchronizationManager.bindResource(sessionFactory,
                new SessionHolder(session));
    }

    @Override
    public void tearDown() throws Exception {
        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager
                .getResource(sessionFactory);
        session = holder.getSession();
        session.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(session);
    }
}