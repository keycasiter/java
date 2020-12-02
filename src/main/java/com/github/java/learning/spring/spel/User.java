package com.github.java.learning.spring.spel;

/**
 * created by guanjian on 2020/11/30 10:55
 */
public class User {

    private String name;

    private Integer age;

    private String bizId;

    private User user;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }


    public static final class Builder {
        private String name;
        private Integer age;
        private String bizId;
        private User user;

        private Builder() {
        }

        public static Builder anUser() {
            return new Builder();
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder bizId(String bizId) {
            this.bizId = bizId;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public User build() {
            User user = new User(name);
            user.setAge(age);
            user.setBizId(bizId);
            user.setUser(user);
            return user;
        }
    }
}
