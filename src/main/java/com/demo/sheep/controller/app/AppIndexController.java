package com.demo.sheep.controller.app;

import com.demo.sheep.constant.*;
import com.demo.sheep.pojo.param.MarketParam;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.param.UploadFileParam;
import com.demo.sheep.pojo.table.Market;
import com.demo.sheep.pojo.table.User;
import com.demo.sheep.pojo.view.Result;
import com.demo.sheep.service.*;
import com.demo.sheep.utils.CommonUtil;
import com.demo.sheep.utils.VerifyCodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("app")
public class AppIndexController {

    @Resource
    private UserService userService;
    @Resource
    private FileRelateService fileRelateService;

    /**
     * 登录
     */
    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(String phone, String verifyCode) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(verifyCode)) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        boolean b = userService.checkVerifyCode(phone, VerifyCodeModeEnum.REGISTER.name(), verifyCode);
        if (!b) {
            return Result.failure(ResultCode.VERIFY_CODE_IS_INVALID);
        }
        //删除验证码 TODO

        User user = userService.getUserByPhone(phone);
        //登录成功，生成token，并存放到redis
        String token = userService.generateToken(user, UserLoginTypeEnum.APP);
        //更新最近登录时间
        userService.updateUserLastDateById(user.getUserId());
        //返回前端需要的数据
        Map<String, String> map = new HashMap<>(1);
        map.put("token", token);
        return Result.success(map);

    }

    /**
     * 图片验证码
     */
    @RequestMapping(value = "verify/code/image", method = RequestMethod.POST)
    @ResponseBody
    public Result imageVerifyCode() throws IOException {
        Map<String, String> image = VerifyCodeUtil.createImage();
        String token = CommonUtil.generateUUID();
        userService.saveVerifyCode(token, VerifyCodeModeEnum.IMAGE.name(), image.remove("code").toLowerCase());
        image.put("uuid", token);
        return Result.success(image);
    }

    /**
     * 短信验证码
     */
    @RequestMapping(value = "verify/code/msg", method = RequestMethod.POST)
    @ResponseBody
    public Result msgVerifyCode(String phone, String mode, String uuid, String verifyCode) {
        if (StringUtils.isBlank(phone) || StringUtils.isBlank(uuid) || StringUtils.isBlank(verifyCode)) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        VerifyCodeModeEnum modeEnum = VerifyCodeModeEnum.check(mode);
        if (modeEnum == null) {
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        //判断图片验证码是否正确
        boolean b = userService.checkVerifyCode(uuid, VerifyCodeModeEnum.IMAGE.name(), verifyCode.toLowerCase());
        if (!b) {
            return Result.failure(ResultCode.VERIFY_CODE_IS_INVALID);
        }
        //删除图片验证码 TODO

        //判断是否过于频繁
        b = userService.checkIfCanSendVerifyCode(phone, modeEnum.name());
        if (!b) {
            return Result.failure(ResultCode.VERIFY_CODE_FREQUENTLY);
        }
        //生成短信验证码发送 TODO
        //String code = CommonUtil.generateVerifyCode();
        String code = "123456";
        userService.saveVerifyCode(phone, modeEnum.name(), code);
        return Result.success();
    }

    @PostMapping("file/upload")
    @ResponseBody
    public Result upload(HttpServletRequest request,UploadFileParam param) throws IOException {
        if (param.getFile() == null || param.getFile().isEmpty()) {
            return Result.failure(ResultCode.PARAM_NOT_COMPLETE);
        }
        UploadModeEnum check = UploadModeEnum.check(param.getUploadMode());
        if(check == null){
            return Result.failure(ResultCode.PARAM_IS_INVALID);
        }
        Integer orderScore = param.getOrder();
        if(orderScore == null){
            orderScore = 1;
        }
        Integer userId = CommonUtil.getUserIdFromRequest(request);
        Integer fileId = fileRelateService.save(userId, param.getFile(), check, orderScore);

        return fileId == null ? Result.failure(ResultCode.FAILED): Result.success(fileId);
    }

}
