package counters;

import bean.ErrorResult;
import bean.Result;
import bean.SuccessResult;
import enums.ErrorEnum;
import util.Constants;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public class WordCounter implements Counter{
    /**
     * "-w" 统计单词数
     * @param url 文件的全路径
     * @return 包含单词数的处理结果
     */
    @Override
    public Result analyseFile(String url) {
        if (url == null || "".equals(url)) {
            return ErrorResult.getErrorResult(ErrorEnum.WRONG_PARAM);
        }

        int wordNum = 0;
        File file = new File(url);
        if(file.isFile()){
            Pattern p = Pattern.compile("\\b[a-zA-Z]+\\b");
            try (InputStream inputStream = new FileInputStream(file);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                String str;
                while((str = reader.readLine()) != null) {
                    Matcher m = p.matcher(str);
                    while (m.find()) {
                        wordNum ++;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new SuccessResult().addResult(Constants.word, wordNum).setFileName(url);

        }else{//文件不存在
            return ErrorResult.getErrorResult(ErrorEnum.FILE_NOT_FOUND);
        }
    }
}
