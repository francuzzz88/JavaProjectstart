package ru.petrov.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "usr")
public class User implements Serializable  {
    @Id
    @JsonView({Views.IdName.class})
    private String id;
    @JsonView({Views.IdName.class})
    private String name;
    @JsonView({Views.IdName.class})
    private String userpic;
    private String email;
    private String gender;
    private String locale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime lastVisit;

}
