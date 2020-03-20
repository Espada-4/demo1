package com.mark.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @program: demo
 * @description:
 * @author: wu
 * @create: 2020-03-20 18:32
 **/
public class aliyu {
    @Test
    public void test() {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FkKPBjCWn5H5DEN37iJ";
        String accessKeySecret = "6xS3IZBPcGnU6ylWXLuLW7Cjz9WKQT";
        String bucketName = "whtfoss";
// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "test/wechat1.png";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传内容到指定的存储空间（bucketName）并保存为指定的文件名称（objectName）。H:\javapr\src\main\resources\static\image\wechat.png
        String content = "Hello OSS";
        ossClient.putObject(bucketName, objectName,new File("src/main/resources/static/image/wechat.png"));

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
