import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * PACKAGE_NAME
 *
 * @Author Administrator
 * @date 16:59
 */
public class Test {
    public static void main(String[] args) {
        Object md5 = new SimpleHash("MD5", "123", "8O+vDNr2sI3N82BI31fu1A==", 1);
        System.out.println(md5.toString());
    }
}
