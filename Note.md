### LOGIN
- verify password
- generate access + refresh
- lưu refresh token (DB)

### REFRESH
- verify refresh token
- rotate (tạo token mới + revoke cũ)

### LOGOUT
- revoke refresh token
- blacklist access token

### REQUEST API
- JwtFilter check:
    - token valid
    - không nằm trong blacklist

---

domain        →-business \
application   → điều phối \
infrastructure→ kỹ thuật (DB, JWT, Spring) \
presentation  → API


domain        ❌ KHÔNG Spring, KHÔNG DTO, KHÔNG Controller \
application   ❌ KHÔNG JPA \
infrastructure ❌ KHÔNG business logic \
presentation  ❌ KHÔNG chứa logic \


presentation/
 ├── rest/
 │     ├── AuthController
 │     └── UserController
 │
 ├── websocket/
 │     └── NotificationSocketHandler
 │
 ├── messaging/
 │     └── OrderCreatedConsumer
 │
 ├── batch/
 │     └── CleanupJob
 │
 └── graphql/
       └── UserResolver