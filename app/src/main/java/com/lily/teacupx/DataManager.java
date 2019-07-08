package com.lily.teacupx;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lily
 * @date 2019/7/8 0008.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class DataManager {

    public DataManager(){
        starList.add(starOne);
        starList.add(starTwo);
    }

    //姓名
    public String[] nameList = new String[]{
            "渡辺麻友(まゆゆ)", "柏木由紀(ゆきりん)"
    };

    //昵称
    public String[] nickName = new String[]{
            "まゆゆ ", "ゆきりん "
    };

    //地址
    public String[] address = new String[]{
            "埼玉県", "鹿児島県 "
    };

    //身高
    public int[] stature = new int[]{
            156, 163
    };

    //年龄
    public int[] year = new int[]{
            26, 28
    };

    public String[] cover=new String[]{
            "http://up.enterdesk.com/edpic/26/dd/c4/26ddc41ce24c62a2eb08935696304353.jpg",
            "http://b-ssl.duitang.com/uploads/item/201501/16/20150116232222_THCfL.jpeg"
    };

    /**
     * 0:A型
     * 1:B型
     * 2:AB型
     * 3:O型
     */
    public int[] blood = new int[]{
            2, 3
    };

    //Star 简介
    public String[] description = new String[]{
            "2006年『第三期AKB48追加メンバーオーディション』に合格。\n" +
                    "2007年AKB48劇場でのチームB 1st Stage「青春ガールズ」初日公演において、チームBの一員として公演デビュー。同年、4thシングル「BINGO!」で、初の選抜メンバー入りを果たした。\n" +
                    "不動のアイドルとして、歌やダンスはもちろん、タレント、女優としても活躍している。",

            "2006年『第三期AKB48追加メンバーオーディション』に合格。\n" +
                    "2007年AKB48劇場でのチームB 1st Stage「青春ガールズ」初日公演において、チームBの一員として公演デビュー。同年、4thシングル「BINGO!」で、初の選抜メンバー入りを果たした。\n" +
                    "AKBの一員として活躍する一方、2009年TBS系列情報『ひるおび!』気象情報コーナーで水・木曜担当。\n" +
                    "また女優としても注目を浴び、様々な場面での活躍をみせている。"
    };

    public String[] details = new String[]{

            /*one*/
            "从小即以上网、看动画为最大兴趣的渡边，性格内向，极少与同学交流。因在网络上看到AKB48的相关表演影片而喜欢上此团体，成为AKB48的歌迷。母亲最初对于女儿提出想进入艺能界而感到为难，不过想到以此为契机，女儿往外发展说不定会成为活泼的孩子，最终赞成渡边参与AKB的征选[2]。2006年2月19日，渡边报名“第2期AKB48追加成员甄选”，在最终审查阶段落选。12月3日，“第3期AKB48追加成员甄选”展开，渡边合格。参加甄选的理由是“因为很喜欢AKB48，所以想加入”[3]，歌唱审查的环节也唱了AKB的单曲《樱花花瓣》。\n" +
                    "\n" +
                    "2007年4月8日，渡边正式以Team B分队成员的身份在AKB48剧场公演中出道。7月18日，在第4张单曲《BINGO!》中，与原Team B成员柏木由纪、平嶋夏海一起成为单曲选拔成员（即成为单曲主要演唱成员），此亦是三人初次成为选拔成员。9月22日，渡边与Team B赴中国北京市参加在中国艺术研究院举办的“日中文化人恳谈会2007—开放的学院in北京”，并进行首场海外公演。12月31日，以AKB48成员的身份，首次登上“NHK红白歌合战”的舞台。\n" +
                    "\n" +
                    "2008年6月，渡边加入尾木制作。10月，与平嶋夏海、仲川遥香、多田爱佳一起组成AKB子团“走廊奔跑队”。2009年7月，在“AKB第一届选拔总选举”中获得第4位。15岁以下的成员中，进入Top 5的目前为止只有渡边，即使包含进入Top 10的也只有松井珠理奈（SKE48中2010年度、2012年度）与渡边两人。\n" +
                    "\n" +
                    "2010年5月25日到6月8日间举行的“AKB第二届选拔总选举”中获得第5位。9月9日，渡边首次以漫画家的身份出道。在知名漫画周刊《周刊YOUNG JUMP》（集英社）2010年41号中，开始连载个人漫画“まゆゆ童话剧场 只爱二次元”[4]。9月15日至9月16日，参加尾木制作举办的第一届Team Ogi祭。",

            /*two*/
    };





    //Star图片
    public List<String[]> starList = new ArrayList<>();


    private String[] starOne = new String[]{
            "http://livedoor.blogimg.jp/akb4839/imgs/9/1/91264eeb.jpg",
            "http://img.vziti.com/yytalljpg/%E6%97%A5%E6%9C%AC/5/539876.jpg",
            "https://www.google.com.hk/url?sa=i&source=images&cd=&ved=&url=https%3A%2F%2Fwww.win7.la%2Fzm%2Fmnv%2F3898.html&psig=AOvVaw1t2r4xCXgAZ3QPGQl390Bc&ust=1562662105569956"
    };

    private String[] starTwo = new String[]{
            "https://pbs.twimg.com/media/CqpOLl2VMAAeI3u.jpg",
            "https://66.media.tumblr.com/2c48e4ff484cc9e22f10ce15d6145c6e/tumblr_inline_pogf83ltKg1r4adcq_540.jpg",
            "http://img.67.com/upload/images/2018/01/19/1516329525_2032659195.png",
            "http://i1.mopimg.cn/img/dzh/2015-04/1274/20150424134020449.jpg"
    };


}
