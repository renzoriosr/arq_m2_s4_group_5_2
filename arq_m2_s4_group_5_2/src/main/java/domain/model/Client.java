package domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {
    private Long id;
    public String name;
    public String email;
    public String document;
    private LocalDateTime createdAt;
}