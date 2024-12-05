package cn.xiaqileyu.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static net.sourceforge.jtds.jdbc.Support.toHex;

/**
 * 公用工具类
 *
 * @author swt
 * @date 2020/6/24
 */
public class CommonUtil {

    private transient static Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * MD5加密
     * 
     * @param password 明文密码
     * @return 加密结果
     */
    public static String encrypt(String password) {
        String passwordMd5 = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(password.getBytes("utf-8"));
            passwordMd5 = toHex(bytes);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordMd5;
    }
}
