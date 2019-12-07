# IoT Platform(가칭)
 Spring 기반의 IoT 플랫폼

# 구성
* Edge Gateway
* etc..

# 모듈별 기능
* Edge Gateway
- 인증 : 장치를 G/W와 연결시도시 인가된 장치인지 확인한다.
- 수집 : 장치의 센서 데이터를 얻는다.
- 제어 : 장치를 제어한다.
- 설정 : 장치의 설정을 변경한다.(제어와 동일)

# 모듈별 설명
* Edge Gateway
1) CentralManager: Inbound, Outbound, 기준 정보 관리 등을 한다. Inbound<->Outbound 간 중계 역할을 한다.
2) InboundManager: 외부에서 Gateway로 들어오는 데이터와 네트워크에 대한 관리를 한다.
3) OutboundManager: 외부로 전송하는 데이터를 관리한다. 

# 사용방법
