package com.example.authdemo.antlr.util;

import java.io.Serializable;
import java.util.List;

/**
 * 基础类型提升
 *
 * @author szj
 */
public class BaseTypePromotion {

    private static final List<? extends Class<? extends java.io.Serializable>> baseClassList = List.of(Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, Character.class, String.class);
    private static final List<? extends Class<? extends Number>> numberClassList = List.of(Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class);
    private static final List<?> strClassList = List.of(Character.class, String.class);

    public static Object negative(Object a) {
        try {
            anyNotNullOrError(a);
        } catch (Exception e) {
            return null;
        }
        isBaseOrError(a);
        final int maxOrder = promotionOrder(a);
        if (maxOrder < 0) {
            throw new RuntimeException("不支持 -%s操作".formatted(a));
        }
        if (isStr(maxOrder)) {
            return "-" + String.valueOf(a);
        }
        if (maxOrder == promotionOrder(Short.class)) {
            return -((Number) a).floatValue();
        } else if (maxOrder == promotionOrder(Integer.class)) {
            return -((Number) a).intValue();
        } else if (maxOrder == promotionOrder(Long.class)) {
            return -((Number) a).longValue();
        } else if (maxOrder == promotionOrder(Float.class)) {
            return -((Number) a).floatValue();
        } else {
            // double
            return -((Number) a).doubleValue();
        }

    }

    public static int compareTo(Object a, Object b) {
        try {
            anyNotNullOrError(a, b);
        } catch (Exception e) {
            throw new RuntimeException("a=%s b=%s 空值不支持compareTo".formatted(a, b), e);
        }
        isBaseOrError(a);
        isBaseOrError(b);
        final int maxOrder = maxPromotionOrder(a, b);
        if (maxOrder < 0) {
            throw new RuntimeException("a=%s b=%s 类型不识别".formatted(a, b));
        }
        final Class<? extends Serializable> maxType = baseClassList.get(maxOrder);
        if (isStr(maxType)) {
            return String.valueOf(a).compareTo(String.valueOf(b));
        } else {
            int result;
            if (maxOrder == promotionOrder(Short.class)) {
                result = Float.compare(((Number) a).floatValue(), ((Number) b).floatValue());
            } else if (maxOrder == promotionOrder(Integer.class)) {
                result = Integer.compare(((Number) a).intValue(), (((Number) b).intValue()));
            } else if (maxOrder == promotionOrder(Long.class)) {
                result = Long.compare(((Number) a).longValue(), (((Number) b).longValue()));
            } else if (maxOrder == promotionOrder(Float.class)) {
                result = Float.compare(((Number) a).floatValue(), (((Number) b).floatValue()));
            } else {
                // double
                result = Double.compare(((Number) a).doubleValue(), (((Number) b).doubleValue()));
            }
            return result;
        }
    }

    public static Object plus(Object a, Object b) {
        final int maxOrder = maxPromotionOrder(a, b);
        if (maxOrder < 0) {
            throw new RuntimeException("a=%s b=%s 类型不识别".formatted(a, b));
        }
        final Class<? extends Serializable> maxType = baseClassList.get(maxOrder);
        if (isStr(maxType)) {
            isBaseOrError(a);
            isBaseOrError(b);
            return String.valueOf(a) + b;
        } else {
            try {
                anyNotNullOrError(a, b);
            } catch (Exception e) {
                throw new RuntimeException("a=%s + b=%s 不支持空值运算".formatted(a, b), e);
            }
            isBaseOrError(a);
            isBaseOrError(b);
            Object result = null;
            if (maxOrder == promotionOrder(Short.class)) {
                result = ((Number) a).floatValue() + (((Number) b).floatValue());
            } else if (maxOrder == promotionOrder(Integer.class)) {
                result = ((Number) a).intValue() + (((Number) b).intValue());
            } else if (maxOrder == promotionOrder(Long.class)) {
                result = ((Number) a).longValue() + (((Number) b).longValue());
            } else if (maxOrder == promotionOrder(Float.class)) {
                result = ((Number) a).floatValue() + (((Number) b).floatValue());
            } else {
                // double
                result = ((Number) a).doubleValue() + (((Number) b).doubleValue());
            }
            return result;
        }
    }

    public static Object minus(Object a, Object b) {
        try {
            anyNotNullOrError(a, b);
        } catch (Exception e) {
            throw new RuntimeException("a=%s - b=%s 不支持空值运算".formatted(a, b), e);
        }

        isNumOrError(a);
        isNumOrError(b);
        final int orderIndex = maxPromotionOrder(a, b);

        Object result = null;
        if (orderIndex == promotionOrder(Short.class)) {
            result = ((Number) a).floatValue() - (((Number) b).floatValue());
        } else if (orderIndex == promotionOrder(Integer.class)) {
            result = ((Number) a).intValue() - (((Number) b).intValue());
        } else if (orderIndex == promotionOrder(Long.class)) {
            result = ((Number) a).longValue() - (((Number) b).longValue());
        } else if (orderIndex == promotionOrder(Float.class)) {
            result = ((Number) a).floatValue() - (((Number) b).floatValue());
        } else {
            // double
            result = ((Number) a).doubleValue() - (((Number) b).doubleValue());
        }
        return result;
    }

