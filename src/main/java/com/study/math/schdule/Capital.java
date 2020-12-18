package com.study.math.schdule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 等额本金
 * 公式
 * 月还本息=（本金/还款月数）+（本金-累计已还本金）×月利率
 * <p>
 * 每月本金=总本金/还款月数
 * <p>
 * 每月利息=（本金-累计已还本金）×月利率
 * <p>
 * 还款总利息=（还款月数+1）×贷款额×月利率/2
 * <p>
 * 还款总额=（还款月数+1）×贷款额×月利率/2+ 贷款额
 *
 * @date 2020-12-18
 */
public class Capital {

    static int DECIMAL_SCALE = 9;
    static BigDecimal BIGDECAMAL_100 = new BigDecimal(100);
    static BigDecimal BIGDECAMAL_12 = new BigDecimal(12);
    static BigDecimal BIGDECAMAL_30 = new BigDecimal(30);

    /**
     * 等额本金 还款列表计算(自然月)
     *
     * @param total     本金总额
     * @param yearRate  年利率
     * @param sumTerm   总期数
     * @param startDate 起息日
     * @throws Throwable
     */
    public static void calculation_DEBJ_ZRY(BigDecimal total, BigDecimal yearRate, int sumTerm, String startDate) throws Throwable {
        //月利率
        BigDecimal monthRate = yearRate.divide(BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        //还款总利息
        BigDecimal sumInterest = new BigDecimal(sumTerm + 1).multiply(total).multiply(monthRate).divide(new BigDecimal(2), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal monthPrincipal = total.divide(new BigDecimal(sumTerm), DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal remainTotal = total;    //剩余本金付初始值为总本金

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = sdf.parse(startDate);
        calendar.setTime(date);

        System.out.println("本金：" + total.setScale(2) + "\t年利率：" + yearRate.multiply(BIGDECAMAL_100) + "%\t总期数：" + sumTerm + "\t总利息:" + sumInterest);
        System.out.println("期次\t回款本息（元）\t回款利息（元）\t回款本金（元）\t剩余本金（元）\t回款日期");
        for (int i = 1; i <= sumTerm; i++) {

            //每月利息=（总本金-累计已还本金）×月利率
            BigDecimal interest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);

            //月还本息
            BigDecimal monthPayTotal = monthPrincipal.add(interest);

            remainTotal = remainTotal.subtract(monthPrincipal);
            calendar.add(Calendar.MONTH, 1);
            System.out.println(i + "\t" + monthPayTotal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + interest.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + monthPrincipal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + remainTotal.setScale(2, BigDecimal.ROUND_HALF_UP) + "\t\t" + sdf.format(calendar.getTime()));

        }
    }


    //本金600000元，年利率4.9%，出借360个月，起息日2020-12-18
    public static void main(String[] args) throws Throwable {
        String startDate = "2020-12-18";
        BigDecimal total = new BigDecimal(600000);
        BigDecimal yearRate = BigDecimal.valueOf(0.049);
        int sumTerm = 360;
        calculation_DEBJ_ZRY(total, yearRate, sumTerm, startDate);
    }

}
