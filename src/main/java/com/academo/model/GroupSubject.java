package com.academo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_groups_subjects")
public class GroupSubject {

    @EmbeddedId
    private GroupSubjectPK groupSubjectPK = new GroupSubjectPK();
    @ManyToOne
    @JoinColumn(name = "id_group")
    @MapsId("idGroup")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "id_subject")
    @MapsId("idSubject")
    private Subject subject;

    public GroupSubjectPK getGroupSubjectPK() {
        return groupSubjectPK;
    }

    public void setGroupSubjectPK(GroupSubjectPK groupSubjectPK) {
        this.groupSubjectPK = groupSubjectPK;
    }
}
