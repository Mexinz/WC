package util;

/**
 * @Author: Mexinz
 * @Date: 2019/9/25
 */
public class Constants {
    // 测试非递归的文件路径
    public static String[] testFiles1 = {"testFiles\\Empty.java","testFiles\\Bracket.java","testFiles\\OneChar.java",
            "testFiles\\APairOfBracket.java","testFiles\\SingleLine.java","testFiles\\Word.java","testFiles\\WordWithBracket.java",
            "testFiles\\User.java","testFiles\\User.txt","testFiles\\User11.java","testFiles\\",null};
    // 测试递归的文件路径
    public static String[] testFiles2 = {"testFiles\\Empty.java","testFiles\\Bracket.java","testFiles\\OneChar.java",
            "testFiles\\APairOfBracket.java","testFiles\\SingleLine.java","testFiles\\Word.java","testFiles\\WordWithBracket.java",
            "testFiles\\User.java","testFiles\\User.txt","testFiles\\*.java","testFiles\\?ord*.java","testFiles\\User11.java",
            "testFiles\\",null};
    //测试4种统计方法
    public static String[] testParams = {"-c","-w","-l","-a"};

    public static String character = "字符个数";
    public static String line = "行数";
    public static String word = "单词数";
    public static String total = "总行数";
    public static String annotation = "注释行数";
    public static String code = "代码行数";
    public static String empty = "空行数";

}
