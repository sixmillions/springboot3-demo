# Spring Boot

# Hello World

> localhost:9289/hello

# DEV

## MyBatis-Plus

> https://baomidou.com/

```sql
create table ts_student
(
    id   bigserial primary key,
    name varchar(100),
    age  int
)
```

## S3

> https://mp.weixin.qq.com/s/QSlCLa01F3Hf3Seq3f6rHw  
> https://blog.csdn.net/weixin_40461281/article/details/124971280

## QA

### 程序启动警告

OpenJDK 64-Bit Server VM warning: Sharing is only supported for boot loader classes because bootstrap classpath has been
appended

> https://blog.csdn.net/weixin_43970743/article/details/123743580

### S3上传警告

```text
com.amazonaws.util.Base64                : JAXB is unavailable. Will fallback to SDK implementation which may be less performant.If you are using Java 9+, you will need to include javax.xml.bind:jaxb-api as a dependency.
```

JDK9之后, JAXB-API 不在默认加载路径之中, 应该将其放到pom中

> https://stackoverflow.com/questions/66920124/jaxb-warning-is-shown-while-starting-my-spring-boot-application


