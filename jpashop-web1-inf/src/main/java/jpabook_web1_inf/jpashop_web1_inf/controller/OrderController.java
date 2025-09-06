package jpabook_web1_inf.jpashop_web1_inf.controller;

import jpabook_web1_inf.jpashop_web1_inf.domain.Member;
import jpabook_web1_inf.jpashop_web1_inf.domain.Order;
import jpabook_web1_inf.jpashop_web1_inf.domain.iteam.Item;
import jpabook_web1_inf.jpashop_web1_inf.repository.OrderSearch;
import jpabook_web1_inf.jpashop_web1_inf.service.ItemService;
import jpabook_web1_inf.jpashop_web1_inf.service.MemberService;
import jpabook_web1_inf.jpashop_web1_inf.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count){

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    // @ModelAttribute
    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model){
        List<Order> orders = orderService.finaOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
