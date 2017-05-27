package com.bwei.recycleviewdemo;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

import java.util.List;

/**
 * Created by muhanxi on 17/5/27.
 */


public class Beans {


    /**
     * ret_code : 200
     * ret_msg : ok
     * list : [{"date":"111","id":1,"pic":"http://p1.pstatp.com/large/22c90001cf8b5388ce33","title":" \n他\u201c秘书圈\u201d的人数规模，赶上了周永康","type":1},{"date":"2222","id":2,"pic":"http://p3.pstatp.com/list/190x124/1c19000062675e53b27e|http://p3.pstatp.com/list/190x124/1aa4000a2b8a788b321f","title":"北京的你再忙也要加一下这个投资微信！不然后悔！","type":2},{"date":"444","id":4,"pic":"http://p3.pstatp.com/list/190x124/1dc900048479b4bd9d6a","title":" \n空调原来要这么用才省电，后悔知道太晚，浪费不少钱！","type":1},{"date":"7777","id":7,"pic":"http://p3.pstatp.com/list/190x124/213300016c777190f9ed","title":" \n曾经的歌星，落魄到参加选秀，结果唱一半却被韩红喊停","type":1},{"date":"222","id":11,"pic":"http://p3.pstatp.com/list/190x124/22ca00011911b0a8061c","title":" \n\u201c二婚女和剩女，我该娶哪个啊？求救！\u201d","type":1}]
     */

    private int ret_code;
    private String ret_msg;
    private List<ListBean> list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Table(name = "beans")
    public static class ListBean {
        /**
         * date : 111
         * id : 1
         * pic : http://p1.pstatp.com/large/22c90001cf8b5388ce33
         * title :
         他“秘书圈”的人数规模，赶上了周永康
         * type : 1
         */

        @Column(name = "date")
        private String date;
        @Column(name = "id" ,isId = true)
        private int id;
        @Column(name = "pic")
        private String pic;
        @Column(name = "title")
        private String title;
        @Column(name = "type")
        private int type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
