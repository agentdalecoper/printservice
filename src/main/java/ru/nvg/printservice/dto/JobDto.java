package ru.nvg.printservice.dto;

import lombok.Data;
import ru.nvg.printservice.domain.JobType;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "job")
@Data
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
}
