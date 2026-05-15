package infrastructure.adapter.input.rest.controller;

import application.port.input.CreateBankAccountUseCase;
import application.port.input.FindBankAccountUseCase;
import application.port.input.TransferBankAccountUseCase;
import domain.exception.BankAccountNotFoundException;
import domain.exception.InvalidBankAccountDataException;
import domain.model.BankAccount;
import infrastructure.adapter.input.rest.dto.BankAccountRequest;
import infrastructure.adapter.input.rest.dto.BankAccountResponse;
import infrastructure.adapter.input.rest.dto.TransferRequest;
import infrastructure.adapter.output.persistance.mapper.BankAccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j

public class BankAccountController {

    private final CreateBankAccountUseCase createBankAccountUseCase;
    private final FindBankAccountUseCase findBankAccountUseCase;
    private final TransferBankAccountUseCase transferBankAccountUseCase;
    private final BankAccountMapper mapper;

    @PostMapping
    public ResponseEntity<BankAccountResponse> createBankAccount(@RequestBody BankAccountRequest request)
    {
        try {
            System.out.print("test bank controller");
            System.out.println("test bank controller 2");
            log.info("Creating bankAccount with name: {} and balance: {}", request.getName(), request.getBalance().toString());

            BankAccount newBankAccount = this.mapper.toDomain(request);
            BankAccount createdBankAccount = this.createBankAccountUseCase.execute(newBankAccount, request.getName(),
                    request.getDocument());

            return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toResponse(createdBankAccount));

        }
        catch (Exception ex)
        {
            log.error("Unexpected error creating bank account", ex);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccountResponse> getBankAccount(@PathVariable Long id) {
        try {
            log.info("Fetching user with ID: {}", id);

            BankAccount bankAccount = this.findBankAccountUseCase.findBankAccountById(id);
            log.info("User found: {}", bankAccount.getAccountNumber());

            return ResponseEntity.ok(this.mapper.toResponse(bankAccount));
        } catch (InvalidBankAccountDataException e) {
            log.warn("Invalid user ID: {}", id);
            return ResponseEntity.badRequest().build();
        } catch (BankAccountNotFoundException e) {
            log.warn("User not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<BankAccountResponse> transferToBankAccount(@RequestBody TransferRequest request) {
        try {
            BankAccount transferedBankAccount = this.transferBankAccountUseCase.transfer(request.getSourceAccount(),
                    request.getDestinationAccount(), request.getAmount());

            return ResponseEntity.status(HttpStatus.CREATED).body(this.mapper.toResponse(transferedBankAccount));
        }
        catch (Exception ex)
        {
            log.error("Unexpected error to transfer money in bank account", ex);
            return ResponseEntity.internalServerError().build();
        }
    }
}

