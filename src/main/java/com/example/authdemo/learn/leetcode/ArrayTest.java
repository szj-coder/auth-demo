package com.example.authdemo.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class ArrayTest {
    public static void main(String[] args) {

        int[] num = {1, 2, 3};
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();
        int[] vis = new int[num.length];
        track(res, num, tmp);
        //trackVis(res,num,new ArrayList<>(),vis);
        for (List<Integer> re : res) {
            for (Integer integer : re) {
                System.out.print(integer + " ");
            }
            System.out.println("__" + re.size());
        }
    }

    public static void track(List<List<Integer>> res, int[] num, List<Integer> tmp) {
        if (tmp.size() == num.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (!tmp.contains(num[i])) {
                tmp.add(num[i]);
                track(res, num, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

}
