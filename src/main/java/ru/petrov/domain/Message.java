package ru.petrov.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "text"})
@EqualsAndHashCode(of = {"id"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Id.class)
    private Long id;

    @JsonView(Views.IdName.class)
    private String text;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @JsonView(Views.FullMessage.class)
    private LocalDateTime timeCreation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonView({Views.FullMessage.class})
    private User author;

    @OneToMany(mappedBy = "message", orphanRemoval = true)
    @JsonView({Views.FullMessage.class})

    private List<Comment> comments;

    @JsonView({Views.FullMessage.class})
    private String link;
    @JsonView({Views.FullMessage.class})
    private String linkTitle;
    @JsonView({Views.FullMessage.class})
    private String linkDescription;
    @JsonView({Views.FullMessage.class})
    private String linkCover;
   }
