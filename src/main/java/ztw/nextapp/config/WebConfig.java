package ztw.nextapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // specify the URL pattern you want to allow
                .allowedOrigins("http://localhost:5173/**")
                .allowedOrigins("https://dev-88760044.okta.com/**")// specify the origin you want to allow
                .allowedMethods("GET", "POST", "PUT", "DELETE") // specify the HTTP methods you want to allow
                .allowedHeaders("*") // specify the headers you want to allow
                .allowCredentials(true); // specify if you want to allow credentials (e.g., cookies)
    }
}
