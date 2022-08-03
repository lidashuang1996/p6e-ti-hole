package club.p6e.ti.hole.leader.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * JSON 的帮助类
 * @author lidashuang
 * @version 1.0
 */
public final class JsonUtil {

    /** 全局创建 JSON 序列化和反序列化的对象 */
    private static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))))
            .registerTypeAdapter(LocalDate.class,
                    (JsonSerializer<LocalDate>) (src, typeOfSrc, context) ->
                            new JsonPrimitive(src.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) -> {
                        final String datetime = json.getAsJsonPrimitive().getAsString();
                        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    }).registerTypeAdapter(LocalDate.class,
                    (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> {
                        final String datetime = json.getAsJsonPrimitive().getAsString();
                        return LocalDate.parse(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    })
            .disableHtmlEscaping().create();

    /**
     * 序列化对象
     * @param o 对象
     * @return 序列化内容
     */
    public static String toJson(Object o) {
        return o == null ? null : GSON.toJson(o);
    }

    /**
     * 反序列化 JSON 到对象
     * @param json json 内容
     * @param tClass 对象类型
     * @param <T> 类型
     * @return 对象
     */
    public static <T> T fromJson(String json, Class<T> tClass) {
        return GSON.fromJson(json, tClass);
    }

    /**
     * 反序列化为 Type
     * @param json 反序列化内容
     * @param typeOfT 反序列化类
     * @param <T> 反序列化类型
     * @return 反序列化对象
     */
    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    /**
     * 反序列化为 MAP 对象
     * @param json 待反序列化的内容
     * @param keyClass KEY CLASS
     * @param valueClass VALUE CLASS
     * @param <T> KEY 的类型
     * @param <W> VALUE 的类型
     * @return 反序列化的内容
     */
    public static <T, W> Map<T, W> fromJsonToMap(String json, Class<T> keyClass, Class<W> valueClass) {
        return GSON.fromJson(json, new TypeToken<Map<T, W>>() {}.getType());
    }

}
