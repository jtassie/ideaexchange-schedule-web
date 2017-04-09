package com.springsource.petclinic.domain;
import java.util.Calendar;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;
import com.springsource.petclinic.reference.Specialty;
import javax.persistence.Enumerated;

public class Vet extends AbstractPerson {

    /**
     */
    @NotNull
    @Past
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar employedSince;

    /**
     */
    @Enumerated
    private Specialty specialty;
}
