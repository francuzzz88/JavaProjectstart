package ru.petrov.dto;


import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.petrov.domain.Views;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {
private ObjectType objectType;
private EventType eventType;
@JsonRawValue
private String body;
}
