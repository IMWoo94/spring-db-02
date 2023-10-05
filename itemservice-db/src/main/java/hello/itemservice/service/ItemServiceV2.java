package hello.itemservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.v2.ItemQueryRepositoryV2;
import hello.itemservice.repository.v2.ItemRepositoryV2;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServiceV2 implements ItemService {

	// 단순 JPA 사용 시 EntityManager 를 이용 해서 처리.
	// private final EntityManager em;
	// 스프링 데이터 JPA 를 사용하는 저장소
	private final ItemRepositoryV2 itemRepositoryV2;
	// QueryDSL 을 사용하는 저장소
	private final ItemQueryRepositoryV2 itemQueryRepositoryV2;

	@Override
	public Item save(Item item) {
		return itemRepositoryV2.save(item);
	}

	@Override
	public void update(Long itemId, ItemUpdateDto updateParam) {
		Item findItem = findById(itemId).orElseThrow();
		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	@Override
	public Optional<Item> findById(Long id) {
		return itemRepositoryV2.findById(id);
	}

	@Override
	public List<Item> findItems(ItemSearchCond cond) {
		return itemQueryRepositoryV2.findAll(cond);
	}
}
