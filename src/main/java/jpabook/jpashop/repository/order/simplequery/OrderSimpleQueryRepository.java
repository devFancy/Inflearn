package jpabook.jpashop.repository.order.simplequery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 성능 최적화 관련 레포지토리 경우
 * 따로 패키지를 만들어서 그 안에 관련 클래스를 만든다.
 */
@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    // 재사용 X, 유지보수성 O
    // 이런 식으로 성능 최적화에 대한 것은 따로 패키지를 분리하는 것을 권장함
    public List<OrderSimpleQueryDto> findOrderDto() {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
