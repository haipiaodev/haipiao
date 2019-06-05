package com.haipiao.persist.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Component
@Validated
@ConfigurationProperties
@PropertySource("classpath:/resources/config.properties")
public class PersistProperties {

    private Postgres postgres;

    public Postgres getPostgres() {
        return postgres;
    }

    public void setPostgres(Postgres postgres) {
        this.postgres = postgres;
    }

    @Override
    public String toString() {
        return "PersistProperties{" +
            "postgres=" + postgres +
            '}';
    }

    public static class Postgres {

        @NotEmpty
        private String driverClassName;

        @NotEmpty
        private String url;

        @NotEmpty
        private String username;

        @NotEmpty
        private String password;

        public String getDriverClassName() {
            return driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "Postgres{" +
                "driverClassName='" + driverClassName + '\'' +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
        }
    }


}
