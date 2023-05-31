package cn.sixmillions.sb3demo;

import cn.sixmillions.sb3demo.entity.Student;
import cn.sixmillions.sb3demo.mapper.StudentMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class DemoApplicationTests {

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void insertStudent() {
        int insert = studentMapper.insert(Student.builder().name("six").age(18).build());
        log.info("insert student result: {}", insert);
        assert insert == 1;
    }

}
