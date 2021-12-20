package com.briup.cms.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 七牛云工具类
 * http://r3tsx6mgn.hn-bkt.clouddn.com/ 域名
 */
@Slf4j
public class QiniuUtil {
    public static final String accessKey = "M1J6WgMIOisq7Bg8vyCii3SKe9GMGNOgtmUhVWWT";
    public static final String secretKey = "gOCD2k-nsqnTqU3u-s-WmTd8YXXZGvRlnpaVt_iC";
    public static final String bucket = "cms-briup1";
    public static final String BASE_URL = "http://r4el9vwrh.hd-bkt.clouddn.com/";

    private static String upToken;
    private static UploadManager uploadManager;
    private static BucketManager bucketManager;

    static{
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 获取上传管理器
        uploadManager = new UploadManager(cfg);
        // 默认不指定key的情况下，以文件内容的hash值作为文件名
        Auth auth = Auth.create(accessKey, secretKey);
        // 获取空间（bucket）管理器
        bucketManager = new BucketManager(auth, cfg);
        // 获取上传token
        upToken = auth.uploadToken(bucket);
    }




    /*
     * @Description 上传文件到七牛云
     * @Date 2021/6/1 9:21
     * @Param [filePath: 需要上传文件的位置, fileName: 指定存在七牛云的标识,不传有默认值]
     * @return void
     **/
    public static void upload2Qiniu(String filePath, String fileName) {
        try {
            Response response = uploadManager.put(filePath, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        } catch (QiniuException ex) {
            log.error("上传七牛云图片失败 {}",ex.getMessage());
        }
    }

    /*
     * @Description 上传文件到七牛云
     * @Date 2021/6/1 9:21
     * @Param [filePath: 需要上传文件的字节数组: 指定存在七牛云的标识,不传有默认值]
     * @return String  返回七牛云的标识  后续通过拼接域名+标识即可访问到图片
     **/
    public static String upload2Qiniu(byte[] bytes, String fileName) {
        try {
            Response response = uploadManager.put(bytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return putRet.key;
        } catch (QiniuException ex) {
            log.error("上传七牛云图片失败 {}",ex.getMessage());
        }
        return null;
    }


    // 重载方法: 上传到七牛云 不传唯一标识
    public static String upload2Qiniu(byte[] bytes) {

        return upload2Qiniu(bytes,null);
    }

    /*
     * @Description `删除七牛云的文件
     * @Date 2021/6/1 9:22
     * @Param [fileName] 七牛云的唯一标识
     * @return void
     **/
    public static void deleteFileFromQiniu(String fileName) {
        try {
            bucketManager.delete(bucket, fileName);
        } catch (QiniuException ex) {
            //如果遇到异常，说明删除失败
            log.error("删除七牛云图片失败 {} {}",ex.code(),ex.response.toString());
        }
    }

    public static void deleteFileFromQiniu(List<String> list) {
        list.forEach(QiniuUtil::deleteFileFromQiniu);
    }


   /*
    * @Description 通过http get下载图片资源
    * @Date 2021/6/1 9:24
    * @Param [url: 七牛云地址, filepath: 下载位置, picKey: 将来拼接filepath+picKye拼接本地下载路径]
    * @return void
    **/
    public static void download(String filepath, String picKey) {
        String url = BASE_URL + picKey;
        OkHttpClient client = new OkHttpClient();
        Request req = new Request.Builder().url(url).build();
        okhttp3.Response resp = null;
        try {
            resp = client.newCall(req).execute();
            if (resp.isSuccessful()) {
                ResponseBody body = resp.body();
                InputStream is = body.byteStream();
                byte[] data = readInputStream(is);

                //判断文件夹是否存在，不存在则创建
                File file = new File(filepath);
                if (!file.exists() && !file.isDirectory()) {
                    file.mkdirs();
                }

                if(!filepath.endsWith("/")){
                    filepath = filepath+"/";
                }

                File imgFile = new File(filepath + picKey + ".jpg");
                FileOutputStream fops = new FileOutputStream(imgFile);
                fops.write(data);
                fops.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("下载七牛云图片失败{}",e.getMessage());
        }
    }

    /**
     * 读取字节输入流内容
     *
     * @param is
     * @return
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 2];
        int len = 0;
        try {
            while ((len = is.read(buff)) != -1) {
                writer.write(buff, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toByteArray();
    }

    public static void main(String args[]) throws IOException {
        //FileInputStream file = new FileInputStream();
        upload2Qiniu("C:\\Users\\vanse\\Pictures\\bg.jpg","bg");
    }

}
