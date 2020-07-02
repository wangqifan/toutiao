package com.wangqifan.toutiao.Service;


import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.wangqifan.toutiao.util.ToutiaoUtil;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class QiniuService {
    String ACCESS_KEY = "IrN94P4QrEz9rasZsOLdxhfKldpQXc0yA3eCPPHL";
    String SECRET_KEY = "0sqO6ONmK8p0WRDthv2GiXREpXgCoMtWnW-qjFpX";
    //要上传的空间
    String bucketname = "wqf";

    //构造一个带指定 Region 对象的配置类
    Configuration cfg = new Configuration(Zone.zone2());

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //创建上传对象
    UploadManager uploadManager = new UploadManager(cfg);

    private static String QINIU_IMAGE_DOMAIN ="http://qctrq4pf1.bkt.clouddn.com/";

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了 
    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public String saveImage(MultipartFile file) throws IOException {
        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            if (!ToutiaoUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            //调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            //打印返回的信息
            if (res.isOK() && res.isJson()) {
                System.out.println(res.toString());
                return QINIU_IMAGE_DOMAIN + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
             //   logger.error("七牛异常:" + res.bodyString());
                return null;
            }
        } catch (QiniuException e) {
            // 请求失败时打印的异常的信息
            e.printStackTrace();
            return null;
        }
    }

}
