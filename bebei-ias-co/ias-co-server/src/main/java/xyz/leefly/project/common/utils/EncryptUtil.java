package xyz.leefly.project.common.utils;


import org.mindrot.jbcrypt.BCrypt;

public class EncryptUtil {

    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String encrypt) {
        return BCrypt.checkpw(password, encrypt);
    }

}
