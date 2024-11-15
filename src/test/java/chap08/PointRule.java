package test.java.chap08;

import test.java.chap08.subs.Grade;
import test.java.chap08.subs.Product;
import test.java.chap08.subs.Subscription;

import java.time.LocalDate;

public class PointRule {

    public int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == Grade.GOLD) {
            point += 100;
        }
        return point;
    }
}
