# Task Manager REST API

Учебный backend-проект для управления задачами на **Java 21** и **Spring Boot**. Приложение позволяет создавать, просматривать, редактировать, завершать и удалять задачи, а также фильтровать их по категориям.

## Технологии

- Java 21
- Spring Boot (Spring Web, Spring Data JPA, Validation)
- Hibernate / JPA
- PostgreSQL 17
- Docker Compose
- Maven
- springdoc-openapi / Swagger UI

## Возможности

- Создание задачи с привязкой к пользователю и категории
- Получение задачи по ID и списка всех задач
- Фильтрация списка задач по категории
- Полное обновление задачи и отметка о выполнении
- Удаление задачи
- Валидация входных данных
- Обработка ошибок `400 Bad Request` и `404 Not Found`
- Интерактивная документация API через Swagger UI

В базе используются три сущности: `User`, `Task` и `Category`. **REST API для управления пользователями и категориями отдельно пока не реализовано**: они используются как связанные данные при создании задач.

## Запуск локально

**Требования:** JDK 21, Docker и Docker Compose. На macOS можно использовать Colima вместо Docker Desktop.

1. Склонируйте репозиторий и перейдите в каталог проекта:

   ```bash
   git clone <URL_ВАШЕГО_РЕПОЗИТОРИЯ>
   cd SpringEducationProj
   ```

   Если каталог после клонирования называется иначе, перейдите в него.

2. Запустите PostgreSQL:

   ```bash
   colima start

   docker compose up -d
   ```

3. Убедитесь, что параметры подключения в `src/main/resources/application.properties` соответствуют настройкам локальной базы данных из `compose.yaml`. В учебной конфигурации PostgreSQL доступен по адресу `localhost:5433`, база — `task_manager`.

4. Запустите приложение из IntelliJ IDEA либо через Maven Wrapper:

   ```bash
   ./mvnw spring-boot:run
   ```

После запуска REST API доступен по адресу `http://localhost:8080`.

## REST API

| Метод | Путь | Назначение |
| --- | --- | --- |
| `GET` | `/api/tasks` | Получить все задачи |
| `GET` | `/api/tasks?categoryId=1` | Получить задачи выбранной категории |
| `GET` | `/api/tasks/{id}` | Получить задачу по ID |
| `POST` | `/api/tasks` | Создать задачу |
| `PUT` | `/api/tasks/{id}` | Обновить задачу |
| `PATCH` | `/api/tasks/{id}/complete` | Отметить задачу выполненной |
| `DELETE` | `/api/tasks/{id}` | Удалить задачу |

Для удаления предусмотрен ответ `204 No Content`. При отсутствии задачи или категории API возвращает `404 Not Found`, а при нарушении правил валидации — `400 Bad Request`.

### Создание задачи

`POST /api/tasks`

```json
{
  "title": "Изучить Spring Boot",
  "description": "Разобраться с REST API и JPA",
  "userId": 1,
  "categoryId": 1
}
```

Для успешного создания записи пользователь с `userId = 1` и категория с `categoryId = 1` должны существовать в базе данных.

Пример ответа (`201 Created`; ID зависит от базы):

```json
{
  "id": 1,
  "title": "Изучить Spring Boot",
  "category": "Education",
  "completed": false
}
```

### Обновление задачи

`PUT /api/tasks/1`

```json
{
  "title": "Изучить Spring Boot полностью",
  "description": "Повторить REST API, JPA и Hibernate",
  "categoryId": 1,
  "completed": false
}
```

### Завершение задачи

`PATCH /api/tasks/1/complete` — тело запроса не требуется.

### Фильтрация

`GET /api/tasks?categoryId=1` — возвращает задачи с указанным ID категории. Без параметра `categoryId` возвращаются все задачи.

## Swagger / OpenAPI

После запуска приложения доступны:

- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

В Swagger UI можно изучать эндпоинты и отправлять тестовые HTTP-запросы.

## Проверка

Запросы можно тестировать через **Postman** или **Swagger UI**. Для проверки сохранённых данных используйте PostgreSQL:

```bash
docker compose exec db psql -U task_user -d task_manager
```

```sql
SELECT id, title, description, completed, category_id FROM task;
```

## Дальнейшее развитие

Возможные улучшения: отдельные REST API для пользователей и категорий, автоматические тесты (JUnit и MockMvc), пагинация, миграции схемы базы данных и авторизация через Spring Security.
