package domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BankAccount {
    private Long id;
    private String accountNumber;
    private Float balance;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Client client;
}

