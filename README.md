# spring boot + vue全栈开发实战案例

## S9：缓存

### 9.1：Ehcache2.x缓存

### 9.2：Redis单机缓存

## S10：安全管理

### 10.1：Spring Security的基本配置

只要存在spring-boot-starter-security依赖，自动保护项目的所有资源。
在不进行任何配置下默认用户名为user，密码随机生成
在application.properties中可以配置默认的用户名和密码
可以通过继承WebSecurityConfigurerAdapter,并重写configure(AuthenticationManagerBuilder auth)进行基于内存的认证配置
可以通过重写configure(HttpSecurity http)实现HttpSecurity的配置，即哪个用户有哪个页面的访问权限

### 10.2：登录表单详细配置，即前后端分离的配置

添加formLogin和logout的配置

### 10.3：基于数据库的认证

注意BCryptPasswordEncoder是一个强哈希算法，每次encode的值都不一样，但它自己的验证方法可以验证成功。
另外该例子中设置了springboot项目启动自动执行SQL文件
注意基于数据库的认证方式，角色名前面要有ROLE_前缀
本例子中设置了角色继承机制

### 10.4：动态权限配置

将权限和角色的关联也交给数据库。
主要实现AccessDecisionManager和FilterInvocationSecurityMetadataSource两个接口

###　10.5：整合OAuth2

OAuth是一个开放标准，该标准允许用户让第三方应用访问该用户在某一个网站上存储的私密资源（如头像、照片、视频等），
而在这个过程中无须将用户名和密码提供给第三方应用。
实现这一功能是通过提供一个令牌（token），而不是用户名和密码来访问他们存放在特定服务提供者的数据。
每一个令牌授权一个特定的网站在特定的时间段内访问特定的资源。
这样，OAuth让用户可以授权第三方网站灵活地访问存储在另外一些资源服务器的特定信息，而非所有内容。
例如：用户想通过QQ登录知乎，这时知乎就是一个第三方应用，知乎要访问用户的一些基本信息就需要得到用户的授权，
如果用户把自己的QQ用户名和密码告诉知乎，那么知乎就能访问用户的所有数据，并且只有用户修改密码才能收回授权，
这种授权方式安全隐患很大，如果使用OAuth，就能很好地解决这一问题

本例子中令牌使用redis存储

用POST请求`http://localhost:8080/oauth/token`并携带如下数据
> username:wfb
  password:123
  grant_type:password
  client_id:password
  scope:all
  client_secret:123

返回结果为：
> {
      "access_token": "e79e3745-f39b-44af-ac09-1a61d9140fa1",
      "token_type": "bearer",
      "refresh_token": "39d9f624-d425-48ea-80eb-68e570971d82",
      "expires_in": 1799,
      "scope": "all"
  }

其中access_token为获取资源时使用的令牌；refresh_token为刷新access_token时使用的令牌；
expires_in为access_token的过期时间

获取新的access_token的地址为`http://localhost:8080/oauth/token`并携带如下数据
> grant_type:refresh_token
  refresh_token:39d9f624-d425-48ea-80eb-68e570971d82
  client_id:password
  client_secret:123

访问资源请求时需要加上参数access_token=e79e3745-f39b-44af-ac09-1a61d9140fa1

### 10.6：整合Shiro

## s11：整合WebSocekt

### 11.1：消息群发

### 11.2：消息点对点发送

## s12：消息服务

## s13：企业开发

###　13.1：邮件发送

[freemarker模板](http://freemarker.foofun.cn/)通过创建模板，然后配置模板的路径来使用，其后缀是ftl

thymeleaf模板默认放在resources/templates目录下，后缀为html

### 13.2：定时任务

- springboot的自带实现，只需导入spring-boot-starter-web,详见MySchedule
- 其余为spring quartz的实现

### 13.3：批处理

### 13.4：swagger2