# Handmade Shop API

A modern e-commerce backend API for handmade products built with Spring Boot, featuring JWT authentication, PostgreSQL database, and comprehensive product catalog management.

## 🚀 Features

- **JWT Authentication** with refresh tokens
- **Product Catalog** with categories and products
- **CORS Support** for frontend integration
- **PostgreSQL Database** with JPA/Hibernate
- **RESTful API** with consistent versioning
- **Swagger/OpenAPI** documentation
- **Docker Support** for easy deployment
- **Comprehensive Testing** setup

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 4.0.3**
- **Spring Security** with JWT
- **PostgreSQL** database
- **Spring Data JPA** with Hibernate
- **MapStruct** for object mapping
- **Lombok** for boilerplate reduction
- **SpringDoc OpenAPI** for API documentation

## 📋 Prerequisites

- Java 21 or higher
- PostgreSQL 16+
- Maven 3.6+

## ⚡ Quick Start

### 1. Clone the repository
```bash
git clone <repository-url>
cd handmadeshop
```

### 2. Set up PostgreSQL
```bash
# Using Docker Compose
docker-compose up -d postgres

# Or install PostgreSQL locally and create database
createdb handmadeshop
```

### 3. Configure environment variables
```bash
# Copy environment file
cp .env.example .env

# Edit .env with your database credentials
```

### 4. Run the application
```bash
# Load environment variables and run
export $(cat .env | xargs) && ./mvnw spring-boot:run

# Or use Maven wrapper
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## 📚 API Documentation

Once the application is running, visit the API documentation at:
- **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

The documentation includes all available endpoints, request/response formats, authentication requirements, and examples.

The API uses JWT tokens for authentication:

1. **Register**: `POST /api/v1/auth/register`
2. **Login**: `POST /api/v1/auth/login`
3. **Refresh Token**: `POST /api/v1/auth/refresh-token`

Include the JWT token in the Authorization header:
```
Authorization: Bearer <your-jwt-token>
```

## 🏗️ API Endpoints

### Authentication
- `POST /api/v1/auth/register` - User registration
- `POST /api/v1/auth/login` - User login
- `POST /api/v1/auth/refresh-token` - Refresh access token

### Categories (Public)
- `GET /api/v1/categories` - Get all categories
- `GET /api/v1/categories/tree` - Get category tree
- `POST /api/v1/categories` - Create category (Admin)

### Products (Public)
- `GET /api/v1/products` - Get all products
- `GET /api/v1/products/{id}` - Get product by ID
- `POST /api/v1/products` - Create product (Admin)
- `PATCH /api/v1/products/{id}/activate` - Activate product (Admin)
- `PATCH /api/v1/products/{id}/deactivate` - Deactivate product (Admin)

## 🐳 Docker Deployment

### Using Docker Compose
```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

### Manual Docker Build
```bash
# Build the application
./mvnw clean package -DskipTests

# Build Docker image
docker build -t handmadeshop .

# Run with Docker
docker run -p 8080:8080 --env-file .env handmadeshop
```

## 🧪 Testing

```bash
# Run unit tests
./mvnw test

# Run integration tests
./mvnw verify

# Run with coverage
./mvnw test jacoco:report
```

## 📊 Monitoring

The application includes Spring Boot Actuator for monitoring:

- **Health Check**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/metrics
- **Info**: http://localhost:8080/actuator/info

## 🔧 Configuration

### Application Properties
Key configuration options in `application.yaml`:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}
  refresh-expiration: ${JWT_REFRESH_EXPIRATION}
```

### Environment Variables
- `SPRING_DATASOURCE_URL` - Database connection URL
- `SPRING_DATASOURCE_USERNAME` - Database username
- `SPRING_DATASOURCE_PASSWORD` - Database password
- `JWT_SECRET` - JWT signing secret
- `JWT_EXPIRATION` - JWT token expiration (ms)
- `JWT_REFRESH_EXPIRATION` - Refresh token expiration (ms)

## 🏗️ Project Structure

```
src/main/java/com/nttoan/handmadeshop/
├── application/
│   ├── config/          # Configuration classes
│   ├── exception/       # Global exception handlers
│   └── interceptor/     # Request interceptors
├── domain/
│   ├── catalog/         # Product catalog domain
│   │   ├── category/    # Category management
│   │   └── product/     # Product management
│   ├── common/          # Shared DTOs and entities
│   └── identity/        # User authentication
└── HandmadeshopApplication.java
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Support

For support, email support@handmadeshop.com or create an issue in this repository.

---

Built with ❤️ using Spring Boot