package cn.sixmillions.sb3demo.base.service;

import com.alibaba.fastjson2.JSON;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 测试 OSS
 *
 * @author pk3xad
 */
@SpringBootTest
@Slf4j
public class OssTemplateTest {

    @Autowired
    private OssTemplate ossTemplate;

    private static final String BUCKET_NAME = "test";

    /**
     * 测试Bucket是否存在
     */
    @Test
    public void test_exist_bucket() {
        assertFalse(ossTemplate.isExistBucket(UUID.randomUUID().toString()));
    }

    /**
     * 测试创建Bucket
     */
    @Test
    public void test_create_bucket() {
        assertFalse(ossTemplate.isExistBucket(BUCKET_NAME));
        ossTemplate.createBucket(BUCKET_NAME);
        assertTrue(ossTemplate.isExistBucket(BUCKET_NAME));
    }

    /**
     * 测试获取所有Bucket
     */
    @Test
    public void test_all_buckets() {
        List<Bucket> buckets = ossTemplate.getAllBuckets();
        log.info(JSON.toJSONString(buckets));
        assertTrue(buckets.stream().anyMatch(item -> item.getName().equals(BUCKET_NAME)));
    }

    /**
     * 测试删除Bucket
     * Bucket中必须是空的才可以删除
     */
    @Test
    public void test_delete_bucket_01() {
        String bucketName = UUID.randomUUID().toString();
        ossTemplate.createBucket(bucketName);
        assertTrue(ossTemplate.isExistBucket(bucketName));
        ossTemplate.removeBucket(bucketName);
        assertFalse(ossTemplate.isExistBucket(bucketName));
    }

    /**
     * 测试删除Bucket
     * Bucket中不为空则抛异常
     */
    @Test
    public void test_delete_bucket_02() throws Exception {
        String fileName = String.format("%s.%s", UUID.randomUUID(), "txt");
        ossTemplate.putObject(BUCKET_NAME, fileName, "Hello World");
        assertThrows(AmazonS3Exception.class, () -> ossTemplate.removeBucket(BUCKET_NAME));
    }

    /**
     * 将字符串直接上传到OSS
     *
     * @throws Exception
     */
    @Test
    public void test_upload_text() throws Exception {
        String fileName = String.format("%s.%s", UUID.randomUUID(), "txt");
        ossTemplate.putObject(BUCKET_NAME, fileName, "Hello World");
    }

    @Test
    public void test_get_obj_url() throws Exception {
        String fileName = String.format("%s.%s", UUID.randomUUID(), "txt");
        ossTemplate.putObject(BUCKET_NAME, fileName, "Hello World");
        String url = ossTemplate.getObjectURL(BUCKET_NAME, fileName, 7);
        log.info(url);
        assertTrue(url.contains(fileName));
    }

}