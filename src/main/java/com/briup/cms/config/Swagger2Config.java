package com.briup.cms.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: vanse
 * @Date: 2021/12/12-12-12-17:40
 * @Description：com.briup.cms.config
 * @version：1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket docket(){
        // 配置swagger2类型 获取api构建器
        // 扫描的包 包下的路径
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 引用自定义方法apiInfo()
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.briup.cms.controller")) // 那些包需要扫描
                .paths(PathSelectors.any())  // 包下的那些路径需要扫描
                .build()
                .securitySchemes(security()).securityContexts(securityContexts())
                .ignoredParameterTypes(HttpServletRequest.class, HttpServletResponse.class);

    }
// public static final ApiInfo DEFAULT = new ApiInfo("Api Documentation", "Api Documentation", "1.0", "urn:tos",
//          DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("cms")
                .description("看点咨询系统(宿州学院)")
                .version("1.0")
                .contact(new Contact("briup","http://www.briup.com","wangsidandan@gmail.com"))
                .build();
    }







    /**
     * 设置认证中显示的显示的基本信息
     */
    private List<ApiKey> security() {
        return Collections.singletonList(new ApiKey("Authorization", "token", "header"));
    }

    /**
     * 设置认证规则
     */
    private List<SecurityContext> securityContexts() {

        List<String> antPaths = new ArrayList<String>();
        antPaths.add("/resource/**");

        return Collections.singletonList(SecurityContext.builder().securityReferences(defaultAuth())
                .forPaths(antPathsCondition(antPaths)).build());
    }

    /**
     * 返回认证路径的条件
     */
    private Predicate<String> antPathsCondition(List<String> antPaths) {

        List<Predicate<String>> list = new ArrayList<>();

        antPaths.forEach(path -> list.add(PathSelectors.ant(path)));

        return Predicates.or(list);

    }

    /**
     * 设置认证的范围，以及认证的类型
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("Authorization", authorizationScopes));
    }
}
