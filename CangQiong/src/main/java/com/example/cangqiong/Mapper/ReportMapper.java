package com.example.cangqiong.Mapper;

import com.example.cangqiong.Pojo.Report.ReportOrdersStatisticsVO;
import com.example.cangqiong.Pojo.Report.ReportTimeRequestBody;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface ReportMapper {

    /*用户统计接口*/

    //获得要查询的所有日期
    @Select("select date(create_time) from user " +
            "where create_time > #{begin} and create_time <= #{end} " +
            "order by create_time asc")
    List<String> userAllDate(ReportTimeRequestBody reportTimeRequestBody);

    //每日新增用户数列表
    @Select("select count(*) as every_day_new_acount\n" +
            "    from user where create_time > #{begin} and create_time <= #{end} " +
            "group by create_time order by create_time asc")
    List<String> userEveryDayNew(ReportTimeRequestBody reportTimeRequestBody);

    //期间每日总人数
    @Select("select ((select count(*) from user where create_time < daily)) as curr_sum\n" +
            "from(select date(create_time) as daily , count(*) as every_day_new_acount\n" +
            "from user where create_time > #{begin} and create_time <= #{end} group by daily\n" +
            ") o order by date(daily) asc")
    List<String> userEveryDayCount(ReportTimeRequestBody reportTimeRequestBody);

    /*盈利统计接口*/

    //营业利润
    @Select("select sum(amount) from orders\n" +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end} \n" +
            "group by date(checkout_time) order by date(checkout_time) asc")
    List<String> turnoverEveryDayMargin(ReportTimeRequestBody reportTimeRequestBody);

    //营业日期
    @Select("select date(checkout_time) from orders " +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end} \n" +
            "group by date(checkout_time) order by date(checkout_time) asc")
    List<String> turnoverAllDate(ReportTimeRequestBody reportTimeRequestBody);

    /*查询销量排名top10接口*/

    //top10名字
    @Select("select od.name as name\n" +
            "from order_detail od left join orders o on od.order_id = o.id\n" +
            "where  date(checkout_time) > #{begin} and date(checkout_time) <= #{end} and o.status = 5\n" +
            "group by name order by name desc limit 0 , 10")
    List<String> top10Names(ReportTimeRequestBody reportTimeRequestBody);

    //top10销量
    @Select("select sum(od.number) as number\n" +
            "from order_detail od left join orders o on od.order_id = o.id\n" +
            "where  date(checkout_time) > #{begin} and date(checkout_time) <= #{end} and o.status = 5\n" +
            "group by name order by number desc limit 0 , 10;")
    List<String> top10Numbers(ReportTimeRequestBody reportTimeRequestBody);

    /*
    订单管理接口
    */

    //日期列表，以逗号分隔
    @Select("select date (checkout_time) from orders\n" +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end} and status = 5 " +
            "group by date (checkout_time)\n" +
            "order by date (checkout_time) asc ")
    List<String> orderDateList(ReportTimeRequestBody reportTimeRequestBody);

    //订单完成率
    @Select("SELECT\n" +
            "  ROUND((SELECT COUNT(*) FROM orders\n" +
            "     WHERE date(checkout_time) > #{begin}\n" +
            "       AND date(checkout_time) <= #{end}\n" +
            "       AND status = 5)\n" +
            "    /\n" +
            "    (SELECT COUNT(*) FROM orders\n" +
            "     WHERE date(checkout_time) > #{begin}\n" +
            "       AND date(checkout_time) <= #{end})\n" +
            "  , 2) AS orderCompletionRate")
    BigDecimal orderCompletionRate(ReportTimeRequestBody reportTimeRequestBody);

    //每日订单
    @Select("select count(*)from orders\n" +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end}\n" +
            "group by date (checkout_time) order by date(checkout_time) asc")
    List<String> orderDailyOrderList(ReportTimeRequestBody reportTimeRequestBody);

    //订单总数列表
    @Select("select count(*) from orders\n" +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end}\n" +
            "group by date(checkout_time) order by date (checkout_time) asc")
    List<String> orderCountList(ReportTimeRequestBody reportTimeRequestBody);

    //订单总数
    @Select("select count(*) from orders\n" +
            "where date(checkout_time) >  #{begin} and date(checkout_time) <= #{end}")
    Integer orderCount(ReportTimeRequestBody reportTimeRequestBody);

    //有效订单数列表
    @Select("select count(*)from orders\n" +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end} and status = 5\n" +
            "group by date(checkout_time) order by date (checkout_time) asc")
    List<String> orderEffectiveOrderList(ReportTimeRequestBody reportTimeRequestBody);

    //有效订单总数
    @Select("select count(*) from orders " +
            "where date(checkout_time) > #{begin} and date(checkout_time) <= #{end} and status = 5;")
    Integer orderEffecttiveOrderCount(ReportTimeRequestBody reportTimeRequestBody);

}
