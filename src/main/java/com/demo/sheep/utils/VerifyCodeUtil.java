package com.demo.sheep.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class VerifyCodeUtil {
    // 验证码字符集
    private static final char[] chars = {
            '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    // 验证码位数
    private static final int CODE_SIZE = 4;
    // 图片上干扰线数量
    private static final int LINES = 5;
    // 图片宽度
    private static final int IMAGE_WIDTH = 80;
    // 图片高度
    private static final int IMAGE_HEIGHT = 40;
    // 图片字体大小
    private static final int FONT_SIZE = 30;

    /**
     * 生成随机验证码及图片
     */
    public static Map<String, String> createImage() throws IOException {
        StringBuilder sb = new StringBuilder();
        // 1.创建空白图片
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        // 2.获取图片画笔
        Graphics graphic = image.getGraphics();
        // 3.设置画笔颜色
        graphic.setColor(Color.LIGHT_GRAY);
        // 4.绘制矩形背景
        graphic.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        // 5.画随机字符
        Random ran = new Random();
        for (int i = 0; i < CODE_SIZE; i++) {
            // 取随机字符索引
            int n = ran.nextInt(chars.length);
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 设置字体大小
            graphic.setFont(new Font(null, Font.BOLD + Font.ITALIC, FONT_SIZE));
            // 画字符
            graphic.drawString(chars[n] + "", i * IMAGE_WIDTH / CODE_SIZE, IMAGE_HEIGHT * 2 / 3);
            // 记录字符
            sb.append(chars[n]);
        }
        // 6.画干扰线
        for (int i = 0; i < LINES; i++) {
            // 设置随机颜色
            graphic.setColor(getRandomColor());
            // 随机画线
            graphic.drawLine(ran.nextInt(IMAGE_WIDTH), ran.nextInt(IMAGE_HEIGHT),
                    ran.nextInt(IMAGE_WIDTH), ran.nextInt(IMAGE_HEIGHT));
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image,"png",out);
        String base64String = Base64.encodeBase64String(out.toByteArray());
        Map<String,String> result = new HashMap<>(2);
        result.put("code",sb.toString());
        result.put("base64","data:image/png;base64," + base64String);
        return result;
    }
    /**
     * 获取随机背景颜色(RGB)
     */
    private static Color getRandomColor() {
        Random ran = new Random();
        return new Color(ran.nextInt(256), ran.nextInt(256), ran.nextInt(256));
    }

}
