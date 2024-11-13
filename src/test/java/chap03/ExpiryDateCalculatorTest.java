package test.java.chap03;

import main.java.chap03.ExpiryDateCalculator;
import main.java.chap03.PayData;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    @Test
    void pay_10000_won_expiry_date_after_month() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 11,11))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 12, 11));
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 1,31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2024, 2, 29));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 31))
                .billingDate(LocalDate.of(2024, 2, 29))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData, LocalDate.of(2024, 3, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 30))
                .billingDate(LocalDate.of(2024, 2, 28))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData2, LocalDate.of(2024, 3, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 5, 31))
                .billingDate(LocalDate.of(2024, 6, 30))
                .payAmount(10_000)
                .build();

        assertExpiryDate(payData3, LocalDate.of(2024, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2024, 5, 1)
        );

        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 3, 1))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2024, 6, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024, 1, 31))
                        .billingDate(LocalDate.of(2024, 2, 29))
                        .payAmount(20_000)
                        .build(),
                LocalDate.of(2024, 4, 30)
        );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024, 1, 31))
                        .billingDate(LocalDate.of(2024, 2, 29))
                        .payAmount(40_000)
                        .build(),
                LocalDate.of(2024, 6, 30)
        );

        assertExpiryDate(
                PayData.builder()
                        .firstBillingDate(LocalDate.of(2024, 3, 31))
                        .billingDate(LocalDate.of(2024, 4, 30))
                        .payAmount(30_000)
                        .build(),
                LocalDate.of(2024, 7, 31)
        );

    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2024, 11, 13))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2025, 11, 13)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

}
