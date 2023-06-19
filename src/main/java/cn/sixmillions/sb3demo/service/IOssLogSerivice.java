package cn.sixmillions.sb3demo.service;

import cn.sixmillions.sb3demo.entity.OssLog;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IOssLogSerivice extends IService<OssLog> {

    void asyncSaveLog(OssLog ossLog);
}
