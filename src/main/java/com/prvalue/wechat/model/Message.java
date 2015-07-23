package com.prvalue.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heisaman
 */
@Entity
@Table(name="MESSAGE")
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {

    @Id
    @Column(name="id")
    @XmlElement(name = "MsgId")
    private long id;

    @Column(name="to_user_name")
    @XmlElement(name = "ToUserName")
    private String toUserName;

    @Column(name="from_user_name")
    @XmlElement(name = "FromUserName")
    private String fromUserName;

    @Column(name="create_time")
    @XmlElement(name = "CreateTime")
    private long createTime;

    @Column(name="msg_type")
    @XmlElement(name = "MsgType")
    private String msgType;

    @Column(name="content")
    @XmlElements({
        @XmlElement(name = "Content"),
        @XmlElement(name = "PicUrl")
    })
    private String content;

    @Column(name="media_id")
    @XmlElement(name = "MediaId")
    private String mediaId;

    @Column(name="format")
    @XmlElement(name = "Format")
    private String format;

    @Column(name="thumb_media_id")
    @XmlElement(name = "ThumbMediaId ")
    private String thumbMediaId;

    @Column(name="location_x")
    @XmlElement(name = "Location_X")
    private double locationX;

    @Column(name="location_y")
    @XmlElement(name = "Location_Y")
    private double locationY;

    @Column(name="scale")
    @XmlElement(name = "Scale")
    private int scale;

    @Column(name="label")
    @XmlElement(name = "Label")
    private String label;

    @Column(name="agent_id")
    @XmlElement(name = "AgentID")
    private int agentId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public double getLocationX() {
        return locationX;
    }

    public void setLocationX(double locationX) {
        this.locationX = locationX;
    }

    public double getLocationY() {
        return locationY;
    }

    public void setLocationY(double locationY) {
        this.locationY = locationY;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
}
