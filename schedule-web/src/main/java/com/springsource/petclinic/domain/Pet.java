package com.springsource.petclinic.domain;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.springsource.petclinic.reference.PetType;

import javax.validation.constraints.Min;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;

public class Pet {

    /**
     */
    @NotNull
    private boolean sendReminders;

    /**
     */
    @NotNull
    @Size(min = 1)
    private String name;

    /**
     */
    @NotNull
    @Min(0L)
    private Float weight;

    /**
     */
    @ManyToOne
    private Owner owner;

    /**
     */
    @NotNull
    @Enumerated
    private PetType type;
}
