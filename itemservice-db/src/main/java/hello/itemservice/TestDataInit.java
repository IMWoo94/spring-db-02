package hello.itemservice;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

	private final ItemRepository itemRepository;

	/**
	 * 확인용 초기 데이터 추가
	 * 스프링 컨테이너가 완전히 초기화를 완료하고 실행 준비가 되었을 때 발생하는 이벤트이며,
	 * 스프링이 이 시점에 해당 어노테이션이 붙은 initData() 메소드를 호출 해준다.
	 * @PostContruct 를 사용하면 되지 않느냐 할 수 있는데, 사용하는 경우에 AOP 같은 부분이 아직 다 처리 되지 않은
	 * 시점에 호출이 될 수 있기 떄문에, 문제가 발생할 수 있습니다.
	 * 예를 들어 @Transactional 과 관련된 AOP 가 적용되지 않은 상태로 호출 될 수 있다.
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void initData() {
		log.info("test data init");
		itemRepository.save(new Item("itemA", 10000, 10));
		itemRepository.save(new Item("itemB", 20000, 20));
	}

}
