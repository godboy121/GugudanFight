package com.bit2016.gugudanfight.test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bit-user on 2016-11-25.
 */

public class SetTest {

    public static  void main(String[] args){
        Set<Multiplication> set=new HashSet<Multiplication>();
        System.out.println(randomize(1,9));

    }
    private static int randomize(int from, int to){
        return (int)(Math.random()*to)+from;
    }
    private class Multiplication{
        private int left;
        private int right;


    }
}
