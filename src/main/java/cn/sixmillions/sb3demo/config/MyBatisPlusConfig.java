package cn.sixmillions.sb3demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus 配置类
 *
 * @author six
 */
@Configuration
@MapperScan("cn.sixmillions.sb3demo.mapper")
public class MyBatisPlusConfig {
}
