package com.academo.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.ManyToAny;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GroupSubjectPK implements Serializable {

    private Integer idGroup;
    private Integer idSubject;

    public GroupSubjectPK() {
    }

    public GroupSubjectPK(Integer idGroup, Integer idSubject) {
        this.idGroup = idGroup;
        this.idSubject = idSubject;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    public Integer getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(Integer idSubject) {
        this.idSubject = idSubject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupSubjectPK that = (GroupSubjectPK) o;
        return Objects.equals(idGroup, that.idGroup) && Objects.equals(idSubject, that.idSubject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGroup, idSubject);
    }
}
