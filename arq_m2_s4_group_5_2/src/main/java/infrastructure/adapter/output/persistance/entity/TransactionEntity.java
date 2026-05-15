package infrastructure.adapter.output.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@Data

@NoArgsConstructor
@AllArgsConstructor

public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bank_account_source_id")
    private BankAccountEntity bankAccountSource;

    @ManyToOne
    @JoinColumn(name = "bank_account_destination_id")
    private BankAccountEntity bankAccountDestination;

    @Column(nullable = false)
    private float amount;

    @Column(nullable = false)
    private float comision;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(nullable = false, length = 100)
    private String state;

    @Column(nullable = false, length = 100)
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
