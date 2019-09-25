package main;

import bean.ErrorResult;
import bean.Result;
import counters.Counter;
import counters.RecursionCounter;
import util.CounterFactory;
import vo.Welcome;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: Mexinz
 * @Date: 2019/9/24
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--------------欢迎使用文本文件统计小demo--------------");
        System.out.println("本程序通过 【param】【fileName】的输入格式来使用");
        System.out.println("【param】支持的参数有以下这些：");
        System.out.println("1. -a：统计代码行、空行、注释行");
        System.out.println("2. -l：统计行数");
        System.out.println("3. -w：统计单词数");
        System.out.println("4. -c：统计字符数");
        System.out.println("5. -s：与以上四个参数中任一共同使用为使用递归统计功能，支持'*'、'?'通配符");
        System.out.println("另：输入-x并回车可以打开程序的图形化操作界面");
        System.out.println("最后，输入'q'退出程序");
        System.out.println("--------------注意：文件分隔符请使用'\\'--------------");

        Scanner scanner = new Scanner(System.in);
        String str;
        while(true){
            str = scanner.nextLine();
            if("q".equals(str)){
                System.exit(0);
            }
            String[] command = str.split(" ");
            //使用图形界面
            if("-x".equals(command[0])){
                Welcome.main(null);
            }else if("-s".equals(command[0])){
                //递归处理 -s -a/-l/-w/-c *.txt
                //获取相应的文件处理对象
                Counter counter = CounterFactory.getCounter(command[1]);
                //递归处理对象
                RecursionCounter recursionCounter = new RecursionCounter(counter);
                //获取处理的结果集
                List<Result> resultList = recursionCounter.getResultList(command[2]);
                for (Result result : resultList) {
                    result.showResult();
                }
            }else{
                //只有一个参数,-c/-w/-l/-a
                Counter counter = CounterFactory.getCounter(command[0]);
                if (counter == null) {
                    System.out.println("输入的参数错误!");
                } else {
                    Result result = counter.analyseFile(command[1]);
                    result.showResult();
                }
            }
        }
    }
}
