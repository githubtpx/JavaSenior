package com.atguigu.java4;

/**
 * @author 田沛勋
 * @create 2020-08-23 17:50
 */
public class Boy {

    private Girl girl;


    public Boy() {
    }

    public Boy(Girl girl) {
        this.girl = girl;
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }

}
