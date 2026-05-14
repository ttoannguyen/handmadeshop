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



POST /products
   ↓
Controller
   ↓
CreateProductUseCase
   ↓
Product (domain)
   ↓
ProductRepository (interface - domain)
   ↓
ProductRepositoryImpl (infrastructure)
   ↓
ProductJpaEntity (@Entity)
   ↓
DB




User:

- Id (UUID/ Long)
- username (unique)
- email (unique)
- passwordHash
- fullname
- dateofbirth
- role (USER, ADMIN)
- enabled
- createdAt
- updatedAt


Product

- Id
- Name
- Description
- basePrice
- Category (ManyToOne)
- Images (OneToMany)
- CreatedAt
- UpdatedAt
- Active


Category

- Id
- Name
- Slug (improve seo & urls)
- parrentCategory (seft reference)


Color

- Id
- Name
- hexCode


Product Variants

- id
- product
- sku (unique)
- color
- size
- stock
- priceAdjustment
- active


ProductImage

- id
- product
- imageUrl
- isPrimary
- displayOrder


Cart

- id
- user
- updatedAt


CartItem

- id
- cart
- productVariant
- quantity
- priceAtAddTime


Order

- id
- user
- status (PENDING, PAID, SHIPPED, COMPLETED)
- paymentMethod
- paymentStatus
- shippingAddress
- totalPrice
- createdAt
- totalPrice
- updateAt


OrderItem

- id
- order
- productVariant
- quantity
- priceAtPurchase (Prices may change later, but orders must remain accurate)


OrderStatus



Address

- id
- user
- fullname
- phone
- street
- city
- country
- postalCode
- isDefault




Review

- id
- user
- product
- rating
- comment
- createdAt


```
public enum OrderStatus {
    PENDING,
    PAID,
    SHIPPED,
    COMPLETED,
    CANCELLED
}

```
User registers → Cart is created

User adds items → CartItems created

User checks out → Order created

Cart is cleared (NOT deleted)

User continues shopping with same cart



