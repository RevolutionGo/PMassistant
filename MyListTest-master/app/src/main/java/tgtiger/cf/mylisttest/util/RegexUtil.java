package tgtiger.cf.mylisttest.util;

public class RegexUtil {
    //手机号的正则表达式
    private static final String PHONE_REGEX = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$";

    //邮箱的正则表达式
    private static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$";

    //判断手机号是否符合规范
    public static boolean isPhone(String value) { return value.matches(PHONE_REGEX);}

    //判断邮箱是否符合规范
    public static boolean isEmail(String value) { return value.matches(EMAIL_REGEX);}
}

