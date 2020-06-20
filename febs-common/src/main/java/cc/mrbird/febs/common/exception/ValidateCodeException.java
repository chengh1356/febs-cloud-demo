package cc.mrbird.febs.common.exception;

/**
 * @Description  验证码类型异常
 * @Author 54045
 * @Date 2020-06-21 17:57
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = 7514854456967620043L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
