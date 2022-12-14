package club.p6e.ti.hole.follower.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 浏览器跨域处理过滤器
 * @author lidashuang
 * @version 1.0
 */
@WebFilter
public class CrossDomainFilterController implements Filter {

    /**
     * 注入日志系统
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CrossDomainFilterController.class);

    /**
     * 自定义请求头
     */
    private static class MyHttpServletRequestWrapper extends HttpServletRequestWrapper {

        /** 缓存用户的请求头信息的对象 */
        private final Map<String, String> myHeader = new HashMap<>();

        /** 跨域头名称 */
        private static final String CROSS_DOMAIN_HEADER_NAME = "Ti-Cross-Domain";
        /** 跨域头内容 */
        private static final String CROSS_DOMAIN_HEADER_CONTENT = "existence";

        /**
         * 构造方法
         * @param request HttpServletRequest 对象
         */
        MyHttpServletRequestWrapper(final HttpServletRequest request) {
            super(request);
            // 初始化请求头
            final Enumeration<String> headerNames =  request.getHeaderNames();
            if (headerNames != null) {
                // 迭代读取请求头
                while (headerNames.hasMoreElements()) {
                    final String name = headerNames.nextElement();
                    final String value = request.getHeader(name);
                    myHeader.put(name, value);
                }
            }
        }

        /**
         * 设置跨域标记请求头的方法
         */
        void setCrossDomain() {
            myHeader.put(CROSS_DOMAIN_HEADER_NAME.toLowerCase(), CROSS_DOMAIN_HEADER_CONTENT);
        }

        /**
         * 判断是否处理了跨域
         * @return 是否处理跨域的状态
         */
        boolean isCrossDomainHandle() {
            final String crossDomain = myHeader.get(CROSS_DOMAIN_HEADER_NAME.toLowerCase());
            if (crossDomain == null) {
                return false;
            } else {
                return CROSS_DOMAIN_HEADER_CONTENT.equalsIgnoreCase(crossDomain);
            }
        }

        /**
         * 重写获取请求头的对应内容的方法
         * @param name 获取请求头内容的头参数
         * @return 请求头对应的内容
         */
        @Override
        public String getHeader(final String name) {
            return myHeader.get(name.toLowerCase());
        }

        /**
         * 重写获取请求头名称的方法
         * @return 请求头名称集合的迭代器
         */
        @Override
        public Enumeration<String> getHeaderNames() {
            return Collections.enumeration(myHeader.keySet());
        }
    }

    /**
     * 初始化过滤器时候的方法回调
     * @param filterConfig 过滤的配置对象
     */
    @Override
    public void init(final FilterConfig filterConfig) {
        LOGGER.info("Filter [ CrossDomainFilter ] init complete ... ");
    }

    /**
     * 过滤器内容的处理方法
     * @param servletRequest 服务请求头对象
     * @param servletResponse 服务返回头对象
     * @param filterChain 内部代理对象
     * @throws IOException 请求头和返回头处理时候可能出现的 IO 异常
     * @throws ServletException 服务处理时候可能出现的异常
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse, final FilterChain filterChain)
            throws IOException, ServletException {
        // 获取请求头和返回头
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 设置 Access-Control-Allow-Origin 内容为请求头 origin 的内容
        // 如果不存在 origin 的请求头，那么设置为 *
        String origin = "*";
        final Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                final String element = enumeration.nextElement();
                if ("origin".equalsIgnoreCase(element)) {
                    origin = request.getHeader(element);
                }
            }
        }

        final MyHttpServletRequestWrapper myHttpServletRequestWrapper = new MyHttpServletRequestWrapper(request);
        if (!myHttpServletRequestWrapper.isCrossDomainHandle()) {
            // 头部添加 cross-Domain 为 existence 标记这个请求已经做了跨域处理
            myHttpServletRequestWrapper.setCrossDomain();
            response.setHeader("Access-Control-Allow-Origin", origin);
            response.setHeader("Access-Control-Allow-Methods", "PUT, POST, GET, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            // 设置允许 cookie
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Depth, "
                    + "User-Agent, X-File-Size, X-Requested-With, X-Requested-By, If-Modified-Since, "
                    + "X-File-Name, X-File-Type, Cache-Control, Origin, Client");
        }

        // 是否为 OPTIONS 方法
        // 如果请求的方法为 options 那么将立即返回数据并设置返回头为 200
        // 关 HTTP OPTIONS 请求的唯一响应是 200 ，但是有一些情况，例如当内容长度为 0 时，一个 204 会更合适
        if (HttpMethod.OPTIONS.matches(request.getMethod().toUpperCase())) {
            // 返回 200 或者 204
            response.setStatus(200);
        } else {
            // 进入内部执行后续的方法
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * 摧毁过滤器的时候的回调方法
     */
    @Override
    public void destroy() {
        LOGGER.info("Filter [ CrossDomainFilter ] destroy complete ... ");
    }

}
