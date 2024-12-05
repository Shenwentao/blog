package cn.xiaqileyu.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 *
 * @author swt
 * @date 2020/6/24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 是否启用swagger文档
     */
    @Value("${swagger.enable}")
    private boolean enable;

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .apiInfo(apiInfo())
                .select()
                // 这里配置要扫描的包,接口在哪个包就配置哪个包
                .apis(RequestHandlerSelectors.basePackage("cn.xiaqileyu.blog.controller"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("博客系统请求接口")
                //创建人
                .contact(new Contact("Shen","",""))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
