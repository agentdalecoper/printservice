package ru.nvg.printservice.dto;

import ru.nvg.printservice.domain.JobType;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "job")
public class JobDto {
    private Long id;

    @XmlEnumValue("type")
    public JobType type;

    @XmlElement(name = "user")
    public String userName;
    @XmlElement(name = "device")
    public String deviceName;

    public int amount;

    @XmlAttribute
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
