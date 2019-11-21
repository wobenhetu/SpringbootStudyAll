访问Hystrix Dashboard的界面：
http://localhost:9000/hystrix

Hystrix Dashboard共支持三种不同的监控方式：

**默认的集群监控： http://turbine-hostname:port/turbine.stream    
指定的集群监控： http://turbine-hostname:port/turbine.stream?cluster=[clusterName]    
单体应用的监控： http://hystrix-app:port/actuator/hystrix.stream**    
页面上面的几个参数局域

最上面的输入框： 输入上面所说的三种监控方式的地址，用于访问具体的监控信息页面。     
**Delay： 该参数用来控制服务器上轮询监控信息的延迟时间，默认2000毫秒。         
Title： 该参数对应头部标题Hystrix Stream之后的内容，默认会使用具体监控实例的Url。**       
