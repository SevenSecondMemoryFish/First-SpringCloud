# First-SpringCloud
项目只是学习使用

### Sentinel 使用
> sentinel 是阿里出的关于服务熔断、服务降级、服务治理等的一个防护组件。[Sentinel](https://github.com/alibaba/Sentinel) Github

1.本项目中，创建的服务`cloudalibaba-sentinel-service8401`,使用了`Sentinel`进项了防护
```yaml
# yml配置
spring:
    sentinel:
      transport:
        # sentinel 地址
        dashboard: localhost:8858
        port: 8719
```
```xml
<!--  sentinel 和 canos 配合使用   -->
<dependencies>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
        <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.cloud</groupId>
         <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    </dependency>
    <dependency>
        <groupId>com.alibaba.csp</groupId>
        <artifactId>sentinel-datasource-nacos</artifactId>
    </dependency>
</dependencies>
```
> 下载安装，目前我是用的是`docker`安装
```yaml
# 下载 sentinel
docker pull bladex/sentinel-dashboard
```
> 运行sentinel
```yaml
# 使用docker 运行sentinel，端口号为8858
docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard
```
> 安装完成，以及运行完成后，可以访问`http:localhost:8858`，即可看到`Sentinel`的界面，密码和账户都为：sentinel

**注意：**
- 当设置流控规则的时候
    - 设置的是`/testA` 关联`/testB`的时候，如果资源`/testB`资源访问达到阀值的时候。`/testA`则会挂掉。而不是`/testB`
    
### seata 使用

```yaml
# 使用自定义registry.conf,将宿主机的/Users/wsj/Desktop/resources1文件全部映射到镜像/root/seata-config/下
docker run --name seata-server1 \
        -p 2309:2309 \
        -e SEATA_CONFIG_NAME=file:/root/seata-config/registry \
        -v /Users/wsj/Desktop/resources1:/root/seata-config/ \
        seataio/seata-server
```
```yaml
# registry.conf的配置
registry {
    # 我是用的是nacos作为服务的发现，所以配置的是nacos
    type = "nacos"
    nacos {
        # 服务的名称 随便你配置
        application="seata-server"
        # nacos的地址
        serverAddr="192.168.31.42:8848"
        # 分组名称
        group="SEATA_GROUP"
    }
    # 如果需要自定义配置file.conf的时候
    config {
        type="file"
         file {
            # 自定义的file.conf的路径即可
            name = "file:/root/seata-config/file.conf"
          }
    }
}

# file.conf下的配置修改
store {
    mode = "db"
    db {
        # 引使用的是mysql8.0 所以使用他
        driverClassName = "com.mysql.cj.jdbc.Driver"
        # mysql 的url。记住不能使用127.0.0.1,或者localhost
        url = "jdbc:mysql://192.168.31.42:3306/seata?useSSL=true&serverTimezone=GMT"
        # mysql的名称
        user = "mysql的登录名称"
        password = "mysql的密码"
    }
}
```
```sql
/// 回滚日志表
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
```