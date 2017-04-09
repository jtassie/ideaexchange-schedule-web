package com.springsource.petclinic.domain;
import javax.validation.constraints.Size;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.ManyToOne;

public class Visit {

    /**
     */
    @Size(max = 255)
    private String description;

    /**
     */
    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date visitDate;

    /**
     */
    @NotNull
    @ManyToOne
    private Pet pet;

    /**
     */
    @ManyToOne
    private Vet vet;
}
