package com.cn.qiniu;

/**
 * Created by Mars on 2016/5/17.
 * 七牛云图片服务器应用
 */

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import com.qiniu.common.Zone;
//import com.qiniu.storage.Configuration;

import java.io.IOException;

public class Qiniu {
    //Logger
    Logger logger = Logger.getLogger(Qiniu.class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "ukoBmyYilY6szsAwM6ooe21Fa2w_XwFehpqITf1b";
    String SECRET_KEY = "2W4d4tqWhVyo4axbga01EZhxZRiXCWl9x3NO_oYV";

    //要上传的空间
    String bucketname = "lottery20160512";

    //上传到七牛后保存的文件名
    String key = "my-java.png";

    //上传文件的路径
    String FilePath = "G:\\family\\20140511104656.jpg";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //创建上传对象
    //Zone z = Zone.autoZone();
    //Configuration c = new Configuration(z);
    UploadManager uploadManager = new UploadManager();

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }

    public static void main(String[] args) {
        Qiniu qiniu = new Qiniu();
        String result = qiniu.getUpToken();
        System.out.println(result);
        try {
            qiniu.upload();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
