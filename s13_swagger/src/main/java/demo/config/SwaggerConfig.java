package demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开启Swagger2
public class SwaggerConfig {
    @Bean
    Docket docket() { //配置Docket
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("demo.controller")) //需要扫描的controller的位置
                .paths(PathSelectors.any())
                .build().apiInfo( //构建的文档的信息
                        new ApiInfoBuilder()
                        .description("swagger demo接口测试文档")
                        .contact(new Contact("汪福斌", "", "fubinsama@qq.com"))
                        .version("v1.0")
                        .title("API测试文档")
                        .license("Apache2.0")
                        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                        .build());
    }
}
