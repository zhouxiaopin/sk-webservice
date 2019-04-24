package cn.sk.server.config;

import cn.sk.server.service.IUserService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    @Autowired
    IUserService userService;

    /**
     * 此方法作用是改变项目中服务名的前缀名，此处127.0.0.1或者localhost不能访问时，请使用ipconfig查看本机ip来访问
     * 此方法被注释后:wsdl访问地址为http://127.0.0.1:8080/services/userService?wsdl
     * 去掉注释后：wsdl访问地址为：http://127.0.0.1:8080/soap/userService?wsdl
     * @return
     */
    @SuppressWarnings("all")
    @Bean
    public ServletRegistrationBean cxfDispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(), "/soap/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Autowired
    private SpringBus springBus;

    /** JAX-WS
     * 站点服务
     * **/
    @Bean
    public EndpointImpl userServiceEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus, userService);// 绑定要发布的服务实现类
        endpoint.publish("/userService"); // 接口访问地址
        return endpoint;
    }

//    //hello
//    @Bean
//    public Endpoint hello() {
//        EndpointImpl endpoint = new EndpointImpl(springBus(), new HelloImpl());// 绑定要发布的服务实现类
//        endpoint.publish("/hello"); // 接口访问地址
//        return endpoint;
//    }
}
