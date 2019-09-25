package counters;

import bean.ErrorResult;
import bean.Result;
import bean.SuccessResult;
import enums.ErrorEnum;
import util.Constants;

import java.io.*;

/**
 * @Author: Mexinz
 * @Date: 2019/9/24
 */
public class ComplexCounter implements Counter{

    /**
     * "-a" 统计文件的复杂数据：代码行 / 空行 / 注释行
     * @param url 文件的全路径
     * @return 处理结果
     */
    @Override
    public Result analyseFile(String url) {

        if(url == null || "".equals(url)){
            return  ErrorResult.getErrorResult(ErrorEnum.WRONG_PARAM);
        }

        int line = 0;
        int emptyLine = 0;
        int codeLine = 0;
        int annotationLine = 0;
        boolean annotationIsStart = false;
        int annotationStartLine = 0;
        File file = new File(url);
        if(file.isFile()){
            try (InputStream inputStream = new FileInputStream(file);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))){
                String str;
                while((str = reader.readLine()) != null){
                    //读取的行不为空，总行数加1
                    line ++;
                    str = str.replaceAll("\\s", "");
                    //判断是否为空行
                    if("".equals(str) || "{".equals(str) || "}".equals(str)){
                        if(str.startsWith("") && annotationIsStart){
                            //这里是处理注释块中含有空行的情况,注释块中的空行是注释行，在这里不计入空行
                            continue;
                        }
                        emptyLine++;
                    } else if (str.startsWith("//") || str.startsWith("}//")||str.startsWith("{//")){
                        //单行注释
                        annotationLine ++;
                    } else if (str.startsWith("/*")){
                        //注释块
                        annotationIsStart = true;
                        //防止注释块里有“/*”影响判断
                        if(annotationStartLine == 0){
                            annotationStartLine = line;
                        }
                    } else if(str.startsWith("*/")){
                        annotationLine += line - annotationStartLine+1;
                        annotationIsStart = false;
                        annotationStartLine = 0;
                    }

                }
                //代码行等于总行数-注释行-空行
                codeLine = line-annotationLine-emptyLine;
                } catch (IOException e) {
                e.printStackTrace();
            }

            SuccessResult result = new SuccessResult();
            result.setFileName(url);
            result.addResult(Constants.total, line);
            result.addResult(Constants.code, codeLine);
            result.addResult(Constants.annotation, annotationLine);
            result.addResult(Constants.empty, emptyLine);

            return result;
        }else{
            //文件不存在
            return ErrorResult.getErrorResult(ErrorEnum.FILE_NOT_FOUND);
        }
    }
}
