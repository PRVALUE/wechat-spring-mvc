package com.prvalue.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Heisaman
 */
@Entity
@Table(name="MESSAGE")
public class Message {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="to_user_name")
    private String toUserName;

    @Column(name="from_user_name")
    private String fromUserName;

    @Column(name="create_ime")
    private String createTime;

    @Column(name="msg_type")
    private String msgType;
}
