package by.novik.hibernatedemo.service;

import by.novik.hibernatedemo.entity.Order;
import by.novik.hibernatedemo.entity.OrderLine;
import by.novik.hibernatedemo.entity.Product;
import by.novik.hibernatedemo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
//т е в репозитории комады, в сервисе решаем выполнять их ци не
public class OrderService {
    private final OrderRepository repository;
    private final ProductService productService;//не очень красиво когда в одном сервере несколько репозиториев
    public Order save(Order order) {

        return repository.save(order);
    }


    public Optional<Order> findById(Long id) {

        return repository.findById(id);
    }


    public List<Order> findAll() {

        return repository.findAll();
    }


    public void delete(Long id) {
        repository.delete(id);
    }

//    public Order addOrderLine(Long id, OrderLine line) {
//        Order order = repository.findById(id).orElseThrow(); //ищет в базе ордер с такой ай ди. не найдет - кинет ошибку
//        order.addOrderLine(line);
//        repository.save(order);
//        return order;
//    }
    public Order addOrderLine(Long id, OrderLine line) {
        Order order = repository.findById(id).orElseThrow();//эта строчка попытается найти ордер по id, если не найдет то кинет ошибку
        log.error("id{}", line.getProduct().getId());
        Product product = productService.findById(line.getProduct().getId());
        line.setProduct(product);
        order.addOrderLine(line);
        return repository.save(order);
    }

    public void updateProduct(Long id, Long orderLineId, Product product) {
        Order order = repository.findById(id).orElseThrow();
        order.getOrderLines().stream().filter(el -> Objects.equals(el.getId(), orderLineId)).findAny()
                .ifPresent(orderLine -> orderLine.setProduct(product)); //найти заказ продукта  и там поменять если нашло
        repository.save(order);
    }
}
