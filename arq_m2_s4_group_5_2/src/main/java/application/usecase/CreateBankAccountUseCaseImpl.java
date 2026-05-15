package application.usecase;

import application.port.input.CreateBankAccountUseCase;
import application.port.output.BankAccountRepositoryPort;
import application.port.output.ClientRepositoryPort;
import domain.exception.InvalidBankAccountDataException;
import domain.model.BankAccount;
import domain.model.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateBankAccountUseCaseImpl implements CreateBankAccountUseCase {

    private final BankAccountRepositoryPort bankAccountRepositoryPort;
    private final ClientRepositoryPort clientRepositoryPort;

    @Override
    public BankAccount execute(BankAccount newBankAccount, String name, String document) {

        if (newBankAccount == null) {
            throw new InvalidBankAccountDataException("User cannot be null");
        }

        Client client;

        boolean exist = clientRepositoryPort.existByName(name);
        if (exist)
        {
            client = clientRepositoryPort.findByName(name);
        } else {
            client = Client.builder()
                    .name(name)
                    .email("test@email.com")
                    .document(document)
                    .build();
            client = clientRepositoryPort.save(client);
        }

        newBankAccount.setAccountNumber(this.generateBankAccountNumber());
        newBankAccount.setState("Active");
        //newBankAccount.setClientId(new ClientId(client.getId()));
        newBankAccount.setClient(client);

        return bankAccountRepositoryPort.save(newBankAccount);
    }

    private String generateBankAccountNumber()
    {
        Random rand = new Random();
        int min = 100000000;
        int max = 999999999;
        int numeroAleatorio = rand.nextInt((max - min) + 1) + min;
        return "192-" + Integer.toString(numeroAleatorio) + "-0";
    }
}
