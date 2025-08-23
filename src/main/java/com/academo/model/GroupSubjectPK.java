package com.academo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GroupSubjectPK implements Serializable {


    @ManyToOne
    private Group group;
    private Subject subject;



}
