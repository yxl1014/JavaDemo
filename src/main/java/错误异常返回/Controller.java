package 错误异常返回;

import java.util.Optional;

/**
 * @ClassName Controller
 * @Date 上午10:01 2023/10/15
 * @Author HarmmerRay
 * @Description TODO
 * @VESION 1.0
 **/
public class Controller {
    public Result test(String id) throws Exception {
        Optional.ofNullable(id).orElseThrow(() -> new Exception("sdfdsfdsfs"));
        return new Result();
    }
}
