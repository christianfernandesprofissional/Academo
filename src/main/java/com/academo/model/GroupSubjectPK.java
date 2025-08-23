package com.academo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

@Embeddable
public class GroupSubjectPK {

    @ManyToOne
    @JoinColumn(name = "id_group")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "id_subject")
    private Subject subject;

}
