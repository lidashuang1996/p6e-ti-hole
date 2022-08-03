package club.p6e.ti.hole.follower.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 回调执行接口
 * @author lidashuang
 * @version 1.0
 */
@RestController
@RequestMapping("/ti/hole/follower")
public class CallbackController {

    @RequestMapping("/callback")
    public ResultContext def(HttpServletRequest request) {
        final Map<String, String> params = new HashMap<>(16);
        final String queryString = request.getQueryString();
        if (queryString != null && !queryString.isEmpty()) {
            final String[] qs = queryString.split("&");
            for (final String q : qs) {
                final String[] vs = q.split("=");
                if (vs.length == 2) {
                    params.put(vs[0], vs[1]);
                }
            }
        }
        return ResultContext.build(params);
    }

    /**
     * 结果上下文
     */
    public static class ResultContext {
        /** 默认的状态码 */
        private static final int DEFAULT_CODE = 0;

        /** 默认的消息内容 */
        private static final String DEFAULT_MESSAGE = "SUCCESS";

        /** 默认的数据内容 */
        private static final String DEFAULT_DATA = null;

        /** 状态码 */
        private final Integer code;

        /** 消息 */
        private final String message;

        /** 数据的对象 */
        private final Object data;

        /**
         * 编译方法
         * @return 结果上下文对象
         */
        public static ResultContext build() {
            return new ResultContext(DEFAULT_CODE, DEFAULT_MESSAGE, DEFAULT_DATA);
        }

        /**
         * 编译方法
         * @param data 数据的对象
         * @return 结果上下文对象
         */
        public static ResultContext build(Object data) {
            return new ResultContext(DEFAULT_CODE, DEFAULT_MESSAGE, data);
        }

        /**
         * 构造方法初始化
         * @param code 状态码
         * @param message 消息
         * @param data 数据的对象
         */
        private ResultContext(Integer code, String message, Object data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public Object getData() {
            return data;
        }
    }

}
