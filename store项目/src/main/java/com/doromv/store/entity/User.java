package com.doromv.store.entity;

import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author Doromv
 * @create 2022-03-29-17:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class User extends BaseEntity implements Serializable {
    private Integer uid ;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
