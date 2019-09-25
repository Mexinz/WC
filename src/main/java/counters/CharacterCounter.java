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
public class CharacterCounter implements Counter {

    /**
     * "-c" 返回文件的字符数
     * @param url 文件的全路径
     * @return 处理结果
     */
    @Override
    public Result analyseFile(String url) {
        if (url == null || "".equals(url)) {
            return ErrorResult.getErrorResult(ErrorEnum.WRONG_PARAM);
        }

        int charNum = 0;
        File file = new File(url);
        if(file.isFile()){
            try (InputStream inputStream = new FileInputStream(file);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                String str;
                while((str=reader.readLine()) != null){
                    //删除各种空格
                    str = str.replaceAll("\\s", "");
                    charNum += str.length();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new SuccessResult().addResult(Constants.character, charNum).setFileName(url);
        }else{
            //文件不存在
            return ErrorResult.getErrorResult(ErrorEnum.FILE_NOT_FOUND);
        }
    }
}
