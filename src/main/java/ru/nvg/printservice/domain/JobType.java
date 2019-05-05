package ru.nvg.printservice.domain;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum JobType {
    print, copy, scan, fax
}
