package com.academo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_groups_subjects")
public class GroupSubject {
    @EmbeddedId
    private GroupSubjectPK groupSubjectPK = new GroupSubjectPK();

    public GroupSubjectPK getGroupSubjectPK() {
        return groupSubjectPK;
    }

    public void setGroupSubjectPK(GroupSubjectPK groupSubjectPK) {
        this.groupSubjectPK = groupSubjectPK;
    }

}
