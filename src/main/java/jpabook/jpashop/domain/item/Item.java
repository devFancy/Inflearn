package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.domain.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
    @OneToMany(mappedBy = "item")
    private Collection<OrderItem> orderItem;

    public Collection<OrderItem> getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(Collection<OrderItem> orderItem) {
        this.orderItem = orderItem;
    }
}
