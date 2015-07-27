package com.cn.xmpp.openfire;

import org.xml.sax.SAXException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public interface Presence {
    @WebMethod
    public abstract String getPresence(String jid, String type)
            throws IOException, SAXException;

}