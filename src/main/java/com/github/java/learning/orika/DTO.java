package com.github.java.learning.orika;

/**
 * created by guanjian on 2020/11/9 10:20
 */
public class DTO {

    private Long id;

    private Integer seq;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DTO{" +
                "id=" + id +
                ", seq=" + seq +
                ", name='" + name + '\'' +
                '}';
    }
}
