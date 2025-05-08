package com.example.authdemo.learn.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.pulsar.shade.io.swagger.annotations.ApiModelProperty;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;

/**
 * issue: <a href="https://github.com/alibaba/easyexcel/issues/4110">...</a>
 */
public class Issues4110TestExport {
//    public static void main(String[] args) {
//        File file = new File("test_" + System.currentTimeMillis() + ".xlsx");
//        String fileName = file.getAbsolutePath();
//        System.out.println(fileName);
//        try (ExcelWriter excelWriter = EasyExcel.write(fileName, DemoData.class)
//                .registerConverter(new ArrayAndStringConvert())
//                .includeColumnFieldNames(Arrays.asList("attackType", "xRealIp"))
//                .build()) {
//            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
//            excelWriter.write(data(), writeSheet);
//        }
//    }

    public static void main(String[] args) {
        DemoData data = new DemoData();
        data.setAttackType(new String[]{"A-attackType" + 1, "B-attackType" + 2});
        data.setARealIp(new String[]{"A-xRealIp" + 3, "B-xRealIp" + 4});
        System.out.println(BeanMap.create(data));

//        System.out.println(BeanMapUtils.create(data));
//        DemoData o = ((DemoData)ReflectUtils.newInstance(data.getClass()));
//        o.setAttackType(new String[]{"A-attackType" + 1, "B-attackType" + 2});
//        System.out.println(o);

    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setAttackType(new String[]{"A-attackType" + i, "B-attackType" + i});
            data.setARealIp(new String[]{"A-xRealIp" + i, "B-xRealIp" + i});
            list.add(data);
        }
        return list;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DemoData {

        @ApiModelProperty(value = "攻击类型")
        @ExcelProperty(value = "攻击类型", converter = ArrayAndStringConvert.class)
        private String[] attackType;

        @ApiModelProperty(value = "123")
        @ExcelProperty(value = "123", converter = ArrayAndStringConvert.class)
        private String[] aRealIp;
    }

}
