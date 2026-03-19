# RoboOps

로봇 상태 관리 및 제어 시스템

## 기능
- 로봇 조회 (/api/robots)
- 상태 변경 (/api/robots/{code}/command)
- 텔레메트리 수신 (/api/telemetry)
- 이벤트 로그 (/api/events)

## 구조
- robot
- telemetry
- event

## 목적
GooSage와 연동되는 실행 계층 (Execution Layer)