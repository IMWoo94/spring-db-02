package hello.springtx.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TxLevelTest {
	@Autowired
	LevelService service;

	@Test
	void orderTest() {
		service.write();
		service.read();
	}

	@TestConfiguration
	static class TxApplyLevelConfig {
		@Bean
		LevelService levelService() {
			return new LevelService();
		}

	}

	// 인터페이스에 적용해도 가능은 하나,
	// 간혼 프록시 AOP 가 반영되지 않을 수도 있습니다.
	// 그래서 구체 클래스에 @Transactional 을 선언하는 게 좋아요
	@Transactional(readOnly = true)
	static interface LevelInterface {

	}

	@Slf4j
	// @Transactional(readOnly = true)
	static class LevelService implements LevelInterface {

		@Transactional(readOnly = false)
		public void write() {
			log.info("call write");
			printTxInfo();
		}

		public void read() {
			log.info("call read");
			printTxInfo();
		}

		private void printTxInfo() {
			boolean txActive = TransactionSynchronizationManager.isActualTransactionActive();
			log.info("tx active={}", txActive);
			boolean readOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
			log.info("tx readOnly={}", readOnly);
		}

	}
}
