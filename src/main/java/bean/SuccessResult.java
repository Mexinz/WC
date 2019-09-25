package bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public class SuccessResult implements Result {

    /**
     * 文件全路径
     */
    private String fileName;
    /**
     * 要统计的指标
     */
    private Map<String,Integer> items = new HashMap<>();

    public SuccessResult addResult(String fileName, Integer itemResult){
        this.items.put(fileName,itemResult);
        return this;
    }

    public SuccessResult setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    @Override
    public void showResult() {
        System.out.println(fileName);
        for (Map.Entry<String,Integer> entry : items.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    @Override
    public String getResult() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String,Integer> entry : items.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue()).append("\r\n");
        }
        return stringBuilder.toString();
    }
}
