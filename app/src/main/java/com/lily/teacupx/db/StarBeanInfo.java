package com.lily.teacupx.db;

import com.lily.teacupx.tool.Sex;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */

@Entity
public class StarBeanInfo {

    @Id(autoincrement = true)
    private Long id;
    //姓名
    private String name;
    //又名
    private String nickName;
    //血型
    private int blood;
    //身高
    private int stature;
    //年龄
    private int age;
    //地址
    private String address;
    //简介
    private String description;
    //详情
    private String detailsDesc;
    //封面
    private String cover;
    //图片集合
    private String image_one;
    private String image_two;
    private String image_three;
    private String image_four;
    public String getImage_four() {
        return this.image_four;
    }
    public void setImage_four(String image_four) {
        this.image_four = image_four;
    }
    public String getImage_three() {
        return this.image_three;
    }
    public void setImage_three(String image_three) {
        this.image_three = image_three;
    }
    public String getImage_two() {
        return this.image_two;
    }
    public void setImage_two(String image_two) {
        this.image_two = image_two;
    }
    public String getImage_one() {
        return this.image_one;
    }
    public void setImage_one(String image_one) {
        this.image_one = image_one;
    }
    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public String getDetailsDesc() {
        return this.detailsDesc;
    }
    public void setDetailsDesc(String detailsDesc) {
        this.detailsDesc = detailsDesc;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getStature() {
        return this.stature;
    }
    public void setStature(int stature) {
        this.stature = stature;
    }
    public int getBlood() {
        return this.blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 847708408)
    public StarBeanInfo(Long id, String name, String nickName, int blood,
            int stature, int age, String address, String description,
            String detailsDesc, String cover, String image_one, String image_two,
            String image_three, String image_four) {
        this.id = id;
        this.name = name;
        this.nickName = nickName;
        this.blood = blood;
        this.stature = stature;
        this.age = age;
        this.address = address;
        this.description = description;
        this.detailsDesc = detailsDesc;
        this.cover = cover;
        this.image_one = image_one;
        this.image_two = image_two;
        this.image_three = image_three;
        this.image_four = image_four;
    }
    @Generated(hash = 491870343)
    public StarBeanInfo() {
    }

}
