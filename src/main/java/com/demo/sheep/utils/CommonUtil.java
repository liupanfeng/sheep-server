package com.demo.sheep.utils;


import com.demo.sheep.constant.CommonConstant;
import com.demo.sheep.constant.UploadModeEnum;
import com.demo.sheep.pojo.param.PageParam;
import com.demo.sheep.pojo.view.PageView;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class CommonUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    private static final Pattern phonePattern = Pattern.compile("[1][0-9]{10}");
    private static final Pattern emailPattern = Pattern.compile("[a-zA-Z0-9_\\-]+@[a-zA-Z0-9]+\\.[a-z]+");

    /**
     * 判断是不是正经的手机号
     */
    public static boolean checkPhone(String phone) {
        return phonePattern.matcher(phone).matches();
    }

    /**
     * 判断是不是正经的邮箱
     */
    public static boolean checkEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    /**
     * 生成UUID
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成验证码
     */
    public static String generateVerifyCode() {
        int index = RandomUtils.nextInt(0, 5);
        String numeric = RandomStringUtils.randomNumeric(10);
        return numeric.substring(index, index + 6);
    }


    /**
     * 处理分页的参数
     */
    public static void handlePageParams(PageParam param) {
        if (param == null) {
            param = new PageParam();
        }
        Integer pageNum = param.getPageNum();
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        Integer pageSize = param.getPageSize();
        if (pageSize == null || pageSize < 1) {
            pageSize = 30;
            param.setPageSize(pageSize);
        }
        param.setStartIndex((pageNum - 1) * pageSize);
    }

    /**
     * 返回一个空的page 结果
     */
    public static PageView emptyPageView() {
        PageView pageView = new PageView();
        pageView.setCount(0);
        pageView.setElements(Collections.emptyList());
        return pageView;
    }

    /**
     * 格式化时间
     */
    public static String formatDate(Date date, String format) {
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 把user放到request中
     */
    public static void putUserIdInRequest(HttpServletRequest request, Object user) {
        request.setAttribute(CommonConstant.USERID_KEY, user);
    }

    /**
     * 从request中取出user
     */
    public static Integer getUserIdFromRequest(HttpServletRequest request) {
        return (Integer) request.getAttribute(CommonConstant.USERID_KEY);
    }

    /**
     * 从request中取出token
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader(CommonConstant.TOKEN_KEY);
    }

    /**
     * 根据主键和初始化时间，获取uuid的前8位，用来当作云存储的路径，防止暴露主键
     *
     * @param id       唯一id
     * @param initDate 初始化时间
     * @return 生成的路径
     */
    public static String generatePath(String id, Date initDate) {
        return UUID.nameUUIDFromBytes((id + initDate.toString()).getBytes()).toString().substring(0, 8);
    }

    /**
     * double保留2位小数
     *
     * @param num
     * @return
     */
    public static Double get2ScaleOfDouble(Double num) {
        String format = new DecimalFormat("#.00").format(num);
        return Double.parseDouble(format);
    }

    public static String saveFile(String uploadFilePath, UploadModeEnum uploadMode, MultipartFile file) throws IOException {
        //获取当前日期
        Date date = new Date();
        String yyyyMM = CommonUtil.formatDate(date, "yyyy-MM");
        String dd = CommonUtil.formatDate(date, "dd");
        //保存到本地
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return null;
        }
        int index = filename.lastIndexOf(".");
        if (index < 1) {
            return null;
        }
        filename = date.getTime() + filename.substring(index);
        String relativePath = String.format(uploadMode.path(), yyyyMM, dd) + filename;
        File dest = new File(uploadFilePath + relativePath);
        if (!dest.getParentFile().exists()) {
            boolean b = dest.getParentFile().mkdirs();
            if (!b) {
                log.info("{}父文件夹创建失败", dest.getAbsolutePath());
            }
        }
        file.transferTo(dest);
        return relativePath;
    }

}
