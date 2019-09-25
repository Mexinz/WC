package enums;

/**
 * @Author: Mexinz
 * @Date: 2019/9/22
 */
public enum  ErrorEnum {

    /**
     * 参数错误，路径解析失败或者为空等
     */
    WRONG_PARAM("参数错误"),
    /**
     * 该url下找不到文件
     */
    FILE_NOT_FOUND("找不到文件");

    private String message;

    ErrorEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