    public static Object mult(Object a, Object b) {
        try {
            anyNotNullOrError(a, b);
        } catch (Exception e) {
            throw new RuntimeException("a=%s * b=%s 不支持空值运算".formatted(a, b), e);
        }

        isNumOrError(a);
        isNumOrError(b);
        final int orderIndex = maxPromotionOrder(a, b);

        Object result;
        if (orderIndex == promotionOrder(Short.class)) {
            result = ((Number) a).floatValue() * (((Number) b).floatValue());
        } else if (orderIndex == promotionOrder(Integer.class)) {
            result = ((Number) a).intValue() * (((Number) b).intValue());
        } else if (orderIndex == promotionOrder(Long.class)) {
            result = ((Number) a).longValue() * (((Number) b).longValue());
        } else if (orderIndex == promotionOrder(Float.class)) {
            result = ((Number) a).floatValue() * (((Number) b).floatValue());
        } else {
            // double
            result = ((Number) a).doubleValue() * (((Number) b).doubleValue());
        }
        return result;
    }

    public static Object div(Object a, Object b) {
        try {
            anyNotNullOrError(a, b);
        } catch (Exception e) {
            throw new RuntimeException("a=%s / b=%s 不支持空值运算".formatted(a, b), e);
        }

        isNumOrError(a);
        isNumOrError(b);
        final int orderIndex = maxPromotionOrder(a, b);

        Object result = null;
        if (orderIndex == promotionOrder(Short.class)) {
            result = ((Number) a).floatValue() / (((Number) b).floatValue());
        } else if (orderIndex == promotionOrder(Integer.class)) {
            result = ((Number) a).intValue() / (((Number) b).intValue());
        } else if (orderIndex == promotionOrder(Long.class)) {
            result = ((Number) a).longValue() / (((Number) b).longValue());
        } else if (orderIndex == promotionOrder(Float.class)) {
            result = ((Number) a).floatValue() / (((Number) b).floatValue());
        } else {
            // double
            result = ((Number) a).doubleValue() / (((Number) b).doubleValue());
        }
        return result;
    }

    public static Object mod(Object a, Object b) {
        try {
            anyNotNullOrError(a, b);
        } catch (Exception e) {
            throw new RuntimeException("a=%s %% b=%s 不支持空值运算".formatted(a, b), e);
        }

        isNumOrError(a);
        isNumOrError(b);
        final int orderIndex = maxPromotionOrder(a, b);

        Object result;
        if (orderIndex == promotionOrder(Short.class)) {
            result = ((Number) a).floatValue() % (((Number) b).floatValue());
        } else if (orderIndex == promotionOrder(Integer.class)) {
            result = ((Number) a).intValue() % (((Number) b).intValue());
        } else if (orderIndex == promotionOrder(Long.class)) {
            result = ((Number) a).longValue() % (((Number) b).longValue());
        } else if (orderIndex == promotionOrder(Float.class)) {
            result = ((Number) a).floatValue() % (((Number) b).floatValue());
        } else {
            // double
            result = ((Number) a).doubleValue() % (((Number) b).doubleValue());
        }
        return result;
    }

    public static int maxPromotionOrder(Object a, Object b) {
        final int max = Math.max(promotionOrder(a), promotionOrder(b));
        // 如果是Integer和Float需要提升为double
        if (max == promotionOrder(Float.class) && (a.getClass() == Long.class || b.getClass() == Long.class)) {
            return promotionOrder(Double.class);
        }
        return max;
    }

    public static int promotionOrder(Object obj) {
        if (obj == null) {
            return -1;
        }
        return baseClassList.indexOf(obj.getClass());
    }

    public static int promotionOrder(Class<?> clazz) {
        if (clazz == null) {
            return -1;
        }
        return baseClassList.indexOf(clazz);
    }

    public static boolean isBase(Object obj) {
        return obj != null && baseClassList.contains(obj.getClass());
    }

    public static void isBaseOrError(Object obj) {
        if (obj != null && !isBase(obj)) {
            throw new RuntimeException("%s类型不是基础类型".formatted(obj.getClass()));
        }
    }

    public static boolean isBase(Class<?> clazz) {
        return clazz != null && baseClassList.contains(clazz);
    }

    public static void isBaseOrError(Class<?> clazz) {
        if (clazz != null && !isBase(clazz)) {
            throw new RuntimeException("%s类型不是基础类型".formatted(clazz));
        }
    }

    public static boolean isStr(Object obj) {
        return obj != null && strClassList.contains(obj.getClass());
    }

    public static boolean isStr(Class<?> clazz) {
        return clazz != null && strClassList.contains(clazz);
    }

    public static boolean isNum(Object obj) {
        return obj != null && numberClassList.contains(obj.getClass());
    }

    public static boolean isNum(Class<?> clazz) {
        return clazz != null && numberClassList.contains(clazz);
    }

    public static void isNumOrError(Object obj) {
        isNumOrError(obj, null);
    }

    public static void isNumOrError(Object obj, String msg) {
        if (!isNum(obj)) {
            throw new RuntimeException("%s 不是数值类型".formatted(msg));
        }
    }

    public static void isNumOrError(Class<?> clazz) {
        isNumOrError(clazz, null);
    }

    public static void isNumOrError(Class<?> clazz, String msg) {
        if (!isNum(clazz)) {
            throw new RuntimeException("%s 不是数值类型".formatted(msg));
        }
    }

    public static boolean parseBoolean(Object obj) {
        return Boolean.TRUE == obj;
    }

    public static void anyNotNullOrError(Object... objects) {
        if (anyNull(objects)) {
            throw new NullPointerException("不支持null类型");
        }
    }

    public static boolean anyNull(Object... objects) {
        if (objects == null) {
            return false;
        }
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }
}
