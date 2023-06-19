package cn.sixmillions.sb3demo.base.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {

    /**
     * 对象存储服务的URL
     * 对应云厂商的oss站点域名
     */
    private String endpoint;

    /**
     * 区域
     */
    private String region;

    /**
     * Access key
     */
    private String accessKey;

    /**
     * Secret key
     */
    private String secretKey;

    /**
     * 自定义域名
     */
    private String domain = "";

    /**
     * true path-style nginx 反向代理和S3默认支持 pathStyle模式 {http://endpoint/bucketname}
     * false supports virtual-hosted-style 阿里云等需要配置为 virtual-hosted-style 模式{http://bucketname.endpoint}
     * 只是url的显示不一样
     */
    private Boolean pathStyleAccess = true;

    /**
     * 最大线程数，默认：20
     */
    private Integer maxConnections = 20;

    /**
     * 默认 bucketName
     */
    private String bucketName = "data";

    /**
     * 默认协议 Https
     */
    private Boolean isHttps = true;

}
