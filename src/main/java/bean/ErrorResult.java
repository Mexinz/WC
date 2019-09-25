package bean;

import enums.ErrorEnum;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public class ErrorResult implements Result {
    /**
     * 错误
     */
    private ErrorEnum errorEnum;

    private ErrorResult(ErrorEnum message) {
        this.errorEnum = message;
    }

    /**
     * 为错误类型获取相应的错误结果
     * @param errorEnum 错误类型
     * @return 错误结果（包含错误消息）
     */
    public static ErrorResult getErrorResult(ErrorEnum errorEnum) {
        return new ErrorResult(errorEnum);
    }


    @Override
    public void showResult() {
        System.out.println(errorEnum.getMessage());
    }

    @Override
    public String getResult() {
        return errorEnum.getMessage();
    }


}
