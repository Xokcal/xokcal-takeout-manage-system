package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Order.OrderBody;
import com.example.cangqiong.Pojo.Order.OrderDetailBody;
import com.example.cangqiong.Pojo.Order.OrderPageRequestBody;
import com.example.cangqiong.Pojo.Order.OrderStatisticsBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    //查询订单
    @Select("select * from orders where id = #{id}")
    OrderBody selectOrderById(long id);

    //查询订单详情
    @Select("select * from order_detail where order_id = #{orderId}")
    List<OrderDetailBody> selectOrderDetailById(long orderId);

    //查询全部订单
    List<OrderBody> pageOrder(@Param("p") OrderPageRequestBody orderPageRequestBody
            , @Param("start") Integer start);

    //查询订单总数
    @Select("select count(*) from orders")
    Long selectOrderTotal();

    //修改接单为派送
    @Update("update orders set status =  #{status} where id = #{id}")
    Integer updateOrderStatusConfirm(@Param("status") Integer status, @Param("id") long id);

    //修改派送为完成
    @Update("update orders set status = #{status} ,delivery_time = #{deliveryTime} where id = #{id}")
    Integer updateOrderStatusDelivery(@Param("status") Integer status
            , @Param("deliveryTime")LocalDateTime deliveryTime, @Param("id") long id);

    //拒单
    @Update("update orders set status = #{status} , cancel_reason = #{cancelReason} " +
            ", cancel_time = #{cancelTime} where id = #{id}")
    Integer updateOrderRejection(@Param("status") Integer status
            ,@Param("cancelReason") String cancelReason,@Param("id") long id
            , @Param("cancelTime") LocalDateTime cancelTime);

    //完成
    @Update("update orders set status = #{status} , checkout_time = now() where id = #{id}")
    Integer updateOrderComplete(@Param("status") Integer status, @Param("id") long id);

    //取消
    @Update("update orders set status = #{status} , cancel_reason = #{cancelReason} " +
            ", cancel_time = #{cancelTime} where id = #{id}")
    Integer updateOrderCancel(@Param("status") Integer status , @Param("cancelReason") String cancelReason
            ,@Param("cancelTime") LocalDateTime cancelTime , @Param("id") long id);

    //订单统计
    @Select("select case when o.status = 3 then 'confirmed'\n" +
            "            when o.status = 4 then 'deliveryInProgress'\n" +
            "            when o.status = 2 then 'toBeConfirmed'\n" +
            "        else 'other'\n" +
            "        end as statistic ,\n" +
            "        count(*) as 'sum'\n" +
            "from orders o group by statistic;")
    List<Map<Object , Object>> getOrderStatistics();
}
