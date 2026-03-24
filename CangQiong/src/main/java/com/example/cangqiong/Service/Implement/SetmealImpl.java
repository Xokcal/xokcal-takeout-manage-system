package com.example.cangqiong.Service.Implement;

import ch.qos.logback.core.joran.conditional.IfAction;
import com.example.cangqiong.Common.CheckIsValid.CheckIsValidUtil;
import com.example.cangqiong.Common.Exception.BusinessException;
import com.example.cangqiong.Mapper.SetmealMapper;
import com.example.cangqiong.Pojo.Setmeal.*;
import com.example.cangqiong.Service.Constant.SetmealConstant;
import com.example.cangqiong.Service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SetmealImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    //分页查询套餐
    @Override
    public SetmealPageResonseBody pageSetmeal(PageSetmealRequetBody pageSetmealRequetBody) {
        if (!CheckIsValidUtil.isValid(pageSetmealRequetBody)) {
            log.warn(SetmealConstant.SETMEAL_SELECT_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_SELECT_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer start = SetmealConstant.startPage(pageSetmealRequetBody.getPage(), pageSetmealRequetBody.getPageSize());
        List<SetmealAndDishBody> setmealBodies = setmealMapper.selectSetmealPage(pageSetmealRequetBody , start);
        if (!CheckIsValidUtil.isValid(setmealBodies)) {
            log.warn(SetmealConstant.SETMEAL_SELECT_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_SELECT_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return new SetmealPageResonseBody(getSetmealTotal(), setmealBodies);
    }

    //新增套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer addSetmeal(SetmealAndDishBody setmealAndDishBody) {
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.ADD_SETMEAL_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.ADD_SETMEAL_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row1 = setmealMapper.insertSetmeal(setmealAndDishBody);
        List<SetmealDishBody> setmealDishBodies = batchKeyValueToSetmealDishBody(
                setmealAndDishBody.getId(), setmealAndDishBody.getSetmealDishes());
        Integer row2 = setmealMapper.insertSetmealDish(setmealDishBodies);
        if (row1 < 1 || row2 < 1) {
            log.warn(SetmealConstant.ADD_SETMEAL_RESULT_DEFEAT);
            throw new BusinessException(SetmealConstant.ADD_SETMEAL_RESULT_DEFEAT
                    , SetmealConstant.CODE_BEHIND);
        }
        return SetmealConstant.ADD_SETMEAL_PASS_RESULT;
    }

    //修改套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSetmeal(SetmealAndDishBody setmealAndDishBody) {
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.UPDATE_SETMEAL_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEAL_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row1 = updateSetmealDo(setmealAndDishBody);
        deleteSetmealDish(setmealAndDishBody.getId());
        List<SetmealDishBody> setmealDishBodies = batchKeyValueToSetmealDishBody(
                setmealAndDishBody.getId(), setmealAndDishBody.getSetmealDishes());
        Integer row2 = setmealMapper.insertSetmealDish(setmealDishBodies);
        if (row1 < 1 || row2 < 1) {
            log.warn(SetmealConstant.UPDATE_SETMEALRESULT_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEALRESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return SetmealConstant.END_RIGHT_RESULT_CODE;
    }

    //修改套餐：执行
    private Integer updateSetmealDo(SetmealAndDishBody setmealAndDishBody) {
        Integer row1 = setmealMapper.updateSetmeal(setmealAndDishBody);
        return row1;
    }

    //批量删除套餐
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteSetmealAndSetmealDish(List<Long> ids) {
        if (!CheckIsValidUtil.isValid(ids)) {
            log.warn(SetmealConstant.DELETE_SETMEAL_AND_DISH_PARRAM_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_AND_DISH_PARRAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row1 = setmealMapper.batchDeleteSetmeal(ids);
        if (row1 < 1) {
            log.warn(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        Integer row2 = setmealMapper.batchDeleteSetmealDish(ids);
        log.info(SetmealConstant.DELETE_SETMEAL_AND_DISH_RESULT_RIGHT, row1, row2);
        return SetmealConstant.END_RIGHT_RESULT_CODE;
    }

    //修改套餐状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer updateSetmealStatus(Integer status, Integer id) {
        if (!CheckIsValidUtil.isValid(status) || !CheckIsValidUtil.isValid(id)) {
            log.warn(SetmealConstant.UPDATE_SETMEAL_STATUS_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.UPDATE_SETMEAL_STATUS_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row = setmealMapper.updateSetmealStatus(status, id);
        return row;
    }

    //根据id查询套餐
    @Override
    public SetmealAndDishBody selectSetmealById(long id) {
        if (!CheckIsValidUtil.isValid(id)) {
            log.warn(SetmealConstant.SELECT_SETMEAL_BY_ID_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SELECT_SETMEAL_BY_ID_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        SetmealAndDishBody setmealAndDishBody = setmealMapper.selectSetmealById(id);
        List<SetmealDishBody> setmealDishBodes = setmealMapper.selectSetmealDishById(id);
        setmealAndDishBody.setSetmealDishes(setmealDishBodes);
        if (!CheckIsValidUtil.isValid(setmealAndDishBody)) {
            log.warn(SetmealConstant.SELECT_SETMEAL_BY_ID_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.SELECT_SETMEAL_BY_ID_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return setmealAndDishBody;
    }

    //删除餐品
    private void deleteSetmealDish(long setmealId) {
        if (!CheckIsValidUtil.isValid(setmealId)) {
            log.warn(SetmealConstant.DELETE_SETMEAL_DISH_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_DISH_PARAM_ERROR
                    , SetmealConstant.CODE_FRONT);
        }
        Integer row = setmealMapper.deleteSetmealDish(setmealId);
        if (row < 1) {
            log.warn(SetmealConstant.DELETE_SETMEAL_DISH_RESULT_ERROR);
            throw new BusinessException(SetmealConstant.DELETE_SETMEAL_DISH_RESULT_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
    }

    //将新增的主键赋值给每一个SetmealDishes的setmeal_id
    private List<SetmealDishBody> batchKeyValueToSetmealDishBody(long id, List<SetmealDishBody> list) {
        if (!CheckIsValidUtil.isValid(id) || !CheckIsValidUtil.isValid(list)) {
            log.warn(SetmealConstant.SETMEAL_VALUE_KEY_PARAM_ERROR);
            throw new BusinessException(SetmealConstant.SETMEAL_VALUE_KEY_PARAM_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        list.forEach(E -> {E.setSetmealId(id);});
        return list;
    }

    //获得套餐总数
    private long getSetmealTotal() {
        long sermealTotal = setmealMapper.getSermealTotal();
        if (!CheckIsValidUtil.isValid(sermealTotal)) {
            log.warn(SetmealConstant.GET_SETMEAL_TOTAL_ERROR);
            throw new BusinessException(SetmealConstant.GET_SETMEAL_TOTAL_ERROR
                    , SetmealConstant.CODE_BEHIND);
        }
        return sermealTotal;
    }


}
