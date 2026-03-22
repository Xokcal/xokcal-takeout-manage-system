package com.example.cangqiong.Controller;

import com.example.cangqiong.Pojo.R;
import com.example.cangqiong.Service.Implement.ShopImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class ShopController {

    @Autowired
    private ShopImpl shopImpl;

    //获取营业状态
    @GetMapping("/shop/status")
    R getShopStatus(){
        Integer r = shopImpl.getShopStatus();
        return new R().ok(r , new Object());
    }

    //修改营业状态
    @PutMapping("/shop/{status}")
    R updateShopStatus(@PathVariable Integer status){
        Integer r = shopImpl.updateShopStatus(status);
        return new R().ok(r);
    }
}
