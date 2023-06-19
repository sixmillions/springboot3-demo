package cn.sixmillions.sb3demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("tl_oss_log")
public class OssLog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String url;
    private String originalFilename;
    private String filename;
    private String vendor;
    private String bucket;
    private String filePath;
    /**
     * 文件大小
     * 单位 KB
     */
    private Integer fileSize;
    private String mediaType;
    private String suffix;
    private String createdBy;
    private LocalDateTime createdTime;


}