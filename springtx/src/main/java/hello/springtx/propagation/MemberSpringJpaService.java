package hello.springtx.propagation;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberSpringJpaService {

	private final MemberSpringJpaRepository memberRepository;
	private final LogSpringJpaRepository logRepository;

	public void joinV1(String username) {
		Member member = new Member(username);
		Log logMessage = new Log(username);
		log.info("== memberRepository 호출 시작 ==");
		memberRepository.save(member);
		log.info("== memberRepository 호출 종료 ==");

		log.info("== logRepository 호출 시작 ==");
		logRepository.save(logMessage);
		log.info("== logRepository 호출 종료 ==");
	}
}
