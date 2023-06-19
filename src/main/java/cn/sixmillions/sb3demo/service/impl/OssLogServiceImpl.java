package cn.sixmillions.sb3demo.service.impl;

import cn.sixmillions.sb3demo.entity.OssLog;
import cn.sixmillions.sb3demo.mapper.OssLogMapper;
import cn.sixmillions.sb3demo.service.IOssLogSerivice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OssLogServiceImpl extends ServiceImpl<OssLogMapper, OssLog> implements IOssLogSerivice {

    @Override
    @Async
    public void asyncSaveLog(OssLog ossLog) {
        this.save(ossLog);
    }
}
