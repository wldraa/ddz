package cn.wldraa.ddz.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author zhangqian
 */
public class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    public static String DICTIONARY_LOWER_LETTER_AND_NUM = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * dump an object to string
     */
    public static String dump(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        for (Field field : fields) {
            field.setAccessible(true);
            sb.append(field.getName());
            sb.append('=');
            try {
                sb.append(field.get(o));
            } catch (IllegalAccessException e) {
                // 不会发生的
                logger.error("fail to get property {}", field.getName(), e);
            }
            sb.append('|');
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * object to string
     * @param o the object to toString
     * @return string of the object
     */
    @Deprecated
    public static String dump2(Object o) {
        PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(o.getClass());
        StringBuilder sb = new StringBuilder();
        for (PropertyDescriptor pd : pds) {
            Method readMethod = pd.getReadMethod();
            readMethod.setAccessible(true);
            sb.append(pd.getName());
            sb.append('=');
            try {
                Object value = readMethod.invoke(o).toString();
                sb.append(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            sb.append('|');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String generatorRandomString(String dictionary, int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0 ; i < length ; i++) {
            sb.append(dictionary.charAt(random.nextInt(dictionary.length())));
        }
        return sb.toString();
    }

}
