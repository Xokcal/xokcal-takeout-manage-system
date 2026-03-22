package com.example.cangqiong.Service.Implement;

import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.ShopMapper;
import com.example.cangqiong.Service.Constant.ShopConstant;
import com.example.cangqiong.Service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    //获取营业状态
    @Override
    public Integer getShopStatus(){
        return shopMapper.getShopStatus();
    }

    //修改营业状态
    @Override
    public Integer updateShopStatus(Integer status){
        if (!CheckIsValidUtil.isValid(status)){
            log.warn(ShopConstant.UPDATE_SHOP_STATUS_PAARAM_ERROR);
            throw new BusinessException(ShopConstant.UPDATE_SHOP_STATUS_PAARAM_ERROR
                    , ShopConstant.CODE_FRONT);
        }
        return shopMapper.updateShopStatus(status);
    }

}
