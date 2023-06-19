package cn.sixmillions.sb3demo.controller;

import cn.sixmillions.sb3demo.base.properties.OssProperties;
import cn.sixmillions.sb3demo.base.service.OssTemplate;
import cn.sixmillions.sb3demo.entity.OssLog;
import cn.sixmillions.sb3demo.service.IOssLogSerivice;
import com.alibaba.fastjson2.util.DateUtils;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/oss")
@Slf4j
public class OssController {

    @Autowired
    private OssTemplate ossTemplate;

    @Autowired
    private IOssLogSerivice ossLogSerivice;

    @Autowired
    private OssProperties ossProperties;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            ossTemplate.putObject(ossProperties.getBucketName(), file.getOriginalFilename(), inputStream, file.getContentType());
        } catch (Exception e) {
            log.error("文件上传OSS出错", e);
            return "";
        }
        return String.format("%s/%s/%s", ossProperties.getDomain(), ossProperties.getBucketName(), file.getOriginalFilename());
    }

    @PostMapping("/upload-img")
    public String uploadFile(MultipartFile file,
                             @RequestParam(value = "keepOriginalFilename", defaultValue = "false") boolean keepOriginalFilename,
                             @RequestParam(value = "filePath", defaultValue = "") String filePath
    ) {
        LocalDateTime now = LocalDateTime.now();
        String fileName = file.getOriginalFilename();
        String suffix = StringUtils.substringAfterLast(fileName, StringPool.DOT);
        if (!keepOriginalFilename) {
            fileName = DateUtils.format(now, "ddHHmmss.") + suffix;
        }
        if (StringUtils.isBlank(filePath)) {
            filePath = DateUtils.format(now, "yyyy/MM");
        }
        // 上传到bucket的根路径判断
        String objectName = filePath.equals(StringPool.SLASH) ? fileName : String.format("%s/%s", filePath, fileName);
        try (InputStream inputStream = file.getInputStream()) {
            ossTemplate.putObject(ossProperties.getBucketName(), objectName, inputStream, file.getContentType());
        } catch (Exception e) {
            log.error("文件上传OSS出错", e);
            return "";
        }
        String url = String.format("%s/%s/%s", ossProperties.getDomain(), ossProperties.getBucketName(), objectName);
        ossLogSerivice.asyncSaveLog(OssLog.builder()
                .url(url)
                .originalFilename(file.getOriginalFilename())
                .filename(fileName)
                .vendor("minio")
                .bucket(ossProperties.getBucketName())
                .filePath(filePath)
                .fileSize((int) (file.getSize() / 1024))
                .mediaType(file.getContentType())
                .suffix(suffix)
                .createdBy("sys")
                .createdTime(LocalDateTime.now())
                .build());
        return url;
    }
}
