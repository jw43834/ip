# IoT Platform(가칭)
 Spring 기반의 IoT 플랫폼

# 구성
* Edge Gateway
* etc..

# 모듈별 기능
* Edge Gateway
- 장치-센서-센서값 의 계층 구조(장치:센서=1:다,센서:센서값=1:다)를 가진다.
- 인증 : 장치를 G/W와 연결시도시 인가된 장치인지 확인한다.
- 수집 : 장치의 센서 데이터를 얻는다.
- 제어 : 장치를 제어한다.
- 설정 : 장치의 설정을 변경한다.(제어와 동일)

# 모듈별 설명
* Edge Gateway
1) InterconnectionManger
- Inbound, Outbound, 기준 정보 관리 등을 한다. Inbound<->Outbound 간 중계 역할을 한다.
- Inbound, Outbound Manager를 생성한다.
2) InboundManager
- 외부에서 Gateway로 들어오는 데이터와 네트워크를 관리한다.
- 설정에 등록된 수집 프로토콜의 Adopter를 생성한다.
(BLE, TCP, MQTT, MODBUS, BACNET, TCP, HTTP, etc...)
3) OutboundManager: 외부로 전송하는 데이터와 네트워크를 관리한다. 
- InboundManager의 Adopter에서 수집된 데이터를 외부로 전송한다.
- 설정에 등록된 외부 전송 프로토콜의 Adopter를 생성한다.

# Flow
1) 초기화
- InterconnectionManager생성 -> InterconnectionManager에서 InboundManager, OutboundManager생성 후 생성할 Adopter목록 각각 전달
-> OutboundManager에 전달된 Adopter목록을 기반으로 Adopter 생성 -> InboundManager에 전달된 Adopter목록을 기반으로 Adopter 생성 
2) 장치등록
- 장치 등록 API를 통한 장치 정보 등록 -> 인증 키 발급
3) 장치인증
- 인증된 키를 설정하여 네트워크 연결 요청 -> InboundManager의 해당 프로토콜 Adopter에서(별도 인증 모듈이 필요?, 프로토콜 통합 인증) 인증 키 체크 
-> 허가된 Key 일 경우 연결 -> ...
4) 수집(센서데이터 전송)
- (장치 등록,인증 후) 데이터 전송 -> 장치 정보 맵핑 -> InterconnectionManager 전처리 -> Outbound Adopter 전달 

# 사용방법
