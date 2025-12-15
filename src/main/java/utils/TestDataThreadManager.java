package utils;


import java.util.Map;

public class TestDataThreadManager {
    private static ThreadLocal<Map<String, String>> threadData = new ThreadLocal<>();

    public static void setData(Map<String, String> data) {
        threadData.set(data);
    }

    public static Map<String, String> getData() {
        return threadData.get();
    }

    public static void removeData() {
        threadData.remove();
    }
}

