package com.cn.xmpp.openfire;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * @author admin Openfire消息服务器userservice服务的控制模块
 */

/**
 * @author admin
 */
public class UserServiceImp implements UserService {
    /**
     * 操作类型：The admin service required. Possible values are 'add', 'delete',
     * 'update', 'enable', 'disable', 'add_roster', 'update_roster',
     * 'delete_roster'.
     */
    private enum Type {
        add("add"), delete("delete"), update("update"), enable("enable"), disable(
                "disable"), add_roster("add_roster"), update_roster(
                "update_roster"), delete_roster("delete_roster");

        private String nCode;

        Type(String nCode) {
            this.nCode = nCode;
        }

        @Override
        public String toString() {
            return this.nCode;
        }

    }

    /**
     * 日志管理
     */
    private Logger logger = Logger.getLogger(UserServiceImp.class);

    /**
     * 访问密钥:The secret key that allows access to the User Service.
     */
    private String secret;

    /**
     * 用户名:The username of the user to 'add', 'delete', 'update', 'enable',
     * 'disable', 'add_roster', 'update_roster', 'delete_roster'. ie the part
     * before the @ symbol.
     */
    private String username;

    /**
     * 用户口令:Required for 'add' operation.The password of the new user or the
     * user being updated.
     */
    private String password;

    /**
     * 显示名:The display name of the new user or the user being updated. For
     * 'add_roster', 'update_roster' operations specifies the nickname of the
     * roster item.
     */
    private String name;

    /**
     * 邮件地址:The email address of the new user or the user being updated.
     */
    private String email;

    /**
     * 用户组列表:List of groups where the user is a member. Values are comma
     * delimited.
     */
    private String groups;

    /**
     * 花名册子项:The JID of the roster item；Required for 'add_roster',
     * 'update_roster', 'delete_roster' operations.
     */
    private String item_jid;

    /**
     * 订阅关系操作:Type of subscription for 'add_roster', 'update_roster' operations.
     * Possible numeric values are: -1(remove), 0(none), 1(to), 2(from),
     * 3(both).
     */
    private String subscription;

    /**
     * 访问地址
     */
    private String url;
    ;

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#addRoster()
     */
    @Override
    public String addRoster() throws IOException, SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.add_roster.toString());
        parameters.put("secret", secret);
        parameters.put("username", "admin");
        parameters.put("item_jid", "a1234@192.168.0.106");
        String result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;
    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#enable(java.lang.String)
     */
    @Override
    public String enable(String username) throws IOException, SAXException {
        String result;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.enable.toString());
        parameters.put("secret", secret);
        parameters.put("username", username);
        result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;
    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#disable(java.lang.String)
     */
    @Override
    public String disable(String username) throws IOException, SAXException {
        String result;
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.disable.toString());
        parameters.put("secret", secret);
        parameters.put("username", username);
        result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;
    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#addUser()
     */
    @Override
    public String addUser() throws IOException, SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.add.toString());
        parameters.put("secret", secret);
        parameters.put("username", "a1234");
        parameters.put("password", "a1234");
        String result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;
    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#deleteRoster()
     */
    @Override
    public String deleteRoster() throws IOException, SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.delete_roster.toString());
        parameters.put("secret", secret);
        parameters.put("username", "admin");
        parameters.put("item_jid", "a1234@192.168.0.106");
        String result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;

    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#deleteUser()
     */
    @Override
    public String deleteUser() throws IOException, SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.delete.toString());
        parameters.put("secret", secret);
        parameters.put("username", "a1234");
        String result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;
    }

    public String getEmail() {
        return email;
    }

    public String getGroups() {
        return groups;
    }

    public String getItem_jid() {
        return item_jid;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSecret() {
        return secret;
    }

    public String getSubscription() {
        return subscription;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    /**
     * @param username 用户名
     * @param password 口令
     * @return 返回操作状态代码
     * @throws SAXException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private String httpInvokeHandler(HashMap<String, String> parameters)
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public void setItem_jid(String item_jid) {
        this.item_jid = item_jid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setSubscription(String subscription) {
        this.subscription = subscription;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.UserService#updateRoster()
     */
    @Override
    public String updateRoster() throws IOException, SAXException {
        HashMap<String, String> parameters = new HashMap<String, String>();
        parameters.put("type", Type.update_roster.toString());
        parameters.put("secret", secret);
        parameters.put("username", "admin");
        parameters.put("item_jid", "a1234@127.0.0.1");
        parameters.put("subscription", "3");
        String result = this.httpInvokeHandler(parameters);
        logger.debug(result);
        return result;

    }

}
