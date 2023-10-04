package hello.itemservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
// 객체 명과 테이블 명이 동일 하면 생략 해도 됩니다.
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 스프링 부트와 통합 해서 사용 하는 경우 카멜 케이스를 언더스코어로 자동으로 변환 해준다.
	// 따라서 아래의 설정을 생략 해도 된다.
	@Column(name = "item_name", length = 10)
	private String itemName;
	private Integer price;
	private Integer quantity;

	// JAP 는 public, protected 의 기본 생성자가 필수 입니다.
	public Item() {
	}

	public Item(String itemName, Integer price, Integer quantity) {
		this.itemName = itemName;
		this.price = price;
		this.quantity = quantity;
	}
}
