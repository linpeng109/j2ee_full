package com.cn.search;

import com.cn.dao.entity.UserBase;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
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
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
//    @Test
    public void searchByWildCard(String fieldName, String searchString) throws IOException {
        String searchDir = "/home/jupiter/workset/workdir/search/com.cn.dao.entity.UserBase";
//        Path searchPath = Paths.get(searchDir);
        Directory directory = FSDirectory.open(new File(searchDir));
        IndexReader reader = DirectoryReader.open(directory);
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
     * 重新建立索引
     */
    @Test
    public void searchByRebuild() throws InterruptedException {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        fullTextSession.createIndexer(UserBase.class).startAndWait();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
