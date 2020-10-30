package com.atguigu.java1;

/**
 * @author 田沛勋
 * @create 2020-07-31 19:23
 */
public class User implements Comparable{
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        System.out.println("User equals().....");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (age != user.age) return false;
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() { //return name.hashCode + age;
        // 方式二low：结果可能出现问题：24 + 20 = 20 + 24，本来这两个对象不是equals()返回true，可以存放到数组中，但hash值此时一样，我们通过链表存储不建议。
        // 下面使用name.hashCode() * 31 + age；让对象之间hash值相等概率低好多(减少冲突)！ (为什么选择31这个系数？因为31 = 2 << 5 - 1)
        // 系数31选择：
        // 1）要大 2）不能溢出 3）这个数尽可能通过位移运算计算更好，故优先考虑 2^(n) - 1/ + 1非偶数考虑素数情况(减少冲突)，因为偶数有约数 ,n = 1,2,3,4,5
        // 4)考虑位运算奇数：7，9，15，17，31，33，63，65  ；干掉非素数的：63-65-9

        //31是一个素数，素数作用就是如果我用一个数字来乘以这个素数，那么最终出来的结果只能被素数本身和被乘数还有1来整除！(减少冲突)
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }


    //自然排序：先按照姓名从大到小排列,年龄从小到大排序(二级排列)
    @Override
    public int compareTo(Object o) {
        if(o instanceof User){
            User user = (User)o;
//            return -this.name.compareTo(user.name);
            int compare = -this.name.compareTo(user.name);
            if(compare != 0){
                return compare;
            }else{
                return Integer.compare(this.age, user.age);
            }

        }else{
            throw new RuntimeException("输入的类型不匹配");
        }

    }
}
