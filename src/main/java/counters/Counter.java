package counters;

import bean.Result;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public interface Counter {

    /**
     * 解析该路径指定的文件，返回处理结果
     * @param url 文件的全路径
     * @return 处理结果
     */
    Result analyseFile(String url);
}
