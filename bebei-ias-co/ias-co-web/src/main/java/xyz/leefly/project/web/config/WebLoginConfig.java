package xyz.leefly.project.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Configuration
public class WebLoginConfig implements WebMvcConfigurer {

    private static Logger logger = LoggerFactory.getLogger(WebLoginConfig.class);

    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截规则：除了login，其他都拦截判断
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/assets/**")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/login");
    }


    public class LoginInterceptor extends HandlerInterceptorAdapter {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
            logger.info("------preHandle------");
            //获取session
            HttpSession session = request.getSession(true);
            //判断用户ID是否存在，不存在就跳转到登录界面
//            if (session.getAttribute("userName") == null) {
//                logger.info("------:跳转到login页面！");
//                response.sendRedirect(request.getContextPath() + "/login");
//                return false;
//            } else {
                return true;
//            }
        }


    }

}
