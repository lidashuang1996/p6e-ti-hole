package club.p6e.ti.hole.follower.action;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 动作执行接口
 * @author lidashuang
 * @version 1.0
 */
public interface Action {

    /**
     * 注册的名称
     * @return 注册的名称
     */
    public String name();

    /**
     * 执行方法
     * @param metadata 执行的元数据
     */
    public void execute(MetadataAction metadata);

    /**
     * 生成 XMLHttpRequest 请求的脚本代码
     * @param baseUrl 基础的 URL 地址
     * @param map 请求的参数
     * @return 生成的脚本内容
     */
    public default String generateXMLHttpRequestScript(String baseUrl, String ...map) {
        return generateUrlXMLHttpRequestScript(baseUrl + "/ti/hole/follower/callback", map);
    }

    /**
     * 生成 XMLHttpRequest 请求的脚本代码
     * @param baseUrl 基础的 URL 地址
     * @param map 请求的参数
     * @return 生成的脚本内容
     */
    public default String generateXMLHttpRequestScript(String baseUrl, Map<String, String> map) {
        return generateUrlXMLHttpRequestScript(baseUrl + "/ti/hole/follower/callback", map == null ? new HashMap<>(0) : map);
    }

    /**
     * 生成 XMLHttpRequest 请求的脚本代码
     * @param url 请求的 url 地址
     * @param map 请求的参数
     * @return 生成的脚本内容
     */
    public default String generateUrlXMLHttpRequestScript(String url, String ...map) {
        if (map != null && map.length > 0) {
            final int len = (int) Math.floor(map.length / 2D);
            final Map<String, String> m = new HashMap<>(len);
            for (int i = 0; i < len; i++) {
                m.put(map[i * 2], map[i * 2 + 1]);
            }
            return generateUrlXMLHttpRequestScript(url, m);
        } else {
            return generateUrlXMLHttpRequestScript(url, new HashMap<>(0));
        }
    }

    /**
     * 生成 XMLHttpRequest 请求的脚本代码
     * @param url 请求的 url 地址
     * @param map 请求的参数
     * @return 生成的脚本内容
     */
    public default String generateUrlXMLHttpRequestScript(String url, Map<String, String> map) {
        final long time = System.currentTimeMillis();
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("const call_fun_");
        stringBuilder.append(time);
        stringBuilder.append("=function(){var e=new XMLHttpRequest;e.open('GET','");
        stringBuilder.append(url);
        stringBuilder.append("?_='+(new Date).getTime()");
        if (map.size() > 0) {
            stringBuilder.append("+");
            stringBuilder.append("'");
        }
        for (final String key : map.keySet()) {
            stringBuilder.append("&");
            stringBuilder.append(key);
            stringBuilder.append("=");
            stringBuilder.append(URLEncoder.encode(map.get(key), StandardCharsets.UTF_8));
        }
        if (map.size() > 0) {
            stringBuilder.append("'");
        }
        stringBuilder.append(",!0),e.send()};call_fun_");
        stringBuilder.append(time);
        stringBuilder.append("();");
        return stringBuilder.toString();
    }

}
