package demo.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class WfbHealth implements HealthIndicator { //自定义健康指示器
    @Override
    public Health health() {
        if (checkNetwork()) {
            return Health.up().withDetail("msg", "网络连接正常...").build();
        }
        return Health.down().withDetail("msg", "网络断开...").build();
    }

    private boolean checkNetwork() {
        return true;
    }
}
