# First-SpringCloud
项目只是学习使用

### Sentinel 使用
> sentinel 是阿里出的关于服务熔断、服务降级、服务治理等的一个防护组件。[Sentinel](https://github.com/alibaba/Sentinel)

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
```docker
# 下载 sentinel
docker pull bladex/sentinel-dashboard
```
> 运行sentinel
```docker
# 使用docker 运行sentinel，端口号为8858
docker run --name sentinel -d -p 8858:8858 -d bladex/sentinel-dashboard
```
> 安装完成，以及运行完成后，可以访问`http:localhost:8858`，即可看到`Sentinel`的界面，密码和账户都为：sentinel