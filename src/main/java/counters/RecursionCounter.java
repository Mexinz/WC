package counters;

import bean.ErrorResult;
import bean.Result;
import enums.ErrorEnum;
import util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Mexinz
 * @Date: 2019/9/24
 */
public class RecursionCounter{

    /**
     * 统计文件的对象
     */
    private Counter counter;

    public RecursionCounter(Counter counter) {
        this.counter = counter;
    }

    /**
     * 获取递归处理后的处理结果List
     * @param url 文件路径
     * @return 结果集
     */
    public List<Result> getResultList(String url) {

        if(url == null || "".equals(url)){
            return Collections.singletonList(ErrorResult.getErrorResult(ErrorEnum.WRONG_PARAM));
        }

        File file = new File(url);
        //文件目录中包含通配符
        if(url.contains("*") || url.contains("?")){
            //递归匹配通配符获取目录下的所有文件的url
            List<String> allFileUrl = FileUtil.getAllFileUrlByRegex(url);
            List<Result> results = new ArrayList<>();
            for (String fileUrl : allFileUrl) {
                Result result = counter.analyseFile(fileUrl);
                results.add(result);
            }
            return results;
        }else{
            if(file.isFile()){
                Result result = counter.analyseFile(url);
                return Collections.singletonList(result);
            }else if(file.isDirectory()){
                //递归获取目录下的所有文件的url
                List<String> allFileUrl = FileUtil.getAllFileUrl(file);
                List<Result> results = new ArrayList<>();
                for (String fileUrl : allFileUrl) {
                    Result result = counter.analyseFile(fileUrl);
                    results.add(result);
                }
                return results;
            }else{
                return Collections.singletonList(ErrorResult.getErrorResult(ErrorEnum.FILE_NOT_FOUND));
            }
        }
    }
}
