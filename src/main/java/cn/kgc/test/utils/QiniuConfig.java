package cn.kgc.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * cn.kgc.test.utils
 *
 * @Author Administrator
 * @date 10:20
 */
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String path;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, Object> upload(MultipartFile multipartFile) {
        //构造一个带指定 Region 对象的配置类 华南
        Configuration cfg = new Configuration(Region.region2());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        Map<String, Object> map = new HashMap<>(16);
        //得到原始的文件名
        String originalFilename = multipartFile.getOriginalFilename();
        //得到文件后缀名
        String fileSuffix=originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
        //生成新的文件名
        String newFilename= UUID.randomUUID().toString()+fileSuffix;
        //文件外链
        String pathFile=path+"/"+newFilename;
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = newFilename;
        try {
            byte[] uploadBytes = multipartFile.getBytes();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = objectMapper.readValue(response.bodyString(), new TypeReference<DefaultPutRet>() {
            });
            map.put("code", 200);
            map.put("msg", "上传成功");
            map.put("originalFilename", originalFilename);
            map.put("pathFile", pathFile);
            map.put("hash", putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            map.put("code", 500);
            map.put("msg", "七牛服务器异常");
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "编码不支持异常");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", 500);
            map.put("msg", "json转换异常");
        }
        return map;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
