package com.cn.search;

import com.cn.dao.entity.UserBase;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by linpeng109 on 15-8-21.
 */
public class SearchModule {
    /**
     * 日志
     */
    final Logger logger = Logger.getLogger(SearchModule.class);

    /**
     * 依赖注入获取sessionFactory
     */
    private SessionFactory sessionFactory;

    /**
     * Lucene模糊检索
     */
    public void wildCardSearcher(String fieldName, String searchString) throws IOException {
        String searchDir = "/home/linpeng109/workset/workdir/search/com.cn.dao.entity.UserBase";

        Directory directory = FSDirectory.open(new File(searchDir));
        IndexReader reader = IndexReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        Term term = new Term(fieldName, searchString);
        WildcardQuery wildcardQuery = new WildcardQuery(term);

        TopDocs topDocs = indexSearcher.search(wildcardQuery, 10);
        ScoreDoc scoreDocs[] = topDocs.scoreDocs;
        for (int i = 0; i < scoreDocs.length; i++) {
            Document document = indexSearcher.doc(scoreDocs[i].doc);
            logger.debug(String.format("userName is [%s] and userId is [%s]", document.get("userName"), document.get("userId")));

        }
    }


    /**
     * 全文检索函数
     *
     * @param searchString 搜索字符串
     */
    public void searchUserBase(String fieldNane, String searchString) throws InterruptedException {
        logger.debug("Searching is running.");

        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
//        fullTextSession.createIndexer().startAndWait();

        Transaction transaction = fullTextSession.beginTransaction();

        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(UserBase.class).get();

        org.apache.lucene.search.Query luceneQuery =
                queryBuilder
                        .keyword()
                        .onField(fieldNane).boostedTo(3)
                        .matching(searchString)
                        .createQuery();

        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);

        List result = fullTextQuery.list(); //return a list of managed objects
        logger.debug(result.size());

        List<UserBase> list = result;
        for (UserBase userBase : list) {
            logger.debug(String.format("The username is [%s] and userId is [%s]", userBase.getUserName(), userBase.getUserId()));
        }

        transaction.commit();
        session.close();

    }


    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
