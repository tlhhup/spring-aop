# spring-aop
2. 重要概念:
	1. (IOC)DI：反转控制(对象的创建和维护交由外部容器进行管理)，依赖注入(反射)
		1. 构造函数
		2. 工厂
		3. 属性注入
		4. 方法注入
	2. AOP:面向切面编程(动态代理)
		1. JDK的动态代理--->面向接口
		2. Cglib的动态代理--->面向类
3. 事务管理
	1. AOP
	2. 处理方式
		1. 基于xml配置
		2. 基于注解
2. IOC：反转控制
	1. 环境：
2. AOP：动态代理
	1. 面向切面编程

			//上菜之前--->问候---->(增强）
			服务上菜---->上菜品------>关注真实的地方--->方法
			//上菜之后--->尽情享用
	2. 增强-->外置一些服务(Advice)--->方法级别
		1. 前置增强：methodBeforeAdvice--->方法调用之前执行

				public class GreetingAdvice implements MethodBeforeAdvice {
				
					/**
					 * @param method 方法名称
					 * @param args 参数值
					 * @param obj 调用的对象
					 */
					@Override
					public void before(Method method, Object[] args, Object obj) throws Throwable {
						String clientName=(String) args[0];
						System.out.println("欢迎光临："+clientName);
					}
				
				}
		2. 后置增强：AfterReturningAdvice--->方法执行之后执行

				public class GreetAfterAdvice implements AfterReturningAdvice {
	
					@Override
					public void afterReturning(Object obj, Method method, Object[] args, Object arg3) throws Throwable {
						System.out.println("指定的方法名："+method.getName());
						System.out.println("方法执行之后执行");
					}
				
				}

		3. 环绕增强：MethodInterceptor--->以上两者的结合

				public class RoundAdivce implements MethodInterceptor {

					@Override
					public Object invoke(MethodInvocation invocation) throws Throwable {
						Object[] arguments = invocation.getArguments();
						String clientName=(String) arguments[0];
						
						System.out.println("欢迎"+clientName+"光临");
						
						//执行预期调用的方法
						Object obj = invocation.proceed();
						
						System.out.println("欢迎下次光临");
						
						return obj;
					}
				
				}
	4. ProxyFactory-->代理工厂

			//创建代理目标对象
			ListWaiter target=new ListWaiter();
			
			ProxyFactory proxyFactory=new ProxyFactory();
			//强制使用CGlib创建代理对象--默认使用JDK的动态代理
			proxyFactory.setOptimize(true);
			
			//JDK--->面向接口
			//设置代理对象
			proxyFactory.setTarget(target);
			//设置接口
			proxyFactory.setInterfaces(target.getClass().getInterfaces());
			//设置增强
			RoundAdivce advice=new RoundAdivce();
			proxyFactory.addAdvice(advice);
			
			//创建代理对象
			Waiter proxy = (Waiter) proxyFactory.getProxy();
			proxy.greetTo("王五");
	5. 切点：以上的处理会对代理对象中所有的方法都添加增强，当需要只针对某些方法添加增强时则需要使用切点进行控制
		1. spring通过Pointcut接口来描述切点，Pointcut由ClassFilter和MethodMatcher构造，通过ClassFilter定义到某些特定的类上，通过MethodMatcher定位到某些特定的方法上。
		2. MethodMatcher(方法匹配器)：
			1. 静态方法匹配器：仅对方法名签名(方法名和入参类型及顺序)进行匹配，只会执行一次
			2. 动态方法匹配器：在运行期检查方法入参的值，调用方法时都会执行
			3. **方法匹配器的类型：**由isRuntime()返回值决定，false为静态方法匹配器
		4. 切点类型
			1. 静态方法切点：StaticMethodMatcherPointcut(匹配所有的类)
			2. 动态方法切点：DynamicMethodMatcherPointcut
			3. 注解切点：AnnotationMatchingPointcut
			4. 表达式切点：ExpressionPointcut
			5. 流程切点：ControlFlowPointcut
			6. 复合切点:ComposablePointcut
		7. 切面：由于增强及包含横切代码，又包含部分连接点的信息(方法前、方法后等的方位信息)，则可以通过增强类生成切面。**但切点近代表目标类连接点的部分信息，所以仅有切点，是无法创建切面，必须结合增强才能制作出切面**。
			1. 切面分类
				1. Advisor： 代表一般切面，近包含Advice,针对所有目标类的所有方法
				2. PointcutAdvisor：代表具有切点的切面，包含Advice和Pointcut,可以通过类、方法名来灵活地定义切面的连接点
				3. IntroductionAdvisor：代表引介切面
			4. PointcutAdvisor实现类
				1. DefaultPointcutAdvisor：
				2. NameMatcherMethodPointcutAdvisor：通过该类可以定义方法名进行切点定义的切面
				3. RegexpMethodPointcutAdvisor:正则表达式匹配方法名进行切点定义的切面。
				4. StaticMethodPointcutAdvisor:静态方法匹配器切点定义的切面
				5. AspectJExpressionPointcutAdvisor：用于AspectJ切点表达式定义切点的切面
				6. AspectJPointcutAdvisor:用于AspectJ语法定义切点的切面
		7. 使用：
			1. 创建切点：决定哪些类和该类中方法需要织入增强

					public class GreetStaticMethodMatcherPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {
	
						private static final long serialVersionUID = 1L;
					
						/**
						 * 用于决定切点的方法
						 */
						@Override
						public boolean matches(Method method, Class<?> clazz) {
							return "greetTo".equals(method.getName());
						}
						
						/**
						 * 用于决定类
						 */
						@Override
						public ClassFilter getClassFilter() {
							return new ClassFilter() {
								
								@Override
								public boolean matches(Class<?> clazz) {
									return Waiter.class.isAssignableFrom(clazz);
								}
							};
						}
						
					}
			2. 测试代码
	
					//创建代理目标对象
					ListWaiter target=new ListWaiter();
					
					ProxyFactory proxyFactory=new ProxyFactory();
					//强制使用CGlib创建代理对象--默认使用JDK的动态代理
					proxyFactory.setOptimize(true);
					
					//JDK--->面向接口
					//设置代理对象
					proxyFactory.setTarget(target);
					//设置接口
					proxyFactory.setInterfaces(target.getClass().getInterfaces());
					
					//创建增强
					RoundAdivce advice=new RoundAdivce();
					//创建切点
					GreetStaticMethodMatcherPointcutAdvisor advisor=new GreetStaticMethodMatcherPointcutAdvisor();
					//注入增强--->创建切面
					advisor.setAdvice(advice);
					
					//设置切面
					proxyFactory.addAdvisor(advisor);
					
					//创建代理对象
					Waiter proxy = (Waiter) proxyFactory.getProxy();
					proxy.greetTo("王五");//该方法织入了增强
					
					proxy.serverTo("王五");
