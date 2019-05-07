package ru.nvg.printservice.dto;

import lombok.Data;
import ru.nvg.printservice.domain.JobType;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "job")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class JobDto {
    @XmlAttribute
    private Long id;
    @XmlEnumValue("type")
    private JobType type;
    @XmlElement(name = "user")
    private String userName;
    @XmlElement(name = "device")
    private String deviceName;
    private int amount;
}
