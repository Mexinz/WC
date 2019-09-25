package util;

import counters.*;

/**
 * @Author: Mexinz
 * @Date: 2019/9/24
 */
public class CounterFactory {

    private CounterFactory() {

    }

    /**
     * 单例
     */
    private static CharacterCounter characterCounter;
    private static LineCounter lineCounter;
    private static WordCounter wordCounter;
    private static ComplexCounter complexCounter;

    public static Counter getCounter(String param){
        param = param.toLowerCase();
        switch (param) {
            case "-c" :
                if (characterCounter == null) {
                    synchronized (CharacterCounter.class) {
                        if (characterCounter == null) {
                            characterCounter = new CharacterCounter();
                        }
                    }
                }
                return characterCounter;
            case "-l" :
                if (lineCounter == null) {
                    synchronized (LineCounter.class) {
                        if (lineCounter == null) {
                            lineCounter = new LineCounter();
                        }
                    }
                }
                return lineCounter;
            case "-w" :
                if (wordCounter == null) {
                    synchronized (WordCounter.class) {
                        if (wordCounter == null) {
                            wordCounter = new WordCounter();
                        }
                    }
                }
                return wordCounter;
            case "-a" :
                if (complexCounter == null) {
                    synchronized (ComplexCounter.class) {
                        if (complexCounter == null) {
                            complexCounter = new ComplexCounter();
                        }
                    }
                }
                return complexCounter;
            default : return null;
        }
    }
}
