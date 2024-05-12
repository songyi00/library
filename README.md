### Armeria + Spring WebFlux + Coroutine
`Armeria` 라우터로부터 사용자의 요청을 받을 경우, `ServiceRequestContext` 를 통해 사용자 요청에 대한 정보를 저장할 수 있다.  
비동기 시스템의 경우 하나의 요청에 대해 스레드가 변할 수 있기 때문에 스레드 로컬 기반으로 사용자의 요청 정보를 저장하면 데이터가 유실될 수 있다.  
`ServiceRequestContext`를 통해 사용자의 요청 ID 를 저장할 수 있으며 이를 추적 ID로 사용할 수 있다.  
**grpc-kotlin** 서비스의 `suspend function`을 사용하는 경우 `CoroutineContext`는 `ServiceRequestContext`와 이벤트 루프 `CoroutineDispatcher`의 조합으로 `ServiceRequestContext.current()`를 사용하여 suspend 함수에서 ServiceRequestContext에 접근할 수 있다.
```
class ArmeriaRequestCoroutineContext internal constructor(
    private val requestContext: RequestContext,
) : ThreadContextElement<SafeCloseable>, AbstractCoroutineContextElement(Key) {
    companion object Key : CoroutineContext.Key<ArmeriaRequestCoroutineContext>

    override fun updateThreadContext(context: CoroutineContext): SafeCloseable {
        return requestContext.push()
    }

    override fun restoreThreadContext(
        context: CoroutineContext,
        oldState: SafeCloseable,
    ) {
        oldState.close()
    }
}
```
코루틴의 `withContext`를 사용한다면 `RequestContext`는 전파 가능하며 `CoroutineScope`와 같이 별도의 독립적인 코루틴을 만든다면 `RequestContext`는 `CoroutineContext`로 변환하여 컨텍스트로 함께 추가해주어야 한다.
