package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public class FileUtil {

    /**
     * 递归获取指定目录下的所有文件的url
     * @param file 目录的的File对象
     * @return 该目录下文件的urlList
     */
    public static List<String> getAllFileUrl(File file) {
        //获取该目录下所有文件
        File[] listFiles = file.listFiles();

        List<String> urlList = new ArrayList<>();
        if (listFiles != null && listFiles.length > 0){
            for (File listFile : listFiles) {
                if(listFile.isFile()){
                    urlList.add(listFile.getAbsolutePath());
                } else if(listFile.isDirectory()){
                    // 如果子目录还有，继续递归调用
                    urlList.addAll(getAllFileUrl(listFile));
                }
            }
        }
        return urlList;
    }

    /**
     * 递归匹配通配符获取目录下的所有文件的url
     * @param url 目录路径
     * @return urlList
     */
    public static List<String> getAllFileUrlByRegex(String url) {
        //获取目录
        String dirPath = url.substring(0, url.lastIndexOf(File.separatorChar));
        //获取要匹配的文件名(包含通配符)
        String regFileName = url.substring(url.lastIndexOf(File.separatorChar)+1);
        Pattern p = Pattern.compile(changeToRegex(regFileName));

        //获取目录下所有文件的url
        List<String> allFileUrl = getAllFileUrl(new File(dirPath));
        List<String> urlList = new ArrayList<>();
        for (String fileUrl : allFileUrl) {
            boolean b = p.matcher(fileUrl.substring(fileUrl.lastIndexOf(File.separatorChar)+1)).matches();
            if(b) {
                urlList.add(fileUrl);
            }
        }

        return urlList;
    }

    /**
     * 将含有通配符*、?的文件名转成正则表达式
     * @param fileName 文件名
     * @return 对应的正则表达式
     */
    private static String changeToRegex(String fileName){
        fileName = fileName.replace(".", "\\.");
        fileName = fileName.replace("*", ".*");
        fileName = fileName.replace("?", ".?");
        return fileName;
    }
}
