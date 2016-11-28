


Maven

   <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>


        <dependency>
            <groupId>com.github.xuejike</groupId>
            <artifactId>bd_framework_access_control</artifactId>
            <version>v0.1</version>
        </dependency>





```
<mvc:interceptors>
    <!-- 使用bean定义一个Interceptor，直接定义在mvc:interceptors根下面的Interceptor将拦截所有的请求 -->

    <mvc:interceptor>
        <mvc:mapping path="/**"/>
        <mvc:exclude-mapping path="/user/loginSave.do"/>
        <!-- 定义在mvc:interceptor下面的表示是对特定的请求才进行拦截的 -->
        <bean class="com.bidanet.bdcms.plugin.bdAc.Interceptor.AcInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```
