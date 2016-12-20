package tydic.cn.com.helloworld;

/**
 * Created by Administrator on 2016/11/4.
 */
public class User {


    private String name;
    private String age;
    private String info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public User(){};
    public User(String name,String age,String info){
        this.name=name;
        this.age = age;
        this.info = info;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
