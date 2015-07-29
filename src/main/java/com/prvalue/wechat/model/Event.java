package com.prvalue.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heisaman
 */
@Entity
@Table(name="EVENTS")
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class Event {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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

    @Column(name="event")
    @XmlElement(name = "Event")
    private String event;

    @Column(name="latitude")
    @XmlElement(name = "Latitude")
    private double latitude;

    @Column(name="longitude")
    @XmlElement(name = "Longitude")
    private double longitude;

    @Column(name="geo_precision")
    @XmlElement(name = "Precision")
    private double precision;

    @Column(name="event_key")
    @XmlElement(name = "EventKey")
    private int eventKey;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public int getEventKey() {
        return eventKey;
    }

    public void setEventKey(int eventKey) {
        this.eventKey = eventKey;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }
}
