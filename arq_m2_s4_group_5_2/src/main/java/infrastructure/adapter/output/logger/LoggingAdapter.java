package infrastructure.adapter.output.logger;

import application.port.output.LoggingPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class LoggingAdapter implements LoggingPort {

    @Override
    public void saveLogInConsole(String bankAccount, String balance) {
        log.info("Bank account {} received {} soles", bankAccount, balance);
    }
}
