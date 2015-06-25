package com.prvalue.wechat.utils;

/**
 *
 * @author Heisaman
 */
public class Constants {

    public static enum Gender {

        Undefined("未定义"),
        Male("男"),
        Female("女");

        private String gender;
        private Gender(String gender) {
           this.gender = gender;
        }
        public String getGender() {
           return gender;
        }
    }

    public static enum Status {

        Subscribed("已关注"),
        Frozen("已冻结"),
        Undefined("未定义"),
        Non_Subscribed("未关注");

        private String status;
        private Status(String status) {
           this.status = status;
        }
        public String getStatus() {
           return status;
        }
    }
}
