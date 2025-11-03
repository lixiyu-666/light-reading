package cn.zealon.readingcloud.account.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * 公用线程池配置
 * @author: zealon
 * @since: 2020/4/17
 */
@Configuration
@ConfigurationProperties(prefix = "spring.thread-pool.common") // 这个前缀与Nacos配置匹配
public class ThreadPoolConfig {

    /** 核心线程数 */
    private int corePoolSize = 1; // 添加默认值
    /** 最大线程数 */
    private int maximumPoolSize = 5; // 添加默认值
    /** 线程存活时间 */
    private Long keepAliveTime = 0L; // 添加默认值，避免null
    /** 队列容量 */
    private int queueCapacity = 20480; // 添加默认值

    /**
     * 云书架数据消费线程池
     * @return
     */
    @Bean(value = "commonQueueThreadPool")
    public ExecutorService buildCommonQueueThreadPool(){
        // 安全处理，确保keepAliveTime不为null
        Long aliveTime = this.keepAliveTime != null ? this.keepAliveTime : 0L;

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("common-queue-thread-%d").build();

        // 实例化线程池
        ExecutorService pool = new ThreadPoolExecutor(
                this.corePoolSize,
                this.maximumPoolSize,
                aliveTime, // 使用确保不为null的值
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(this.queueCapacity),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        return pool;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}