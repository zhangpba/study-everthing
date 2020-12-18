package com.study.math.schdule;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 等额本息
 * <p>
 * 还款公式推导
 * 此处纯属娱乐 可忽略
 * <p>
 * 设贷款总额为A，银行月利率为β，总期数为m(个月)，月还款额设为X，则各个月所欠银行贷款为:
 * <p>
 * 第一个月A(1+β)-X
 * <p>
 * 第二个月(A(1+β)-X)(1+β)-X=A(1+β)2-X[1+(1+β)]
 * <p>
 * 第三个月((A(1+β)-X)(1+β)-X)(1+β)-X =A(1+β)3-X[1+(1+β)+(1+β)2] …
 * <p>
 * 由此可得第n个月后所欠银行贷款为 A(1+β)n –X[1+(1+β)+(1+β)2+…+(1+β)(n-1)]= A(1+β)n –X[(1+β)n - 1]/β
 * <p>
 * 由于还款总期数为m，也即第m月刚好还完银行所有贷款，
 * <p>
 * 因此有 A(1+β)m –X[(1+β)m - 1]/β=0
 * <p>
 * 由此求得 X = Aβ(1+β)m/[(1+β)m - 1]
 * <p>
 * 公式
 * 这个还是有用的，下面计算的时候会用到，尤其是每月还款额的公式，其他三个可用可不用。
 * <p>
 * 每月还款额=[总本金×月利率×(1+月利率)还款月数] ÷ [(1+月利率)总期数-1]
 * <p>
 * 每月应还利息=总本金×月利率×[(1+月利率)还款月数-(1+月利率)(还款月序号-1)]÷[(1+月利率)还款月数-1]
 * <p>
 * 每月应还本金=总本金×月利率×(1+月利率)(还款月序号-1)÷[(1+月利率)还款月数-1]
 * <p>
 * 总利息=[总期数×贷款本金×月利率×(1+月利率)还款月数]÷[(1+月利率)还款月数-1]-总本金
 * <p>
 * 或者
 * <p>
 * 总利息=月还本息×总期数-总本金
 *
 * @date 2020-12-18
 */
public class Interest {

    static int DECIMAL_SCALE = 9;
    static BigDecimal BIGDECAMAL_100 = new BigDecimal(100);
    static BigDecimal BIGDECAMAL_12 = new BigDecimal(12);
    static BigDecimal BIGDECAMAL_30 = new BigDecimal(30);
    static BigDecimal BIGDECAMAL_360 = new BigDecimal(360);

    /**
     * 等额本息 还款列表计算(自然月，即回款日期与出借日期相同)
     *
     * @param total    本金总额
     * @param yearRate 年利率
     * @param sumTerm  总期数
     * @throws Throwable
     */
    public static void calculation_DEBX_ZRY(BigDecimal total, BigDecimal yearRate, int sumTerm, String startDate) throws Throwable {
        BigDecimal monthRate = yearRate.divide(BIGDECAMAL_12, DECIMAL_SCALE, BigDecimal.ROUND_HALF_UP);

        // 每月还款额=[总本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^总期数-1]
        BigDecimal tmp = monthRate.add(new BigDecimal(1)).pow(sumTerm);//(1+月利率)^还款月数
        BigDecimal monthPayTotal = total
                .multiply(monthRate)
                .multiply(tmp)
                .divide(tmp.subtract(new BigDecimal(1)), 2, BigDecimal.ROUND_HALF_UP);

		/*总利息
			计算总利息有3种方法:
			1.在循环中将每月应还利息累加
			2.总利息=月还本息×总期数-总本金
			3.公式：总利息=[总期数×贷款本金×月利率×(1+月利率)^还款月数]÷[(1+月利率)^还款月数-1]-总本金
			具体使用哪种根据情况而定，推荐第二种
		*/
        //2:
        BigDecimal sumInterest = monthPayTotal.multiply(new BigDecimal(sumTerm)).subtract(total);
        //3:
		/*BigDecimal sumInterest = new BigDecimal(sumTerm).multiply(total).multiply(monthRate)
				.multiply(BigDecimal.ONE.add(monthRate).pow(sumTerm))
				.divide(BigDecimal.ONE.add(monthRate).pow(sumTerm).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP)
				.subtract(total);*/

        BigDecimal remainTotal = total;    //剩余本金付初始值为总本金
        BigDecimal monthPrincipal = BigDecimal.ZERO;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = sdf.parse(startDate);
        calendar.setTime(date);

        System.out.println("本金：" + total.setScale(2) + "\t年利率：" + yearRate.multiply(BIGDECAMAL_100) + "%\t总期数：" + sumTerm + "\t总利息:" + sumInterest);
        System.out.println("期次\t回款本息（元）\t回款利息（元）\t回款本金（元）\t剩余本金（元）\t回款日期");
        for (int i = 1; i <= sumTerm; i++) {
			/*
			 	每月应还本金和利息也有2种方法:
			 	1.公式:
			 		每月应还利息=总本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
			 		每月应还本金=总本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
			 */
			/*BigDecimal interest = total.multiply(monthRate)
					.multiply(BigDecimal.ONE.add(monthRate).pow(sumTerm).subtract(BigDecimal.ONE.add(monthRate).pow(i-1)))
					.divide(BigDecimal.ONE.add(monthRate).pow(sumTerm).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP);

			monthPrincipal = total.multiply(monthRate)
					.multiply(BigDecimal.ONE.add(monthRate).pow(i-1))
					.divide(BigDecimal.ONE.add(monthRate).pow(sumTerm).subtract(BigDecimal.ONE),2,BigDecimal.ROUND_HALF_UP);
			*/


            //2.月还利息=剩余本金×月利率
            //月还本金=月还本息-月还利息
            //具体使用哪种根据喜好而定，第二种更容易理解
            BigDecimal interest = remainTotal.multiply(monthRate).setScale(2, BigDecimal.ROUND_HALF_UP);
            //为避免因精度损失产生误差，最后一期  还款利息=月还本息-剩余本金
            //为避免利息产生负数的情况出现 当利息小于等于0时利息赋值为0.1元
            if (sumTerm == i) {
                interest = monthPayTotal.subtract(remainTotal);
                if (interest.compareTo(BigDecimal.ZERO) <= 0) {
                    interest = new BigDecimal(1).divide(new BigDecimal(10), 2, BigDecimal.ROUND_HALF_UP);
                }
            }
            //月还本金=月还本息-月还利息
            monthPrincipal = monthPayTotal.subtract(interest);

            remainTotal = remainTotal.subtract(monthPrincipal);
            calendar.add(Calendar.MONTH, 1);
            System.out.println(i + "\t" + monthPayTotal + "\t\t" + interest + "\t\t" + monthPrincipal + "\t\t" + remainTotal + "\t\t" + sdf.format(calendar.getTime()));
        }

    }


    //本金600000元，年利率4.9%，出借360个月，起息日2020-12-18
    public static void main(String[] args) throws Throwable {
        String startDate = "2020-18-25";
        BigDecimal total = new BigDecimal(600000);
        BigDecimal yearRate = BigDecimal.valueOf(0.049);
        int sumTerm = BIGDECAMAL_360.intValue();
        calculation_DEBX_ZRY(total, yearRate, sumTerm, startDate);
    }

}
