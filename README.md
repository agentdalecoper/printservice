# printservice

PrintService - проект по заданию на интервью.

Основные технологии: java8, spring, gradle, query dsl, postgree sql, flyway, thymeleaf.

Для логирования используется @Slf4j .


Создание job происходит через Post запрос с xml /api/v1/jobs .
![image](https://user-images.githubusercontent.com/23356183/57305123-33c8cf80-70e9-11e9-9c3c-5e2f37b2a5a0.png)

Запрос по статистике происходит по uri /api/v1/statistics
![image](https://user-images.githubusercontent.com/23356183/57307944-16e2cb00-70ee-11e9-9373-3cdb8a99b56a.png)


Для гибкой фильтрации используется query dsl. Надо сказать пришлось повозиться с этой библиотекой.

Написаны основные тесты на Junit.

Для базовой демонстрации создания job создан view на thymleaf доступный по переходу в браузере по localhost:8080/api/v1/jobs/ .

![image](https://user-images.githubusercontent.com/23356183/57305178-4c38ea00-70e9-11e9-853e-560816741573.png)

Для версионирования бд использовалось flyway migrate.

