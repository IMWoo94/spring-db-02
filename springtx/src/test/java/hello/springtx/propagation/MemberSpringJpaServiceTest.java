package hello.springtx.propagation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class MemberSpringJpaServiceTest {

	@Autowired
	MemberSpringJpaService memberService;

	@Autowired
	MemberSpringJpaRepository memberRepository;

	@Autowired
	LogSpringJpaRepository logRepository;

	@Test
	void outerTxOff_success() {
		// given
		String username = "outerTxOff_success";

		// when
		memberService.joinV1(username);

		// then : 모든 데이터가 정상 저장 된다.
		assertTrue(memberRepository.findByUsername(username).isPresent());
		assertTrue(logRepository.findByMessage(username).isPresent());
	}

}