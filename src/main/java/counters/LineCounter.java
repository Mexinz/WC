package counters;

import bean.ErrorResult;
import bean.Result;
import bean.SuccessResult;
import enums.ErrorEnum;
import util.Constants;

import java.io.*;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public class LineCounter implements Counter {

    /**
     * "-l" 统计文件的行数
     * @param url 文件的全路径
     * @return 包含行数的结果
     */
    @Override
    public Result analyseFile(String url) {
        if (url == null || "".equals(url)) {
            return ErrorResult.getErrorResult(ErrorEnum.WRONG_PARAM);
        }

        int lineNum = 0;
        File file = new File(url);
        if(file.isFile()){
            try (InputStream inputStream = new FileInputStream(file);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                while((reader.readLine())!=null) {
                    lineNum ++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new SuccessResult().addResult(Constants.line, lineNum).setFileName(url);

        }else{
            //文件不存在
            return ErrorResult.getErrorResult(ErrorEnum.FILE_NOT_FOUND);
        }
    }
}
