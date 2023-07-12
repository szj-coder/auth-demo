package com.example.authdemo.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 手机号工具类使用方式
 *
 * @author szj
 * @date 2023/5/15 10:16
 */
public class PhoneNumUtilTest {

    private final static Pattern MOBILE_PATTERN = Pattern.compile("^(((\\+\\d{2}-)?0\\d{2,3}-\\d{7,8})|(\\+[1-9]\\d{0,2})?\\d{1,12}|((\\+\\d{2}-)?(\\d{2,3}-)?([1][34578][0-9]\\d{8})))$");

    @Test
    public void checkPhoneNumber() {
        ArrayList<String> numList = new ArrayList<>();
        numList.add("18566666666");
        numList.add("8618566666666");
        numList.add("+8618566666666");
        numList.add("+85251111111");
        numList.add("+8170-1111-1111");
        numList.add("+8170 1111 1111");
        numList.add("008170 1111 1111");
        // 座机
        numList.add("021-12345678");

        for (String num : numList) {
            Assert.isTrue(PhoneNumUtilTest.validPhoneNumber(num), "手机号校验不通过");
        }
    }

    /**
     * 根据手机号获取区域信息
     */
    @Test
    public void getRegionCodeForNumber() throws NumberParseException {
        String phone = "+13474461111";
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = instance.parse(phone, "CN");
        System.out.println(instance.getRegionCodeForNumber(phoneNumber));
    }

    @Test
    public void checkPhoneNumberError() {
        ArrayList<String> numList = new ArrayList<>();
        numList.add("185666666661");
        numList.add("+86185666666611");
        numList.add("+86115666666611");
        numList.add("+8111566666661");
        numList.add("8170-1111-1111");
        numList.add("xxxxxx");

        for (String num : numList) {
            Assert.isTrue(!PhoneNumUtilTest.validPhoneNumber(num), "手机号校验不通过");
        }
    }

    /**
     * 性能测试：
     * PhoneNumberUtil 平均解析1w数据需要91ms
     * 正则表达式 平均解析1w数据需要3ms
     */
    @Test
    public void benchmarkTest() {
        ArrayList<String> numList = new ArrayList<>();
        numList.add("18566666666");
        numList.add("18511111111");
        numList.add("18522222222");
        numList.add("18533333333");
        numList.add("18544444444");
        numList.add("18555555555");
        // 座机
        int total = 100;
        int num = total;
        int sum = 0;
        while (num-- > 0) {
            int i = 0;
            int count = 0;
            long start = System.currentTimeMillis();
            while (count++ < 10000) {
//                boolean result = validPhoneNumber(numList.get(i));
                boolean result = validPhoneNumber2(numList.get(i));
//            System.out.println(number.toString());
                Assert.isTrue(result, numList.get(i));
                i++;
                i = i % numList.size();
            }
            long time = System.currentTimeMillis() - start;
            sum += time;
//            System.out.println(time);
        }
        System.out.printf("sum:%s num:%s ave:%s%n", sum, total, sum / total);
    }

    private static boolean validPhoneNumber(String mobile) {
        PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber number = null;
        try {
            number = instance.parse(mobile, "CN");
        } catch (NumberParseException e) {
            return false;
        }
        return instance.isValidNumber(number);
    }

    public boolean validPhoneNumber2(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }


    /**
     * 列出国家和地区列表
     */
    @Test
    public void countryList() {
        Set<String> set = PhoneNumberUtil.getInstance().getSupportedRegions();
        String[] arr = set.toArray(new String[0]);

        for (String s : arr) {
            Locale locale = new Locale("en", s);
            System.out.printf("lib country:%5s :%5s :%-1s\n", s, PhoneNumberUtil.getInstance().getCountryCodeForRegion(s), locale.getDisplayCountry());
        }
        System.out.println(arr.length);
    }
}
