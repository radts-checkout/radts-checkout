package com.mediinfo.radts.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Email {
    @Id
    private String fromName;

    @Column
    private String fromEmail;

    @Column
    private String fromMsg;
}
