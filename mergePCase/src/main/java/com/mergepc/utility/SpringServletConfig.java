package com.mergepc.utility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
@EnableWebMvc
public class SpringServletConfig {
    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver vr = new InternalResourceViewResolver();
        vr.setPrefix("/WEB-INF/");
        vr.setSuffix(".jsp");
        return vr;
    }
	/*
	 * @Bean
	 * 
	 * @PostConstruct public PropertySourcesPlaceholderConfigurer
	 * propertySourcesPlaceholderConfigurer() { PropertySourcesPlaceholderConfigurer
	 * properties = new PropertySourcesPlaceholderConfigurer();
	 * properties.setLocation(new
	 * FileSystemResource("C:\\properties\\dfc.properties"));
	 * properties.setIgnoreResourceNotFound(false); return properties; }
	 */
}