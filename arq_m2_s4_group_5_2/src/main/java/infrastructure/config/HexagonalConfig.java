package infrastructure.config;

import application.port.input.CreateBankAccountUseCase;
import application.port.input.FindBankAccountUseCase;
import application.port.input.TransferBankAccountUseCase;
import application.port.output.BankAccountRepositoryPort;
import application.port.output.ClientRepositoryPort;
import application.port.output.LoggingPort;
import application.usecase.CreateBankAccountUseCaseImpl;
import application.usecase.FindBankAccountUseCaseImpl;
import application.usecase.TransferBankAccountUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HexagonalConfig {

    @Bean
    public CreateBankAccountUseCase createBankAccountUseCase(BankAccountRepositoryPort bankAccountRepositoryPort,
                                                             ClientRepositoryPort clientRepositoryPort) {
        return new CreateBankAccountUseCaseImpl(bankAccountRepositoryPort, clientRepositoryPort);
    }

    @Bean
    public FindBankAccountUseCase findBankAccountUseCase(BankAccountRepositoryPort bankAccountRepositoryPort ) {
        return  new FindBankAccountUseCaseImpl(bankAccountRepositoryPort);
    }

    @Bean
    public TransferBankAccountUseCase transferBankAccountUseCase(BankAccountRepositoryPort bankAccountRepositoryPort,
                                                                 LoggingPort loggingPort) {
        return  new TransferBankAccountUseCaseImpl(bankAccountRepositoryPort, loggingPort);
    }
}
