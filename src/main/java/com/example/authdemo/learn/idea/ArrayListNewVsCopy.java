package com.example.authdemo.learn.idea;

import java.util.ArrayList;

/**
 * ArrayList 是 clone快还是new一个新的数组快？
 *
 * <p>
 * 100000
 * clone times: 4072 size:100000
 * new times: 3283 size:100000
 * clone times: 3284 size:100000
 * new times: 3258 size:100000
 * ------------
 * 100000
 * clone times: 3983 size:100000
 * new times: 3184 size:100000
 * clone times: 3202 size:100000
 * new times: 3219 size:100000
 * </p>
 * 结论：差不多
 */
public class ArrayListNewVsCopy {

    private static void testClone(ArrayList<Integer> list, int num) {
        long start = System.currentTimeMillis();
        ArrayList<Integer> copys = null;
        for (int i = 0; i < num; i++) {
            copys = (ArrayList<Integer>) list.clone();
        }
        System.out.printf("clone times: %s size:%s%n", System.currentTimeMillis() - start, copys == null ? 0 : copys.size());
    }

    private static void testNew(ArrayList<Integer> list, int num) {
        long start = System.currentTimeMillis();
        ArrayList<Integer> copys = null;
        for (int i = 0; i < num; i++) {
            copys = new ArrayList<>(list);
        }
        System.out.printf("new times: %s size:%s%n", System.currentTimeMillis() - start, copys == null ? 0 : copys.size());
    }

    public static void main(String[] args) {
        int size = (int) 1e4;
        ArrayList<Integer> nums = new ArrayList<>(size);
        int num = (int) 1e5;
        for (int i = 0; i < size; i++) {
            nums.add(1);
        }
        System.out.println("------------start test------------");
        testClone(nums, num);
        testNew(nums, num);

        testClone(nums, num);
        testNew(nums, num);
    }
}
