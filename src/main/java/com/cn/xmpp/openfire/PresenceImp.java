package com.cn.xmpp.openfire;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 获取用户状态模块
 *
 * @author admin
 */
public class PresenceImp implements Presence {
    public String url;

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.Presence#getPresence(java.lang.String, java.lang.String)
     */
    @Override
    public String getPresence(String jid, String type) throws IOException,
            SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", type);
        parameters.put("jid", jid);
        parameters.put("req_jid", "admin@192.168.0.106");
        String result = this.presenceHandler(parameters);
        return result;
    }

    public String getUrl() {
        return url;
    }

    /**
     * @param username 用户名
     * @param password 口令
     * @return 返回操作状态代码
     * @throws SAXException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private String presenceHandler(HashMap<String, String> parameters)
            throws IOException, SAXException {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(url);
        Iterator<?> iterator = parameters.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, String> next = (Entry<String, String>) iterator
                    .next();
            Entry<String, String> entry = next;
            String key = entry.getKey();
            String value = entry.getValue();
            request.setParameter(key, value);
        }
        WebResponse response = conversation.getResponse(request);
        String result = response.getText();
        return result;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
