# Sistemas de gestión bancaria

---

## Descripción

Sistema de gestión bancaria que permita crear nuevas cuentas y realizar transferencia entre cuentas.

---
## Pre-requisitos
- SDK: Oracle Open JDK 21.0.3
- Maven
- H2 database
- Lombok
- Springframework 3.5.14
- MapStruct 
---
# Instalación

Abrir el proyecto con un IDE como IntellijIDEA e instalar todos las dependencias.

---
## Endpoints

### POST /api/accounts
Crea una nueva cuenta bancaria.

Body:

``` json
{
    "name": "Fred M.",
    "document" : "123456789",
    "balance" : 1000.00
}
```


### GET /api/accounts/{accountBankId}
Muestra los datos de la cuenta bancaria

### POST /api/accounts/transfer
Realizar una transferencia a otra cuenta bancaria.

Body:
``` json
{
    "sourceAccount" : "192-450994769-0",
    "destinationAccount" : "192-691934863-0",
    "amount" : 20
}
```
---
### Patrones de diseño

#### Adapter

Actúa como un puente entre la lógica de negocio (núcleo) y los servicios externos (UI, BBDD, APIs), convirtiendo la interfaz de una tecnología específica a la interfaz que el puerto de la aplicación espera. Facilita el desacoplamiento, permitiendo cambiar infraestructuras sin modificar el dominio.

Ejemplo: 

BankAccountRepositoryAdapter.java
``` java

public class BankAccountRepositoryAdapter implements BankAccountRepositoryPort {

    private final BankAccountJpaRepository jpaRepository;
    private final BankAccountMapper mapper;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        log.info("Saving bank account: {}", bankAccount);

        BankAccountEntity entity = this.mapper.toEntity(bankAccount);

        BankAccountEntity savedEntity = this.jpaRepository.save(entity);
        BankAccount savedBankAccount = this.mapper.toDomain(savedEntity);

        log.info("Saved bank account via Spring Data: {}", savedBankAccount);

        return savedBankAccount;
    }

    @Override
    public Optional<BankAccount> FindById(Long id) {
        return this.jpaRepository.findById(id).map(this.mapper::toDomain);
    }
    
    @Override
    public BankAccount FindByAccountNumber(String accountNumber) {
        BankAccountEntity entity = this.jpaRepository.findByAccountNumber(accountNumber);
        return this.mapper.toDomain(entity);
    }
}
```


#### Singleton

Garantiza que una clase tenga una única instancia (objeto) y proporciona un punto de acceso global a ella. En el contexto hexagonal, se usa comúnmente en la capa de infraestructura.

Ejemplo: HexagonalConfig.java
``` java
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

```
---