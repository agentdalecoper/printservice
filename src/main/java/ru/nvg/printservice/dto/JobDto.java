package ru.nvg.printservice.dto;

import ru.nvg.printservice.domain.JobType;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "job")
public class JobDto {
    private Long id;
    @XmlEnumValue("type")
    private JobType type;
    @XmlElement(name = "user")
    private String userName;
    @XmlElement(name = "device")
    private String deviceName;
    private int amount;


    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public JobType getType() {
        return type;
    }

    public void setType(JobType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
