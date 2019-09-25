import bean.Result;
import counters.Counter;
import counters.RecursionCounter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import util.Constants;
import util.CounterFactory;

import java.util.List;

/**
 * @Author: Mexinz
 * @Date: 2019/9/25
 */
@RunWith(JUnit4.class)
public class CounterTest {

    /**
     * 非递归
     */
    @Test
    public void testSingle() {
        Counter counter;
        for (String url : Constants.testFiles1) {
            for (String param : Constants.testParams) {
                counter = CounterFactory.getCounter(param);
                if (counter == null) {
                    System.out.println("输入的参数错误!");
                } else {
                    counter.analyseFile(url).showResult();
                }
            }
        }
    }

    /**
     * 递归
     */
    @Test
    public void testRecursion() {
        RecursionCounter recursionCounter;
        List<Result> resultList;
        for (String url : Constants.testFiles2) {
            for (String param : Constants.testParams) {
                recursionCounter = new RecursionCounter(CounterFactory.getCounter(param));
                resultList = recursionCounter.getResultList(url);
                for (Result result : resultList) {
                    result.showResult();
                }
            }
        }
    }
}
