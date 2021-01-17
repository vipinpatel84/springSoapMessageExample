package com.springSoap.webService;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfiguration extends WsConfigurerAdapter {
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext){
		MessageDispatcherServlet servlet= new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		servlet.setTransformSchemaLocations(true);
		return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/ws/*");
	}
	
	@Bean(name="SimpleMessage")
	public Wsdl11Definition wsdlDefinition(XsdSchema schema)
	{
		DefaultWsdl11Definition  defaultWsdl11Definition= new DefaultWsdl11Definition();
		defaultWsdl11Definition.setSchema(schema);
		defaultWsdl11Definition.setPortTypeName("SimpleMessageImpl");
		defaultWsdl11Definition.setLocationUri("http://localhost:8080/ws"); 	 
		defaultWsdl11Definition.setTargetNamespace("http://localhost:9090/demoServices");
		return defaultWsdl11Definition;
	}
	
	@Bean
	public XsdSchema endPoint() {
		return new SimpleXsdSchema(new ClassPathResource("SoapMessage.xsd"));
	}

}
