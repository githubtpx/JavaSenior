package com.atguigu.exer;

/**
 * MyDate类包含:
 * private成员变量year,month,day；并为每一个属性定义getter, setter 方法；
 *
 * @author 田沛勋
 * @create 2020-08-01 15:59
 */
public class MyDate implements Comparable<MyDate>{
    private int year;
    private int month;
    private int day;


    public MyDate() {
    }

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }


    //自然排序
//    @Override
//    public int compareTo(Object o) {
//        if(o instanceof MyDate){
//            MyDate m = (MyDate)o;
//
//            //比较年
//            int minusYear = this.getYear() - m.getYear();
//            if(minusYear != 0){
//                return minusYear;
//            }
//            //比较月
//            int minusMonth = this.getMonth() - m.getMonth();
//            if(minusMonth != 0){
//                return minusMonth;
//            }
//            //比较日
//            return this.getDay() - m.getDay();
//        }
//
//        throw new RuntimeException("传入的数据类型不一致！");
//
//    }

    @Override
    public int compareTo(MyDate o) {
        //比较年
        int minusYear = this.getYear() - o.getYear();
        if(minusYear != 0){
            return minusYear;
        }
        //比较月
        int minusMonth = this.getMonth() - o.getMonth();
        if(minusMonth != 0){
            return minusMonth;
        }
        //比较日
        return this.getDay() - o.getDay();
    }
}
